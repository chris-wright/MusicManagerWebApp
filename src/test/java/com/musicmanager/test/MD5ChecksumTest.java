package com.musicmanager.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.FileActions;

public class MD5ChecksumTest {

	private FileActions fileActions;
	private File file1;
	private File file2;
	private File file3;
	
	@Before
	public void setUp() throws Exception {
		fileActions = new FileActions();
		file1 = fileActions.createDummyFile("resources/1.txt", "abcdefghijklmnopqrstuvqxyz");
		file2 = fileActions.createDummyFile("resources/2.txt", "abcdefghijklmnopqrstuvqxyz");
		file3 = fileActions.createDummyFile("resources/3.txt", "123456789");
	}

	@After
	public void tearDown() throws Exception {
		file1.deleteOnExit();
		file2.deleteOnExit();
		file3.deleteOnExit();
	}

	@Test
	public void test() {
		try {
			assertEquals(fileActions.getMD5Checksum(file1.getName()), fileActions.getMD5Checksum(file2.getName()));
			assertNotEquals(fileActions.getMD5Checksum(file1.getName()), fileActions.getMD5Checksum(file3.getName()));
			assertNotEquals(fileActions.getMD5Checksum(file2.getName()), fileActions.getMD5Checksum(file3.getName()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
