package com.musicmanager.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import radams.gracenote.webapi.GracenoteException;
import radams.gracenote.webapi.GracenoteMetadata;
import radams.gracenote.webapi.GracenoteWebAPI;

public class GracenoteTest {

	@Test
	public void test() {
		try
        {
            /* You first need to register your client information in order to get a userID.
            Best practice is for an application to call this only once, and then cache the userID in
            persistent storage, then only use the userID for subsequent API calls. The class will cache
            it for just this session on your behalf, but you should store it yourself. */
            GracenoteWebAPI api = new GracenoteWebAPI("12672256-34C3D300D5E89570E2A1418A15A61E69", "34C3D300D5E89570E2A1418A15A61E69"); // If you have a userID, you can specify it as the third parameter to constructor.
            String userID = api.register();
            System.out.println("UserID = " + userID);

            // Once you have the userID, you can search for tracks, artists or albums easily.
            System.out.println("Search Track:");
            GracenoteMetadata results = api.searchTrack("", "", "Hallelujah");
            results.print();
            
            System.out.println("Search Artist:");
            results = api.searchArtist("Jeff Buckley");
            results.print();
            
            System.out.println("Search Album:");
            results = api.searchAlbum("Jeff Buckley", "Grace");
            results.print();
            
            
//
//            System.out.println("Search Artist:");
//            results = api.searchArtist("Moby");
//            results.print();
//
//            System.out.println("Search Album:");
//            results = api.searchAlbum("Moby", "Play");
//            results.print();
//
//            System.out.println("Fetch Album:");
//            results = api.fetchAlbum("97474325-8C600076B380712C6D1C5DC5DC5674F1");
//            results.print();
        }
        catch (GracenoteException e)
        {
            e.printStackTrace();
        }
	}

}
