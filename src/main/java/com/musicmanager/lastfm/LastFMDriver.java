package com.musicmanager.lastfm;

import java.text.DateFormat;
import java.util.Collection;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Chart;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;

public class LastFMDriver {
	public static void main(String[] args) {
		String key = "082af20fbd8d96480496d231e906da2a"; //this is the key used in the Last.fm API examples
		String user = "chrisanwright";
		Collection<Track> tracks = Artist.getTopTracks("Muse", key);
		System.out.println(tracks.size());
//		Chart<Artist> chart = User.getWeeklyArtistChart(user, 10, key);
//		DateFormat format = DateFormat.getDateInstance();
//		String from = format.format(chart.getFrom());
//		String to = format.format(chart.getTo());
//		System.out.printf("Charts for %s for the week from %s to %s:%n", user, from, to);
//		Collection<Artist> artists = chart.getEntries();
//		for (Artist artist : artists) {
//			System.out.println(artist.getName());
//		}
	}
}
