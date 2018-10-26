package com.evolvice;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertEquals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.evolvice.entity.Car;
import com.evolvice.repository.CarRepository;
import com.evolvice.service.ICommonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CarServiceIntegrationTest extends AbstractTransactionalTestNGSpringContextTests implements ICommonService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private MockMvc mockMvc;

	@BeforeMethod
	public void setup() {

	}

	@AfterMethod
	public void tearDown() {

	}

	@Test
	public void testGetAllCars() throws Exception {
		mockMvc.perform(get("/car/all/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	public void testUpdateCar() throws Exception {
		Optional<Car> car = carRepository.findById(1L);
		assertTrue(car.isPresent());

		final String newModel = "New CS35";
		car.get().setModel(newModel);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(
				patch("/car/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(car.get())))
				.andExpect(status().isOk()).andExpect(jsonPath("$", is(true)));

		car = carRepository.findById(1L);
		assertTrue(car.isPresent());
		assertEquals(newModel, car.get().getModel());
	}

	@Test
	public void testAddNewCar() throws Exception {
		Car car = new Car();
		car.setBrand("BYD");
		car.setModel("V7");
		car.setProductionYear(2018);
		car.setModelDetails("Made In China");

		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/car/new").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(car)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteCar() throws Exception {
		Car car = new Car();
		car.setBrand("BYD");
		car.setModel("V7");
		car.setProductionYear(2018);
		car.setModelDetails("Made In China");

		Car savedCar = carRepository.save(car);

		mockMvc.perform(delete("/car/delete/" + savedCar.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(true)));

		Optional<Car> deletedCar = carRepository.findById(savedCar.getId());

		assertFalse(deletedCar.isPresent());
	}
}