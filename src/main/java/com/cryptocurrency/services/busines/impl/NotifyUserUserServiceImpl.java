package com.cryptocurrency.services.busines.impl;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.entities.User;
import com.cryptocurrency.entities.UserCoin;
import com.cryptocurrency.services.busines.NotifyUserService;
import com.cryptocurrency.services.entities.CoinService;
import com.cryptocurrency.services.entities.UserCoinService;
import com.cryptocurrency.services.entities.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class NotifyUserUserServiceImpl implements NotifyUserService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserCoinService userCoinService;
    @Autowired
    private CoinService coinService;

    @Transactional
    @Modifying
    @Override
    public void notifyUser(String username, String symbol) {
        User user = userService.getByUsername(username);
        Coin coin = coinService.getBySymbol(symbol);
        boolean priceUpdated = false;
        if (Objects.nonNull(user)) {
            List<UserCoin> coinList = user.getCoinList();
            for (UserCoin el : coinList) {
                if (el.getSymbol().equals(symbol)) {
                    Float price_usd = coin.getPrice_usd();
                    el.setPrice_usd(price_usd);
                    log.info("User:" + username + " " + "update 'Price_usd' to " + price_usd);
                    priceUpdated = true;
                    break;
                }
            }
            if (!priceUpdated) {
                UserCoin userCoin = UserCoin.builder()
                        .user(user)
                        .id(coin.getId())
                        .symbol(coin.getSymbol())
                        .price_usd(coin.getPrice_usd())
                        .build();
                userCoinService.save(userCoin);
            }
        } else {
            UserCoin userCoin = UserCoin.builder()
                    .id(coin.getId())
                    .symbol(coin.getSymbol())
                    .price_usd(coin.getPrice_usd())
                    .build();
            userCoinService.save(userCoin);
            User newUser = User.builder()
                    .username(username)
                    .coinList(List.of(userCoin))
                    .build();
            userCoin.setUser(newUser);
            userService.save(newUser);
        }

    }
}
