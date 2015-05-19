package com.musicmanager.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.FileActions;

public class MoveFileTest {

	private FileActions fileActions;
	private File file1;
	private File file2;

	@Before
	public void setUp() throws Exception {
		fileActions = new FileActions();
		file1 = fileActions.createDummyFile("resources/1.txt", "abcdefghijklmnopqrstuvqxyz");
		file2 = new File("C:\\git\\Other\\MusicManager\\resources\\1.txt");
	}

	@After
	public void tearDown() throws Exception {
		file1.delete();
		file2.delete();
		// file2 doesn't delete, why?
		File file3 = new File("C:\\git\\Other\\MusicManager\\resources\\move\\1.txt");
		file3.delete();
	}

	@Test
	public void test() {
		fileActions.moveFile(file1, "C:\\git\\Other\\MusicManager\\resources\\move");
		assertFalse(file2.exists());
	}

}
