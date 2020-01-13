package com.velykorod.productcatalogue.persistance.repository;

import com.velykorod.productcatalogue.persistance.domain.impl.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.login =:login, u.password =:password, u.email =:email where u.id =:id")
    void updateUser(@Param("login") String login, @Param("password") String password, @Param("email") String email, @Param("id") Long id);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

}
