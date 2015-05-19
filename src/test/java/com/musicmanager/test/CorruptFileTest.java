package com.musicmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.FileActions;
import com.musicmanager.actions.MP3Actions;

public class CorruptFileTest {
	
	File file;
	FileActions fActioner;
	MP3Actions mActioner;

	@Before
	public void setUp() throws Exception {
		file = new File("C:\\git\\Other\\MusicManager\\resources\\music\\[U-8994][T-26645][P-832866]17 time is running out (live at earl.mp3");
		fActioner = new FileActions();
		mActioner = new MP3Actions();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		MP3File mp3File = mActioner.getNewMP3File(file.getAbsolutePath());
		if(mp3File == null) {
			File file2 = new File("C:\\git\\Other\\MusicManager\\resources\\music\\[U-8994][T-26645][P-832866]17 time is running out (live at earl.mp3");
			assertFalse(file2.exists());
		} else {
			assertTrue(false);
		}
	}

}
