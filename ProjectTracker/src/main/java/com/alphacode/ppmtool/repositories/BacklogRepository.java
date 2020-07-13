package com.alphacode.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alphacode.ppmtool.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long>{

}
