package com.cryptocurrency.services.entities;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.entities.CoinInfo;

import java.util.List;

public interface CoinService {
    public Coin getBySymbol(String symbol);

    public Coin getByDbId(Long dbId);

    public void save(Coin coin);

    public List<CoinInfo> getCoinsInfo();
}
