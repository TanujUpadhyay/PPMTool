package com.alphacode.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alphacode.ppmtool.domain.User;

@Repository
public interface UserRepositiry extends CrudRepository<User, Long> {
	
}
