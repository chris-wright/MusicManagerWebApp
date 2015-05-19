package com.musicmanager.mp3;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;

public class FileTagHelper {
	
	public static boolean isNullOrEmpty(String attr) {
		if(attr == null || attr.isEmpty())
			return true;
		return false;
	}
	
	public static boolean hasFullTags(AbstractID3v2 tag) {
		if(tag != null) {
			if(!hasTitle(tag)) {
				return false;
			} else if(!hasArtist(tag)) {
				return false;
			} else if(!hasAlbum(tag)) {
				return false;
			} else if(!hasGenre(tag)) {
				return false;
			} else if(!hasTrackNumber(tag)) {
				return false;
			} else if(!hasYearReleased(tag)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean hasGenre(AbstractID3v2 tag) {
		return !isNullOrEmpty(tag.getSongGenre());
	}
	
	public static boolean hasTrackNumber(AbstractID3v2 tag) {
		return !isNullOrEmpty(tag.getTrackNumberOnAlbum());
	}
	
	public static boolean hasYearReleased(AbstractID3v2 tag) {
		return !isNullOrEmpty(tag.getYearReleased());
	}
	
	public static boolean hasAlbum(AbstractID3v2 tag) {
		return !isNullOrEmpty(tag.getAlbumTitle());
	}
	
	public static boolean hasArtist(AbstractID3v2 tag) {
		return !isNullOrEmpty(tag.getLeadArtist());
	}
	
	public static boolean hasTitle(AbstractID3v2 tag) {
		return !isNullOrEmpty(tag.getSongTitle());
	}
	
	public static boolean hasTitleAndAlbum(AbstractID3v2 tag) {
		return hasTitle(tag) && hasAlbum(tag);
	}
	
	public static boolean hasAlbumAndArtist(AbstractID3v2 tag) {
		return hasAlbum(tag) && hasArtist(tag);
	}
	
	public static boolean hasTitleAndArtist(AbstractID3v2 tag) {
		return hasTitle(tag) && hasArtist(tag);
	}
}
