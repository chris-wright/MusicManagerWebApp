package com.musicmanager.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.ArrayListMultimap;
import com.musicmanager.actions.FileActions;
import com.musicmanager.objects.MP3FileObject;

@ManagedBean(name = "duplicateViewManagedBean")
@SessionScoped
public class DuplicateViewManagedBean {

	private List<MP3FileObject> fileObjects = new ArrayList<MP3FileObject>();
	private int size;
	private FileActions fileActions;
	private boolean showPanel = false;
	
	@PostConstruct
	public void init() {
		fileActions = new FileActions();
	}
	
	public void buildDuplicatesList() {
		showPanel = true;
		String[] extensions = {"mp3"};
		ArrayListMultimap<String, File> dupes = fileActions.findDuplicatesByMD5("D:\\backup\\music", extensions);
		for(String key : dupes.keySet()) {
			List<File> f = dupes.get(key);
			if(f.size() > 1) {
				for(File file : f) {
					fileObjects.add(new MP3FileObject(file));
				}
			}
		}
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

	public FileActions getFileActions() {
		return fileActions;
	}

	public void setFileActions(FileActions fileActions) {
		this.fileActions = fileActions;
	}

	public boolean isShowPanel() {
		return showPanel;
	}

	public void setShowPanel(boolean showPanel) {
		this.showPanel = showPanel;
	}
}
