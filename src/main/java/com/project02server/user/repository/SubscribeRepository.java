package com.project02server.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project02server.user.domain.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
}
