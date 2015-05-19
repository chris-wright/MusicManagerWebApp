package com.musicmanager.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.farng.mp3.MP3File;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;

public class LastFMActions {
	
	String key = "082af20fbd8d96480496d231e906da2a"; //this is the key used in the Last.fm API examples
	String user = "chrisanwright";
	
	public Track findTrack(MP3File mp3File) {
		if(mp3File.getID3v2Tag() == null) {
			return null;
		} else {
			String title = mp3File.getID3v2Tag().getSongTitle();
			String author = mp3File.getID3v2Tag().getLeadArtist();
			return findTrack(title, author);
		}
	}
	
	public Set<String> findAlbums(String artist) {
		Set<String> albums = new HashSet<String>();
		for(Album alb : Artist.getTopAlbums(artist, key)) {
			albums.add(alb.getName());
		}
		return albums;
	}
	
	public Set<String> findAlbumsWithTrack(String artist, String title) {
		Set<String> targetAlbums = new HashSet<String>();
		Collection<Album> allAlbums = Artist.getTopAlbums(artist, key);
		for(Album album : allAlbums) {
			Album alb = Album.getInfo(album.getArtist(), album.getMbid(), key);
			if(alb != null) {
				for(Track t : alb.getTracks()) {
					if(t.getName().toLowerCase().equals(title.toLowerCase())) {
						targetAlbums.add(alb.getName());
					}
				}
			}
		}
		return targetAlbums;
	}
	
	public Collection<Track> findTracks(String title, String artist) {
		Collection<Track> tracks = Artist.getTopTracks(artist, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + artist);
			}
		}
		return tracks;
	}
	
	public Collection<Track> findTracks(String title) {
		Collection<Track> tracks = Track.search(title, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + track.getArtist());
			}
		}
		return tracks;
	}
	
	public int getTrackNumber(String title, String artist, String album) {
		Track track = findTrack(title, artist, album);
		Album alb = Album.getInfo(artist, track.getAlbumMbid(), key);
		int i = 1;
		for(Track t : alb.getTracks()) {
			if(t.getName().equals(title)) {
				return i;
			}
			i++;
		}
		return 0;
	}
	
	public Track findTrack(String title, String artist, String album) {
		Collection<Track> tracks = Artist.getTopTracks(artist, key);
		for(Track track : tracks) {
			if(track.getName().equals(title) && track.getAlbum().equals(album)) {
				System.out.println("Found - " + title + " by " + artist);
				return track;
			}
		}
		System.out.println("No track found - " + title + " by " + artist);
		return null;
	}
	
	public Track findTrack(String title, String artist) {
		Collection<Track> tracks = Artist.getTopTracks(artist, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + artist);
				return track;
			}
		}
		System.out.println("No track found - " + title + " by " + artist);
		return null;
	}
	
	public Track findTrack(String title) {
		Collection<Track> tracks = Track.search(title, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + track.getArtist());
				return track;
			}
		}
		System.out.println("No track found - " + title);
		return null;
	}
}
