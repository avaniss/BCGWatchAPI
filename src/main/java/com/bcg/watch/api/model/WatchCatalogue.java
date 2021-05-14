package com.bcg.watch.api.model;

import com.bcg.watch.api.exception.WatchIdAlreadyExistsException;
import com.bcg.watch.api.exception.WatchNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WatchCatalogue {

    private static WatchCatalogue watchCatalogue = null;
    private final Map<String, Watch> watches;

    private WatchCatalogue() {
        watches = new HashMap<>();
    }

    private void addWatch(String watchId, Watch watch) throws WatchIdAlreadyExistsException {
        if (watches.containsKey(watchId))
            throw new WatchIdAlreadyExistsException(watchId);
        watches.put(watchId, watch);
    }

    public Watch getWatchById(String watchId) throws WatchNotFoundException {
        Watch watch = watches.get(watchId);
        if (watch == null) {
            throw new WatchNotFoundException(watchId);
        }
        return watch;
    }

    public List<Watch> getAllWatches() {
        return new ArrayList<>(watches.values());
    }

    public static synchronized WatchCatalogue getInstance() throws WatchIdAlreadyExistsException {
        if (watchCatalogue == null) {

            watchCatalogue = new WatchCatalogue();

            Rolex rolex = new Rolex();
            watchCatalogue.addWatch(rolex.id, rolex);

            MichaelKors michaelKors = new MichaelKors();
            watchCatalogue.addWatch(michaelKors.id, michaelKors);

            Swatch swatch = new Swatch();
            watchCatalogue.addWatch(swatch.id, swatch);

            Casio casio = new Casio();
            watchCatalogue.addWatch(casio.id, casio);
        }
        return watchCatalogue;
    }
}
