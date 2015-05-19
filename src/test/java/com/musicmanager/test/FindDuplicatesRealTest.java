package com.musicmanager.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.FileActions;

public class FindDuplicatesRealTest {

	FileActions fActioner = new FileActions();

	@Test
	public void test() {
		try {
			System.out.println(fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 Human.mp3"));
			System.out.println(fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 Human (2).mp3"));
			System.out.println(fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 I Predict A Riot.mp3"));
			assertEquals(fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 Human.mp3"), fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 Human (2).mp3"));
			assertNotEquals(fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 I Predict A Riot.mp3"), fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 Human.mp3"));
			assertNotEquals(fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 I Predict A Riot.mp3"), fActioner.getMD5Checksum("C:\\git\\Other\\MusicManager\\resources\\music\\01 Human (2).mp3"));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
