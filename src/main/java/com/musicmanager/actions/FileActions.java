package com.musicmanager.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ArrayListMultimap;
import com.musicmanager.drivers.MD5Checksum;

public class FileActions {
	
	public File createDummyFile(String filename, String contents) {
		File file = new File(filename);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contents);
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return file;
	}

	public void findAndActionDuplicates(String rootDirectory, String duplicatesDirectory, String[] extensions, boolean md5, boolean move) {
		if(move)
			createDuplicatesDirectory(duplicatesDirectory);
		if(md5) {
			ArrayListMultimap<String, File> fileMap = findDuplicatesByMD5(rootDirectory, extensions);
			actionDuplicates("D:\\duplicates", fileMap, move);
		} else {
			Set<File> fileSet = findDuplicates(rootDirectory, extensions);
			actionDuplicates("D:\\duplicates", fileSet, move);
		}

	}

	public Set<File> findDuplicates(String rootDirectory, String[] extensions) {
		int i = 0;
		Long start = System.currentTimeMillis();
		System.out.println();
		File dir = new File(rootDirectory);

		Set<File> fileList = new HashSet<File>();
		System.out.println("Iterating over files...");
		List<File> blacklist = new ArrayList<File>();
		Iterator<File> iterator2 = FileUtils.iterateFiles(dir, extensions, true);
		while(iterator2.hasNext()) {
			File file1 = iterator2.next();
			if(!blacklist.contains(file1)) {
				Iterator<File> iterator3 = FileUtils.iterateFiles(dir, extensions, true);
				while(iterator3.hasNext()) {
					try {
						File file2 = iterator3.next();
						if(FileUtils.contentEquals(file1, file2) && !file1.getAbsoluteFile().toString().equals(file2.getAbsoluteFile().toString())) {
							i++;
							fileList.add(file2);
							blacklist.add(file2);
							System.out.println("Found duplicate (" + i + ") - " + file1.getName() + " = " + file2.getName());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println("Took: " + (end - start) + "ms");
		return fileList;
	}

	public ArrayListMultimap<String, File> findDuplicatesByMD5(String rootDirectory, String[] extensions) {
		int i = 0;

		Long start = System.currentTimeMillis();

		File dir = new File(rootDirectory);

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


		Long end = System.currentTimeMillis();
		System.out.println("Took: " + (end - start) + "ms");
		return fileMap;

	}

	public void createDuplicatesDirectory(String directory) {
		if(!new File(directory).mkdirs()) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void actionDuplicates(String moveDirectory, Set<File> fileSet, boolean move) {
		System.out.println("Checking files...");
		for(File file : fileSet) {
			if(move) {
				System.out.println("Moving " + file);
				//moveFile(file, moveDirectory);
				copyAndDelete(file, moveDirectory);
			} else {
				System.out.println("Listing " + file);
			}
		}
		System.out.println("Done!");
	}

	public void actionDuplicates(String moveDirectory, ArrayListMultimap<String, File> fileMap, boolean move) {
		System.out.println("Checking files...");
		for(String key : fileMap.keys()) {
			List<File> files = fileMap.get(key);
			if(files.size() > 1) {
				boolean first = true;
				for(File file : files) {
					if(!first) {
						if(move) {
							System.out.println("Moving " + file);
							moveFile(file, moveDirectory);
						} else {
							System.out.println("Listing " + file);
						}
					}
					first = false;
				}
			}
		}
		System.out.println("Done!");
	}
	
	public void moveFile(File file, String moveDirectory) {
		File f = new File(moveDirectory);
		String dest = moveDirectory + "\\" + file.getName();
		boolean b = file.renameTo(new File(moveDirectory + "\\" + file.getName()));
		System.out.println(b);
	}

	public static byte[] createChecksum(String filename) throws Exception {
		InputStream fis =  new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	// see this How-to for a faster way to convert
	// a byte array to a HEX string
	public static String getMD5Checksum(String filename) throws Exception {
		byte[] b = createChecksum(filename);
		String result = "";

		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
	
	public void copyAndDelete(File file, String moveDir) {
		InputStream inStream = null;
		OutputStream outStream = null;
	 
	    	try{
	 
	    	    File bfile =new File(moveDir + "\\" + file.getName());
	 
	    	    inStream = new FileInputStream(file);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	 
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	 
	    	    //delete the original file
	    	    file.delete();
	    	    if(file.exists()) {
	    	    	file.delete();
	    	    }
	 
	    	    System.out.println("File is copied successful!");
	 
	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}
	}
}
