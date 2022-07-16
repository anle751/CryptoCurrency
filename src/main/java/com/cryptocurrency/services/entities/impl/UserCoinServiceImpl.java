package com.cryptocurrency.services.entities.impl;

import com.cryptocurrency.entities.UserCoin;
import com.cryptocurrency.entities.repositories.UserCoinRepository;
import com.cryptocurrency.services.entities.UserCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCoinServiceImpl implements UserCoinService {
    @Autowired
    UserCoinRepository userCoinRepository;

    @Override
    public void save(UserCoin userCoin) {
        userCoinRepository.save(userCoin);
    }

    @Override
    public List<UserCoin> getAll() {
        return userCoinRepository.findAll();
    }
}
