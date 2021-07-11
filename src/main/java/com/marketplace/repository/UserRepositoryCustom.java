package com.marketplace.repository;

import com.marketplace.dto.AuthDto;
import com.marketplace.model.Product;
import com.marketplace.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);


    List<User> getPaginatedUsers(int firstResults, int maxResults);

    long getTotalUsers();
}

