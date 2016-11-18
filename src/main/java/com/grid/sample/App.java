package com.grid.sample;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        long numberOfItems = getNumberOfItemsToGenerate(args);


        Random random = new Random();

        try (Ignite ignite = Ignition.start("ignite-config.xml")) {
            IgniteCache<Long, Long> cache = ignite.getOrCreateCache("example");

            System.out.printf("Generating %d items\n", numberOfItems);

            for (long i = 0; i < numberOfItems; ++i) {
                cache.put(i, random.nextLong());
            }

            System.out.println("Done");
        }

    }

    private static long getNumberOfItemsToGenerate(String[] args) {
        if (args.length != 1) {
            return 2_000_000;
        } else {
            return Long.parseLong(args[0]);
        }
    }

}
