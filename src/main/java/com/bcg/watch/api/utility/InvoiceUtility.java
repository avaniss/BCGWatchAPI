package com.bcg.watch.api.utility;

import com.bcg.watch.api.exception.WatchCatalogueException;
import com.bcg.watch.api.exception.WatchOrderRequestParserException;
import com.bcg.watch.api.model.Invoice;
import com.bcg.watch.api.model.Watch;
import com.bcg.watch.api.model.WatchCatalogue;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceUtility {

    public static Invoice generateInvoice(InputStream inputStream) throws WatchCatalogueException {
        List<String> orders = parseOrderRequest(inputStream);
        Map<String, Integer> orderedWatches = new HashMap<>();
        for (String watchId : orders) {
            orderedWatches.put(watchId, orderedWatches.getOrDefault(watchId, 0) + 1);
        }
        return new Invoice(calculateTotalPrice(orderedWatches));
    }

    public static List<String> parseOrderRequest(InputStream inputStream) throws WatchOrderRequestParserException {
        List<String> order = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            order = new ObjectMapper().readValue(inputStream, collectionType);
        } catch (IOException e) {
            throw new WatchOrderRequestParserException();
        }
        return order;
    }

    private static int calculateTotalPrice(Map<String, Integer> orderedWatches) throws WatchCatalogueException {
        int totalPrice = 0;
        WatchCatalogue watchCatalogue = WatchCatalogue.getInstance();
        for (Map.Entry<String, Integer> orderedWatch : orderedWatches.entrySet()) {
            String watchId = orderedWatch.getKey();
            int quantity = orderedWatch.getValue();
            Watch watch = watchCatalogue.getWatchById(watchId);
            totalPrice += watch.getDiscountedPrice(quantity);
        }
        return totalPrice;
    }
}
