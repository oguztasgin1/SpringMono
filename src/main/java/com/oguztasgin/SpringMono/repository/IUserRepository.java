package com.oguztasgin.SpringMono.repository;

import com.oguztasgin.SpringMono.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Boolean existsUserByUsername(String username);

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);
}