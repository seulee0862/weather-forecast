package com.project02server.user.repository;

import static com.project02server.user.domain.QUser.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project02server.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QUserRepository {

	private final JPAQueryFactory queryFactory;

	public Optional<User> findByEmailAndPlatform(String email, String oAuthProvider) {
		return Optional.ofNullable(
			queryFactory
			.selectFrom(user)
			.where(
				eqEmailAndPlatform(email, oAuthProvider)
			)
			.fetchOne()
		);
	}

	private BooleanExpression eqEmailAndPlatform(String email, String oAuthProvider) {
		return user.email.eq(email).and(user.oAuthProvider.eq(oAuthProvider));
	}

}
