package com.cryptocurrency.services.busines.impl;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.entities.UserCoin;
import com.cryptocurrency.services.busines.ObserverService;
import com.cryptocurrency.services.entities.CoinService;
import com.cryptocurrency.services.entities.UserCoinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ObserverServiceImpl implements ObserverService {
    @Autowired
    private CoinService coinService;
    @Autowired
    private UserCoinService userCoinService;

    @Override
    public void observe() {
        List<UserCoin> userCoins = userCoinService.getAll();
        for (UserCoin el : userCoins) {
            Coin coin = coinService.getBySymbol(el.getSymbol());
            Float coinPrice_usd = coin.getPrice_usd();
            Float userCoinPrice_usd = el.getPrice_usd();
            log.info("curr:"+coinPrice_usd+";"+"lock:"+userCoinPrice_usd);
            float result = ((coinPrice_usd * 100) / userCoinPrice_usd) - 100;
            String str = "Price change: {" + " user:" + el.getUser().getUsername() + " symbol:" + el.getSymbol() + " percent:" + result + "}";
            log.info(str);
            if (Math.abs(result) > 1) {
                log.warn(str);
            }
        }
    }
}
