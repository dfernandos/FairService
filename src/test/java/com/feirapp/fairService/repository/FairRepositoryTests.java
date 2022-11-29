package com.feirapp.fairService.repository;

import com.feirapp.fairService.entity.Fair;
import com.feirapp.fairService.exceptions.FairException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class FairRepositoryTests {

	@Autowired
	private FairRepository fairRepository;

	@Test
	void shouldSaveAFair() throws FairException {

		//given instantiate
		Fair fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
		//when save a fair
		Fair fair1 = fairRepository.save(fair);
		System.out.println(fair1);
		//then it should return a fair
		Assert.notNull(fair1);

		//given I have a valid fair
		Optional<Fair> fairOptional = fairRepository.findById(fair1.getId());
		System.out.println(fairOptional.get());
		//when delete the fair
		fairRepository.deleteById(fairOptional.get().getId());
	}

	@Test
	void shouldGetAFairById() throws FairException {

		//given save a fair
		Fair fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
		Fair fair1 = fairRepository.save(fair);
		System.out.println(fair1);
		Assert.notNull(fair1);

		//when get a fair by id
		Fair fairFromDB = fairRepository.getById(fair1.getId());
		System.out.println(fairFromDB);

		//then it should return a fair
		Assertions.assertEquals(fairFromDB, fair1);
		fairRepository.deleteById(fairFromDB.getId());
	}

	@Test
	void shouldGetAFairByWeekDay() throws FairException {

		//given save a fair
		Fair fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
		Fair fair1 = fairRepository.save(fair);
		System.out.println(fair1);
		Assert.notNull(fair1);

		//when get a fair by weekday
		List<Fair> fairList = fairRepository.getByweekday(fair1.getWeekday());
		System.out.println(fairList.get(0));

		//then it should return a fair
		Assertions.assertEquals(fairList.get(0), fair1);
		fairRepository.deleteById(fairList.get(0).getId());
	}

}
