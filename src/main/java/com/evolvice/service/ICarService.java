package com.evolvice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evolvice.entity.Car;

@Service
public interface ICarService {

	List<Car> listAllCars(int... pageDetails) throws Exception;

	boolean updateCar(Car car);

	boolean deleteCar(Long id);

	Car addNewCar(Car car);
}