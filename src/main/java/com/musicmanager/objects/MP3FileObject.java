package com.musicmanager.objects;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.farng.mp3.MP3File;

public class MP3FileObject {
	private String type;
	private String filename;
	private String title;
	private String artist;
	private String album;
	private String genre;
	private String trackNumber;
	private String yearReleased;
	private MP3File file;
	
	public MP3FileObject() {
		
	}
	
	public MP3FileObject(MP3File file) {
		this.file = file;
		this.type = FilenameUtils.getExtension(file.getMp3file().getName());
		this.filename = file.getMp3file().getName();
		if(file.getID3v2Tag() == null) {
			this.title = "";
			this.artist = "";
			this.album = "";
			this.genre = "";
			this.trackNumber = "";
			this.yearReleased = "";
		} else {
			this.title = file.getID3v2Tag().getSongTitle();
			this.artist = file.getID3v2Tag().getLeadArtist();
			this.album = file.getID3v2Tag().getAlbumTitle();
			this.genre = file.getID3v2Tag().getSongGenre();
			this.trackNumber = file.getID3v2Tag().getTrackNumberOnAlbum();
			this.yearReleased = file.getID3v2Tag().getYearReleased();
		}
	}
	
	public MP3FileObject(File file) {
		this.type = FilenameUtils.getExtension(file.getName());
		this.filename = file.getName();
		this.title = "";
		this.artist = "";
		this.album = "";
		this.genre = "";
		this.trackNumber = "";
		this.yearReleased = "";
	}
	
	public MP3FileObject(String type, String filename) {
		this.type = type;
		this.filename = filename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}

	public MP3File getFile() {
		return file;
	}

	public void setFile(MP3File file) {
		this.file = file;
	}
	
	
}
