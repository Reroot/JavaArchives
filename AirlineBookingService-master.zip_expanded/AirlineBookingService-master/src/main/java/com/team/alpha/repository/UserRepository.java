package com.team.alpha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value="from User where username=:username")
	User findByUsername1(@Param("username") String username);
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
    Boolean existsByEmail(String email);
	
	@Query(value="from User where username=:username and password=:password")
	User findByUsernamePassword(@Param("username") String username, @Param("password") String password);
	
	User findByEmail(String email);
	
	@Query("from User where id in (:pIdList)")
    List<String> findUserIdList(@Param("pIdList") List<Long> idList);
}
