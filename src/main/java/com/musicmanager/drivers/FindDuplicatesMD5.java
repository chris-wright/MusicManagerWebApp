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
import com.musicmanager.actions.FileActions;

public class FindDuplicatesMD5 {
	public static void main(String[] args) {
		FileActions fa = new FileActions();
		String[] extensions = {"mp3"};
		fa.actionDuplicates("D:\\duplicates", fa.findDuplicatesByMD5("D:\\backup\\music", extensions), true);
	}
}
