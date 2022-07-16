package com.cryptocurrency.entities.repositories;

import com.cryptocurrency.entities.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoinRepository extends JpaRepository<UserCoin, Long> {

}
