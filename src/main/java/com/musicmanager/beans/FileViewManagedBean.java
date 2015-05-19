package com.musicmanager.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;

import com.musicmanager.objects.MP3FileObject;

@ManagedBean(name = "fileViewManagedBean")
@SessionScoped
public class FileViewManagedBean {
	private List<MP3FileObject> fileObjects = new ArrayList<MP3FileObject>();
	private int size;
	private boolean showPanel = false;
	
	public FileViewManagedBean(){
		
	}
	
	public void populateFileList() {
		showPanel = true;
		String[] extensions = {"mp3"};
		Iterator<File> iterator = FileUtils.iterateFiles(new File("D:\\backup\\music"), extensions, true);
		while(iterator.hasNext()) {
			File f = iterator.next();
			MP3FileObject mp3 = new MP3FileObject();
			mp3.setType("mp3");
			mp3.setFilename(f.getName());
			fileObjects.add(mp3);
		}
		setSize(fileObjects.size());
	}

	public List<MP3FileObject> getFileObjects() {
		return fileObjects;
	}

	public void setFileObjects(List<MP3FileObject> fileObjects) {
		this.fileObjects = fileObjects;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isShowPanel() {
		return showPanel;
	}

	public void setShowPanel(boolean showPanel) {
		this.showPanel = showPanel;
	}
	
	
}
