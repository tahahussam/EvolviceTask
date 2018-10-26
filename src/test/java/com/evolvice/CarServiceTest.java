package com.evolvice;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.evolvice.entity.Car;
import com.evolvice.repository.CarRepository;
import com.evolvice.service.ICarService;
import com.evolvice.service.ICommonService;

@SpringBootTest
public class CarServiceTest extends AbstractTransactionalTestNGSpringContextTests implements ICommonService {

	@Autowired
	private ICarService carService;

	@Autowired
	private CarRepository carRepository;

	@BeforeMethod
	public void setup() {

	}

	@AfterMethod
	public void tearDown() {

	}

	@Test
	public void testGetAllCars() throws Exception {
		List<Car> carsList = carService.listAllCars();
		assertNotNull(carsList);
		assertEquals(3, carsList.size());
	}

	@Test
	public void testGetAllCarsWithPageNumber() throws Exception {
		List<Car> carsList = carService.listAllCars(1);
		assertNotNull(carsList);
		assertEquals(3, carsList.size());
	}

	@Test
	public void testGetAllCarsWithPageNumberAndPageSize() throws Exception {
		List<Car> carsList = carService.listAllCars(1, 1);
		assertNotNull(carsList);
		assertEquals(1, carsList.size());

		carsList = carService.listAllCars(1, 2);
		assertNotNull(carsList);
		assertEquals(2, carsList.size());

		carsList = carService.listAllCars(2, 2);
		assertNotNull(carsList);
		assertEquals(1, carsList.size());
	}

	@Test
	public void testUpdateCar() throws Exception {
		final Long carId = 1L;
		final String newBrand = "New Changan";

		Optional<Car> car = carRepository.findById(carId);
		assertTrue(car.isPresent());

		car.get().setBrand(newBrand);

		carService.updateCar(car.get());

		car = carRepository.findById(carId);
		assertTrue(car.isPresent());
		assertEquals(newBrand, car.get().getBrand());
	}

	@Test
	public void testAddNewCar() {
		Car car = new Car();
		car.setBrand("BYD");
		car.setModel("V7");
		car.setProductionYear(2018);
		car.setModelDetails("Made In China");
		Car savedCar = carService.addNewCar(car);
		assertNotNull(savedCar);
		Optional<Car> savedNewCar = carRepository.findById(savedCar.getId());
		assertTrue(savedNewCar.isPresent());
		assertEquals(savedCar.getId(), savedNewCar.get().getId());
	}

	@Test
	public void testDeleteCar() {

		Car car = new Car();
		car.setBrand("BYD");
		car.setModel("V7");
		car.setProductionYear(2018);
		car.setModelDetails("Made In China");
		Car savedCar = carService.addNewCar(car);
		assertNotNull(savedCar);

		boolean isDeletedSuccessfully = carService.deleteCar(savedCar.getId());
		assertTrue(isDeletedSuccessfully);
		Optional<Car> deletedCar = carRepository.findById(savedCar.getId());
		assertFalse(deletedCar.isPresent());
	}
}