package com.cardg.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cardg.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user usr where usr.user_email = :userEmail", nativeQuery = true)
	public User findByUserEmail(@Param("userEmail") String userEmail);

	@Query(value = "SELECT * FROM user usr where usr.user_type = 'Supplier'", nativeQuery = true)
	public ArrayList<User> findByuserType();
}
