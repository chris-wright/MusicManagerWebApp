package com.musicmanager.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.LastFMActions;

public class FindTitleTest {
	
	LastFMActions actioner;

	@Before
	public void setUp() throws Exception {
		actioner = new LastFMActions();
	}

	@Test
	public void test() {
		assertNotNull(actioner.findTracks("Hallelujah"));
	}

}
