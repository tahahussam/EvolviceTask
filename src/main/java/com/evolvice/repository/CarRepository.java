package com.evolvice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evolvice.entity.Car;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called CarRepository
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

	Page<Car> findAll(Pageable pageable);

}