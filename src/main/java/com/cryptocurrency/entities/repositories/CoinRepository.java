package com.cryptocurrency.entities.repositories;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.entities.CoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    public Coin findBySymbol(String symbol);

    public Coin getCoinByDbId(Long id);
}
