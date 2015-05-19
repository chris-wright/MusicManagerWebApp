package com.musicmanager.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.FileActions;
import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;

public class FailedTagTest {
	
	LastFMActions lActioner;
	MP3Actions mActioner;
	FileActions fActioner;

	@Before
	public void setUp() throws Exception {
		mActioner = new MP3Actions();
		lActioner = new LastFMActions();
		fActioner = new FileActions();
	}

	@Test
	public void test() {
		String filename = "D:\\backup\\music\\Muse\\Earls Court\\[U-8993][T-26645][P-832866]17 time is running out (live at earl.mp3";
		MP3File file = mActioner.getNewMP3File(filename);
	}

}
