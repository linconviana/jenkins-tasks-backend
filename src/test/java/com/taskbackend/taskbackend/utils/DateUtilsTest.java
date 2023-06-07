package com.taskbackend.taskbackend.utils;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		
		LocalDate date = LocalDate.of(2030, 01, 01);
		
		Assertions.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		
		LocalDate date = LocalDate.of(2010, 01, 01);
		
		Assertions.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDataAtual() {
		
		LocalDate date = LocalDate.now();
		
		Assertions.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
}
