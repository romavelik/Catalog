package com.velykorod.productcatalogue.service;

import com.velykorod.productcatalogue.exceptions.EmailExistsException;
import com.velykorod.productcatalogue.persistance.domain.impl.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Iterable <User> findAll();
    User findById(Long id);
    void registerUser(User user) throws EmailExistsException;
    void deleteUser(Long id);
    void editUser(User user);
}
