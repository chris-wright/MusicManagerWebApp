package com.musicmanager.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.musicmanager.actions.FileActions;

import java.io.File;
import java.util.Set;

public class FindDuplicatesMD5Test {

	private FileActions fileActions;
	private File file1;
	private File file2;
	private File file3;
	private File file4;
	private File file5;
	
	@Before
	public void setUp() throws Exception {
		fileActions = new FileActions();
		file1 = fileActions.createDummyFile("resources/1.txt", "abcdefghijklmnopqrstuvqxyz");
		file2 = fileActions.createDummyFile("resources/2.txt", "abcdefghijklmnopqrstuvqxyz");
		file3 = fileActions.createDummyFile("resources/3.txt", "123456789");
		file4 = fileActions.createDummyFile("resources/4.txt", "123456789");
		file5 = fileActions.createDummyFile("resources/5.txt", "123456789");
	}

	@After
	public void tearDown() throws Exception {
		file1.delete();
		file2.delete();
		file3.delete();
		file4.delete();
		file5.delete();
	}

	@Test
	public void test() {
		String[] extensions = {"txt"};
		ArrayListMultimap<String, File> files = fileActions.findDuplicatesByMD5("resources", extensions);
		assertEquals(files.keySet().size(), 2);
	}
}
