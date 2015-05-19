package com.musicmanager.mp3;

import com.musicmanager.actions.MP3Actions;

public class MP3Driver {
	public static void main(String[] args) {
		
		MP3Actions actions = new MP3Actions();
		int i = actions.getFilesWithoutTags().size();
		System.out.println("Without Tags: " + i);
		
		//for(String s : actions.getFullTagStatus()) {
		//	if(!s.isEmpty()) {
		//		System.out.println(s);
		//	}
		//}
	}
}
