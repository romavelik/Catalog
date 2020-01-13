package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.exceptions.EmailExistsException;
import com.velykorod.productcatalogue.exceptions.UserNotFoundException;
import com.velykorod.productcatalogue.persistance.domain.impl.Role;
import com.velykorod.productcatalogue.persistance.domain.impl.user.User;
import com.velykorod.productcatalogue.persistance.domain.impl.user.UserDto;
import com.velykorod.productcatalogue.persistance.repository.UserRepository;
import com.velykorod.productcatalogue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public User registerUser(UserDto userDto) throws EmailExistsException{
        if(emailExists(userDto.getEmail())){
            throw new EmailExistsException(String.format("Account with email %s already exists", userDto.getEmail()));
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setLogin(userDto.getLogin());
        user.setRoles(Arrays.asList(Role.USER));
        userRepository.save(user);
        return user;

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUser(User user) {
        userRepository.updateUser(user.getLogin(), user.getPassword(), user.getEmail(), user.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByLogin(username);
        result.orElseThrow(()-> new UserNotFoundException(String.format("User with username %s not found", username)));
        return result.get();
    }

    private boolean emailExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

}
