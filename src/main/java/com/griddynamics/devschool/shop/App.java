package com.griddynamics.devschool.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author Sergey Korneev
 */
public class App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("Start");
        StoreService service = new StoreService(new Store(), new Scanner(System.in));
        service.run();
        logger.info("Stop");
    }
}
