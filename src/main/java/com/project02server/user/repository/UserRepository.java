package com.project02server.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project02server.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
