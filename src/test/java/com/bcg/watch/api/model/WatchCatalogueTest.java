package com.bcg.watch.api.model;

import com.bcg.watch.api.exception.WatchIdAlreadyExistsException;
import com.bcg.watch.api.exception.WatchNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WatchCatalogueTest {

    private static WatchCatalogue watchCatalogue;
    private static final List<Watch> expectedWatches = new ArrayList<Watch>() {{
        add(new Rolex());
        add(new MichaelKors());
        add(new Swatch());
        add(new Casio());
    }};

    @BeforeClass
    public static void setup() {
        try {
            watchCatalogue = WatchCatalogue.getInstance();
        } catch (WatchIdAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetWatchById() {
        try {
            compareWatchDetails(watchCatalogue.getWatchById("001"), expectedWatches.get(0));
            compareWatchDetails(watchCatalogue.getWatchById("002"), expectedWatches.get(1));
            compareWatchDetails(watchCatalogue.getWatchById("003"), expectedWatches.get(2));
            compareWatchDetails(watchCatalogue.getWatchById("004"), expectedWatches.get(3));
        } catch (WatchNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllWatches() {
        List<Watch> watches = watchCatalogue.getAllWatches();
        assertEquals(watches.size(), expectedWatches.size());
        for (int i = 0; i < watches.size(); i++) {
            compareWatchDetails(watches.get(i), expectedWatches.get(i));
        }
    }

    private void compareWatchDetails(Watch actual, Watch expected) {
        assertEquals(actual.id, expected.id);
        assertEquals(actual.name, expected.name);
        assertEquals(actual.price, expected.price);
    }

}