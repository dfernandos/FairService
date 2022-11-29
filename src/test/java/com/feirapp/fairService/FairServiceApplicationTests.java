package com.feirapp.fairService;

import com.feirapp.fairService.entity.Fair;
import com.feirapp.fairService.exceptions.FairException;
import com.feirapp.fairService.service.FairService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class FairServiceApplicationTests {

	@Autowired
	private FairService fairService;

	@Test
	void shouldSaveAndDeleteAFair() throws FairException {

		//given instantiate
		Fair fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
		//when save a fair
		Fair fair1 = fairService.saveFair(fair);
		System.out.println(fair1);
		//then it should return a fair
		Assert.notNull(fair1);

		//given I have a valid fair
		Optional<Fair> fairOptional = fairService.getFair(fair1.getId());
		System.out.println(fairOptional.get());
		//when delete the fair
		boolean deleted = fairService.deleteFair(fairOptional.get().getId());
		//then it should return true
		Assertions.assertTrue(deleted);
	}

	@Test
	void shouldGetAFairById() throws FairException {

		//given save a fair
		Fair fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
		Fair fair1 = fairService.saveFair(fair);
		System.out.println(fair1);
		Assert.notNull(fair1);

		//when get a fair by id
		Optional<Fair> fairOptional = fairService.getFair(fair1.getId());
		System.out.println(fairOptional.get());

		//then it should return a fair
		Assertions.assertEquals(fairOptional.get(), fair1);
		boolean deleted = fairService.deleteFair(fairOptional.get().getId());
		Assertions.assertTrue(deleted);
	}

	@Test
	void shouldGetAFairByWeekDay() throws FairException {

		//given save a fair
		Fair fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
		Fair fair1 = fairService.saveFair(fair);
		System.out.println(fair1);
		Assert.notNull(fair1);

		//when get a fair by weekday
		List<Fair> fairOptional = fairService.getFairByWeekDay(fair1.getWeekday());
		System.out.println(fairOptional.get(0));

		//then it should return a fair
		Assertions.assertEquals(fairOptional.get(0), fair1);
		boolean deleted = fairService.deleteFair(fairOptional.get(0).getId());
		Assertions.assertTrue(deleted);
	}

}
