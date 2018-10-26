package com.evolvice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evolvice.entity.Car;
import com.evolvice.service.ICarService;

@RestController
@RequestMapping(path = "/car")
public class CarController {

	@Autowired
	private ICarService carService;

	@GetMapping(path = { "/all", "/all/{pageNumber:[1-9][\\d]*}",
			"/all/{pageNumber:[1-9][\\d]*}/{pageSize:[1-9][\\d]*}" })
	public ResponseEntity<List<Car>> getAllCars(
			@PathVariable(value = "pageNumber", required = false) Optional<Integer> pageNumber,
			@PathVariable(value = "pageSize", required = false) Optional<Integer> pageSize) {

		try {
			List<Car> carsList;

			if (pageNumber.isPresent() && pageSize.isPresent())
				carsList = carService.listAllCars(pageNumber.get(), pageSize.get());
			else if (pageNumber.isPresent())
				carsList = carService.listAllCars(pageNumber.get());
			else
				carsList = carService.listAllCars();

			return new ResponseEntity<>(carsList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/new")
	public ResponseEntity<Car> saveNewCar(@RequestBody(required = true) Car carToBeAdded) {
		Car savedCar = carService.addNewCar(carToBeAdded);
		return new ResponseEntity<Car>(savedCar, savedCar != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PatchMapping("/update")
	public ResponseEntity<Boolean> updateCar(@RequestBody(required = true) Car carToBeUpdated) {
		boolean succeed = carService.updateCar(carToBeUpdated);
		return new ResponseEntity<Boolean>(succeed, succeed ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/delete/{carId:[\\d]+}")
	public ResponseEntity<Boolean> updateCar(@PathVariable Long carId) {
		boolean succeed = carService.deleteCar(carId);
		return new ResponseEntity<Boolean>(succeed, succeed ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}