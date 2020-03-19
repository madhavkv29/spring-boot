package com.dataeconomy.proessApp.controller;

import com.dataeconomy.proessApp.model.Item;
import com.dataeconomy.proessApp.service.ItemService;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/items/all")
    public List<Item> fetchItems() {
        LOGGER.info("  /items/all call in ItemController" + Instant.now());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();;
        List<Item> items = itemService.fetchAllItems();
        LOGGER.info(" /items/all Service call to fetchAllItems finished in " + stopWatch.getTime() + " msec");
        return items;
    }
    /*@RequestMapping(path = "/items/first",method = RequestMethod.GET)
    public Item fetchFirst() {
        return userRepository.firstUser();
    }
    @RequestMapping(path = "/items/",method = RequestMethod.GET)
    public Item findByFirstNameLastName(String firstName,String lastName ) {
        return userRepository.userByFirstNameAndLastName(firstName,lastName);
    }*/

}
