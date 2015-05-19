package com.musicmanager.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.echonest.api.v4.Artist;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Song;
import com.echonest.api.v4.SongParams;

public class EchoNestAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		EchoNestAPI echoNest = new EchoNestAPI("X4QNARFGEB9NSPTGL");
	    List<Artist> artists;
	    List<Song> songs;
		try {
			artists = echoNest.searchArtists("Weezer");
			SongParams sp = new SongParams();
			sp.setTitle("I Predict a Riot");
			songs = echoNest.searchSongs(sp);
		    if (artists.size() > 0) {
		        Artist weezer = artists.get(0);
		        System.out.println("Similar artists for " + weezer.getName());
		        for (Artist simArtist : weezer.getSimilar(10)) {
		            System.out.println("   " + simArtist.getName());
		        }
		    }
		    assertTrue(true);
		} catch (EchoNestException e) {
			e.printStackTrace();
			fail("Exception - " + e.getMessage());
		}
	}

}
