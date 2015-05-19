package com.musicmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.farng.mp3.MP3File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.MP3Actions;
import com.musicmanager.mp3.FileTagHelper;

public class MissingTagTest {
	
	MP3Actions actions = new MP3Actions();

	@Test
	public void test() {
		List<MP3File> files = new ArrayList<MP3File>();
		File f = new File("D:\\backup\\music\\01 I Predict A Riot.mp3");
		MP3File file = actions.getNewMP3File(f.getAbsolutePath());
		if(!FileTagHelper.hasFullTags(file.getID3v2Tag())) {
			files.add(file);
		}
		assertFalse(files.isEmpty());
	}

}
