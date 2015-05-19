package com.musicmanager.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;
import com.musicmanager.mp3.FileTagHelper;

public class EnrichSpecificID3Test {

	MP3Actions actioner;
	LastFMActions lActioner;
	ClassLoader classLoader;

	@Before
	public void setUp() throws Exception {
		actioner = new MP3Actions();
		lActioner = new LastFMActions();
		classLoader = getClass().getClassLoader();
	}

	@Test
	public void test() {
		System.out.println("Creating MP3File...");
		MP3File file;
		try {
			file = new MP3File("C:\\git\\Other\\MusicManager\\resources\\music\\01 I Predict A Riot.mp3");
			assertNotNull(file);
			System.out.println("File created, and not null...");
			if(file.hasID3v2Tag()) {
				if(FileTagHelper.hasFullTags(file.getID3v2Tag())) {
					System.out.println("Item has full tags, no work to do!");
					assertTrue(true);
				} else {
					System.out.println("Item missing tags, work to do...");
					actioner.enrichFile(file);
					System.out.println("File enriched, re-checking...");
					if(FileTagHelper.hasFullTags(file.getID3v2Tag())) {
						System.out.println("Item has full tags, no work to do!");
						assertTrue(true);
					} else {
						System.out.println("Item missing tags, enriching failed....");
						fail("Still doesn't have full tags...");
					}
				}
			} else {
				System.out.println("No tags, pass I guess?");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
