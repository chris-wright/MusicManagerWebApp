package com.musicmanager.drivers;

import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v1_1;
import org.farng.mp3.id3.ID3v2_2;
import org.farng.mp3.id3.ID3v2_3;
import org.farng.mp3.id3.ID3v2_4;

public class Driver {
	public static void main(String[] args) {
		try {
			File file = new File("D:\\backup\\music\\School@1289255363.mp3");
			MP3File song = new MP3File(file);
			
		    ID3v1 tag2 = song.getID3v1Tag();
		    AbstractID3v2 tag3 = song.getID3v2Tag();
		    
		    System.out.println("ID3v1 Title: " + tag2.getTitle() + " ID3v2 Title: " + tag3.getSongTitle());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		}
	}
}
