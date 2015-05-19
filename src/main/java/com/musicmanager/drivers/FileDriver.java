package com.musicmanager.drivers;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class FileDriver {
	public static void main(String[] args) {
		
		File dir = new File("D:\\backup\\music");
		String[] extensions = {"mp3"};
		Iterator<File> iterator = FileUtils.iterateFiles(dir, extensions, true);
		int i = 0;
		while(iterator.hasNext()) {
			i++;
			File file = iterator.next();
			System.out.println(i + " - " + file);
		}
	}
}
