package com.cryptocurrency.services.busines.impl;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.services.busines.UpdateCoinService;
import com.cryptocurrency.services.entities.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
@Slf4j
public class UpdateCoinServiceImpl implements UpdateCoinService {
    @Value("${crypto.coins.ids}")
    private String ids;
    @Value("${crypto.coins.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CoinService coinService;

    @Override
    public void update() {
        ResponseEntity<Coin[]> responseEntity = restTemplate.getForEntity(url + "?id=" + ids, Coin[].class);
        Coin[] coins = responseEntity.getBody();
        if (Objects.nonNull(coins)) {
            Arrays.stream(coins)
                    .peek(a -> coinService.save(a))
                    .forEach(a -> log.debug("Coin:" + a.getSymbol() + "updated"));
        }
    }
}
