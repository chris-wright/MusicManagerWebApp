package com.musicmanager.drivers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.musicmanager.actions.FileActions;

public class FindDuplicates {
	public static void main(String[] args) {
		FileActions fa = new FileActions();
		String[] extensions = {"mp3"};
		fa.actionDuplicates("C:\\git\\Other\\MusicManager\\resources\\duplicate_music", fa.findDuplicates("C:\\git\\Other\\MusicManager\\resources\\music", extensions), false);
	}
}
