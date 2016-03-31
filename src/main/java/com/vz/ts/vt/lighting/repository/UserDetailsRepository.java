package com.vz.ts.vt.lighting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vz.ts.vt.lighting.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long>{

}
