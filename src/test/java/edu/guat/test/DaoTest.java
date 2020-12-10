package edu.guat.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.guat.PetHostingApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PetHostingApp.class)
public class DaoTest {
	
	@Test
	public void test() {
		System.out.println("1111");
	}
	
}
