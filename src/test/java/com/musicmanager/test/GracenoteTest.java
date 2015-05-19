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
		try {
            GracenoteWebAPI api = new GracenoteWebAPI("12672256-34C3D300D5E89570E2A1418A15A61E69", "34C3D300D5E89570E2A1418A15A61E69"); // If you have a userID, you can specify it as the third parameter to constructor.
            String userID = api.register();
            System.out.println("UserID = " + userID);

            System.out.println("Search Track:");
            GracenoteMetadata results = api.searchTrack("", "", "Hallelujah");
            results.print();
            
            System.out.println("Search Artist:");
            results = api.searchArtist("Jeff Buckley");
            results.print();
            
            System.out.println("Search Album:");
            results = api.searchAlbum("Jeff Buckley", "Grace");
            results.print();
        } catch (GracenoteException e) {
            e.printStackTrace();
        }
	}

}
