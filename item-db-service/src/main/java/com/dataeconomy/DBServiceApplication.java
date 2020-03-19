package com.dataeconomy;

import com.dataeconomy.model.Item;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DBServiceApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DBServiceApplication.class);

    @Value("${items.insert.db}")
    private boolean isInsertRecords;
    @Value("${items.insert.db.count}")
    private int noOfRecords;

    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(DBServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("StartApplication...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if (isInsertRecords) {
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < noOfRecords; i++) {
                Item item = new Item();
                //  item.setId("Id"+i);
                item.setName("Name" + i);
                item.setCategory("Category" + i);
                item.setComments("Comments" + i);
                item.setLikeCount("" + (100 * i));
                item.setBrand("Brand " + i);
                item.setPrice("" + 101 * i / 10);
                item.setDescription("Description " + item.getName());
                item.setItemWeight("10");
                items.add(item);
                //  itemRepository.save(item);
            }
            itemRepository.saveAll(items);
        }else{
            log.info("records not inserted");
        }
        log.info("Inserted 50000 records took time in msec " + stopWatch.getTime());
        log.info(" size of items " + itemRepository.findAll().size());
    }

}