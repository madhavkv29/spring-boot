package com.dataeconomy.controller;

import com.dataeconomy.ItemRepository;
import com.dataeconomy.model.Item;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class ItemDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDataController.class);
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path = "/items")
    public List<Item> fetchItems() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();;
        LOGGER.info(" DB call to find all Items " + Instant.now());
        List<Item> items = itemRepository.findAll();
        LOGGER.info(" DB call to find all Items finsished in " + stopWatch.getTime() + " msec");
        return items;
    }
}
