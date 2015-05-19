package com.musicmanager.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.junit.Test;

public class UnsupportedOperationTest {

	@Test
	public void test() {
		try {
			MP3File f = new MP3File("D:\\backup\\music\\Lady Gaga\\Lady GaGa - The Fame [2008][CD+SkidVid_XviD+Cov]320Kbps\\02  Lady GaGa - Lovegame.mp3");
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (TagException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

}
