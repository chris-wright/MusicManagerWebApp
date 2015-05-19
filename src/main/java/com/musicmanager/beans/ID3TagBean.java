package com.musicmanager.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v2_2;

import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;
import com.musicmanager.objects.MP3FileObject;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;

@ManagedBean(name = "id3Bean")
@SessionScoped
public class ID3TagBean {

	private List<MP3FileObject> taglessSongs;
	private MP3FileObject selectedSong;
	private LastFMActions lastFMActioner;
	private MP3Actions mp3Actions;
	private boolean showPanel;
	private boolean showEnrichmentPanel;
	private String filename;
	private String title;
	private String artist;
	private String album;
	private Set<String> currentAlbums;
	private Set<String> currentArtists;

	private int step;

	public ID3TagBean() {
		taglessSongs = new ArrayList<MP3FileObject>();
		mp3Actions = new MP3Actions();
		lastFMActioner = new LastFMActions();
		step = 1;
	}

	public void doEnrichment(MP3FileObject file) {
		filename = file.getFilename();
		title = file.getTitle();
		artist = file.getArtist();
		album = file.getAlbum();
		selectedSong = file;
		showPanel = !showPanel;
		showEnrichmentPanel = !showEnrichmentPanel;
		//file = new MP3File("");
	}

	public void previousStep() {
		setStep(getStep() - 1);
	}

	public void nextStep() {
		setStep(getStep() + 1);
		if(getStep() == 3) {
			setCurrentArtists(getArtistsForCurrentTitle());
		}
		if(getStep() == 4) {
			setCurrentAlbums(buildCurrentAlbums());
		}
	}
	
	public void submit() {
		if(!selectedSong.getFile().hasID3v2Tag()) {
			selectedSong.getFile().setID3v2Tag(new ID3v2_2());
		}
		AbstractID3v2 tag = selectedSong.getFile().getID3v2Tag();
		tag.setSongTitle(title);
		tag.setAlbumTitle(album);
		tag.setLeadArtist(artist);
		tag.setTrackNumberOnAlbum(String.valueOf(lastFMActioner.getTrackNumber(title, artist, album)));
		try {
			selectedSong.getFile().save();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		}
		togglePanel();
		showEnrichmentPanel = !showEnrichmentPanel;
		buildTaglessSongsList();
	}
	
	public void togglePanel() {
		this.showPanel = !showPanel;
	}

	public void chooseArtistOption(String artist) {
		setArtist(artist);
	}

	public void chooseAlbumOption(String album) {
		setAlbum(album);
	}

	public Set<String> buildCurrentAlbums() {
		Set<String> albumsWithTrack = lastFMActioner.findAlbumsWithTrack(artist, title);
		if(albumsWithTrack.isEmpty()) {
			return lastFMActioner.findAlbums(artist);
		} else {
			return albumsWithTrack;
		}
	}

	public Set<String> getArtistsForCurrentTitle() {
		return getArtistsForTitle(title);
	}

	public Set<String> getArtistsForTitle(String title) {
		Collection<Track> tracks = getTracksWithTitle(title);
		Set<String> artists = new HashSet<String>();
		Iterator<Track> trackIterator = tracks.iterator();
		while(trackIterator.hasNext()) {
			Track t = trackIterator.next();
			artists.add(t.getArtist());
		}
		return artists;
	}

	public Collection<Track> getTracksWithTitle(String title) {
		return lastFMActioner.findTracks(title);
	}

	public void buildTaglessSongsList() {
		showPanel = true;
		List<MP3File> files = mp3Actions.getFilesWithoutFullTags();
		for(MP3File file : files) {
			taglessSongs.add(new MP3FileObject(file));
		}
	}

	public List<MP3FileObject> getTaglessSongs() {
		return taglessSongs;
	}

	public void setTaglessSongs(List<MP3FileObject> taglessSongs) {
		this.taglessSongs = taglessSongs;
	}

	public MP3FileObject getSelectedSong() {
		return selectedSong;
	}

	public void setSelectedSong(MP3FileObject selectedSong) {
		this.selectedSong = selectedSong;
	}

	public boolean isShowPanel() {
		return showPanel;
	}

	public void setShowPanel(boolean showPanel) {
		this.showPanel = showPanel;
	}

	public MP3Actions getMp3Actions() {
		return mp3Actions;
	}

	public void setMp3Actions(MP3Actions mp3Actions) {
		this.mp3Actions = mp3Actions;
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

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public boolean isShowEnrichmentPanel() {
		return showEnrichmentPanel;
	}

	public void setShowEnrichmentPanel(boolean showEnrichmentPanel) {
		this.showEnrichmentPanel = showEnrichmentPanel;
	}

	public Set<String> getCurrentAlbums() {
		return currentAlbums;
	}

	public void setCurrentAlbums(Set<String> currentAlbums) {
		this.currentAlbums = currentAlbums;
	}

	public Set<String> getCurrentArtists() {
		return currentArtists;
	}

	public void setCurrentArtists(Set<String> currentArtists) {
		this.currentArtists = currentArtists;
	}
}
