package com.musicmanager.drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.musicmanager.util.PropertiesReader;

public class QuarantineDuplicatesMD5 {
	public static void main(String[] args) {
		
		int i = 0;

		Long start = System.currentTimeMillis();
		
		File dir = new File("C:\\git\\Other\\MusicManager\\resources\\music");
		
		if(!new File("C:\\git\\Other\\MusicManager\\resources\\duplicate_music").mkdirs()) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String[] extensions = {"mp3"};
		
		ArrayListMultimap<String, File> fileMap = ArrayListMultimap.create();
		System.out.println("Iterating over files...");
		@SuppressWarnings("unchecked")
		Iterator<File> iterator = FileUtils.iterateFiles(dir, extensions, true);
		while(iterator.hasNext()) {
			i++;
			System.out.println(i);
			File file = iterator.next();
			try {
				fileMap.put(MD5Checksum.getMD5Checksum(file.getAbsolutePath()), file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done!");
		System.out.println("Checking files...");
		for(String key : fileMap.keySet()) {
			List<File> files = fileMap.get(key);
			System.out.println(files.get(0).getName());
			if(files.size() > 1) {
				boolean candidate = false;
				for(File file : files) {
					if(!candidate) {
						System.out.println("Moving file - " + file.getAbsolutePath() + " - " + file.getName());
						file.renameTo(new File("C:\\git\\Other\\MusicManager\\resources\\duplicate_music\\" + file.getName()));
						candidate = !candidate;
					}
						
				}
			}
		}
		System.out.println("Done!");
		Long end = System.currentTimeMillis();
		System.out.println("Took: " + (end - start) + "ms");
	}
}
