package com.musicmanager.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicmanager.util.PropertiesReader;

public class PropertiesTest {

	@Test
	public void test() {

		PropertiesReader pReader = new PropertiesReader();
		try {
			pReader.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
