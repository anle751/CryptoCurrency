package com.cryptocurrency.services.entities.impl;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.entities.CoinInfo;
import com.cryptocurrency.entities.repositories.CoinRepository;
import com.cryptocurrency.services.entities.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CoinServiceImpl implements CoinService {
    @Autowired
    CoinRepository coinRepository;

    @Override
    public Coin getBySymbol(String symbol) {
        return coinRepository.findBySymbol(symbol);
    }

    @Override
    public Coin getByDbId(Long dbId) {
        return coinRepository.getCoinByDbId(dbId);
    }

    @Transactional
    @Modifying
    @Override
    public void save(Coin coin) {
        Coin coinDb = coinRepository.findBySymbol(coin.getSymbol());
        if (Objects.isNull(coinDb)) {
            coinRepository.save(coin);
        } else {
            coinDb.setName(coin.getName());
            coinDb.setNameid(coin.getNameid());
            coinDb.setRank(coin.getRank());
            coinDb.setPrice_usd(coin.getPrice_usd());
            coinDb.setPercent_change_24h(coin.getPercent_change_24h());
            coinDb.setPercent_change_1h(coin.getPercent_change_1h());
            coinDb.setPercent_change_7d(coin.getPercent_change_7d());
            coinDb.setMarket_cap_usd(coin.getMarket_cap_usd());
            coinDb.setVolume24(coin.getVolume24());
            coinDb.setVolume24_native(coin.getVolume24_native());
            coinDb.setCsupply(coin.getCsupply());
            coinDb.setPrice_btc(coin.getPrice_btc());
            coinDb.setTsupply(coin.getTsupply());
            coinDb.setMsupply(coin.getMsupply());
        }
    }

    @Override
    public List<CoinInfo> getCoinsInfo() {
        return coinRepository.findAll().stream().map(a -> CoinInfo.builder()
                        .id(a.getId())
                        .symbol(a.getSymbol())
                        .build())
                .toList();
    }
}
