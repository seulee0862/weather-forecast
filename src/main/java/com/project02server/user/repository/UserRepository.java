package com.project02server.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project02server.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u "
		+ "from User u "
		+ "left join fetch u.subscribe s "
		+ "where s.isSubscribed = true")
	List<User> findUsersByActiveSubscribe();
}
