package com.dataeconomy.proessApp.service;

import com.dataeconomy.proessApp.model.Item;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    RestTemplate restTemplate;
    @Value("${item.dbservice.url}")
    String dbServiceUrl;

    @Cacheable("allitemscache")
    public List<Item> fetchAllItems(){
        LOGGER.info("fetchAllItems method entered " + Instant.now());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();;

        List<Item> items = restTemplate.getForObject(dbServiceUrl, List.class);
        LOGGER.info(" DB REST Service call finished fetchAllItems in " + stopWatch.getTime()+ " msec for " + items.size() +" size of Items ");
        return items;
    }

//    public List<Item> fetchItems(int recordCount){
//        List<Item> items = fetchAllItems();
//        items.
//    }
}
