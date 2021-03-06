package com.bcg.watch.api;

import com.bcg.watch.api.service.WatchCatalogueService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class WatchApiApplication extends Application {
    @SuppressWarnings("unchecked")
	public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(WatchCatalogueService.class));
    }
}
