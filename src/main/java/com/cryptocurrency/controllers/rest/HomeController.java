package com.cryptocurrency.controllers.rest;

import com.cryptocurrency.entities.Coin;
import com.cryptocurrency.entities.CoinInfo;
import com.cryptocurrency.services.busines.NotifyUserService;
import com.cryptocurrency.services.busines.ObserverService;
import com.cryptocurrency.services.busines.UpdateCoinService;
import com.cryptocurrency.services.entities.CoinService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crypto")
@Api(value = "Crypto")
public class HomeController {

    @Autowired
    private NotifyUserService notifyUserService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private ObserverService observerService;

    @Autowired
    private UpdateCoinService updateCoinService;

    @PostMapping("/notify")
    public void notify(@RequestParam(value = "username") String username, @RequestParam("symbol") String symbol) {
        notifyUserService.notifyUser(username, symbol);
    }

    @GetMapping("/getCoin")
    public Coin getCoin(@RequestParam("symbol") String symbol) {
        return coinService.getBySymbol(symbol);
    }

    @GetMapping
    public List<CoinInfo> getCoinsInfo() {
        return coinService.getCoinsInfo();
    }

    @Scheduled(fixedRate = 60000)
    public void schedule() {
        updateCoinService.update();
        observerService.observe();
    }
}
