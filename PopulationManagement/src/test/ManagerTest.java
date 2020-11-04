package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import model.Manager;
import model.Record;

class ManagerTest {
	
	private Manager manager;
	
	public void setup1() {
		manager = new Manager();
	}
	
	@Test
	public void generationTest() throws IOException {
		setup1();
		boolean flag = manager.loadData();
		Record record = null;
		if (flag) {
			record = manager.generateRecord();
		}
		assertTrue(record != null);
		System.out.println(record.getName());
		System.out.println(record.getLastname());
		System.out.println(record.getCode());
		System.out.println(record.getGender());
		System.out.println(record.getHeight());
		System.out.println(record.getNacionality());
		System.out.println(record.getBirthDate());
		System.out.println(record.getAge());
	}

}
