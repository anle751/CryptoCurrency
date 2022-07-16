package com.cryptocurrency.services.entities;

import com.cryptocurrency.entities.UserCoin;

import java.util.List;

public interface UserCoinService {
    public void save(UserCoin userCoin);

    public List<UserCoin> getAll();
}
