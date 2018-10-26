package com.evolvice.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.evolvice.entity.Car;
import com.evolvice.repository.CarRepository;
import com.evolvice.service.ICarService;
import com.evolvice.service.ICommonService;

@Service
public class CarServiceImpl implements ICommonService, ICarService {

	private final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> listAllCars(int... pageDetails) throws Exception {
		try {
			Page<Car> carsPage = carRepository.findAll(gotoPage(pageDetails));
			return carsPage.getContent();
		} catch (Exception e) {
			logger.error("Exception in listing All Cars: ", e);
			throw e;
		}
	}

	@Override
	public boolean updateCar(Car car) {
		try {
			if (car != null) {
				Optional<Car> carFromDataBase = carRepository.findById(car.getId());
				if (carFromDataBase.isPresent()) {
					logger.info("Car with id {} found successfully", car.getId());
					Car dbCar = carFromDataBase.get();
					dbCar.setBrand(car.getBrand());
					dbCar.setModel(car.getModel());
					dbCar.setProductionYear(car.getProductionYear());
					dbCar.setModelDetails(car.getModelDetails());
					dbCar = carRepository.save(dbCar);
					logger.info("Car with id {} updated successfully", car.getId());
					return dbCar != null;
				}
			}

		} catch (Exception e) {
			logger.error("Exception in updateing Car with id " + car.getId(), e);
		}
		return false;
	}

	@Override
	public boolean deleteCar(Long id) {
		try {
			Optional<Car> carFromDataBase = carRepository.findById(id);
			if (carFromDataBase.isPresent()) {
				carRepository.deleteById(id);
				logger.info("Car with id {} deleted successfully", id);
				return true;
			}
		} catch (Exception e) {
			logger.error("Exception in deleting Car with id " + id, e);
		}
		return false;
	}

	@Override
	public Car addNewCar(Car car) {
		try {
			if (car != null) {
				car.setId(-1L);
				car = carRepository.save(car);
				return car;
			}
		} catch (Exception e) {
			logger.error("Exception in adding New Car", e);
		}
		return null;
	}
}