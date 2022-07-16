package com.cryptocurrency.services.entities;

import com.cryptocurrency.entities.User;

public interface UserService {
    public User getByUsername(String username);

    public void save(User user);
}
