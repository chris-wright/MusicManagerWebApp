package com.musicmanager.test;


import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;

public class ID3Test {
	
	MP3Actions actioner;
	LastFMActions lActioner;

	@Before
	public void setUp() throws Exception {
		actioner = new MP3Actions();
		lActioner = new LastFMActions();
	}

	@Test
	public void test() {
		
		try {
			MP3File mp3File = new MP3File("D:\\backup\\music\\missing_tags\\01 Human.mp3");
			ID3v1 id31 = mp3File.getID3v1Tag();
			AbstractID3v2 id32 = mp3File.getID3v2Tag();
			id32.setAlbumTitle("Day and Age");
			mp3File.save();
			assertNotNull(lActioner.findTrack(mp3File));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		}
	}

}
