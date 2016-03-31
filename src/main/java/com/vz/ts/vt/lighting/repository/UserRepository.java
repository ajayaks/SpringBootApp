package com.vz.ts.vt.lighting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vz.ts.vt.lighting.entity.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	User findById(Long id);
	
	@Modifying
	@Query("update User u set u.authToken = :authToken where u.id = :id")
	int updateAuthToken(@Param("authToken") String authToken, @Param("id") Long id);
	
}
