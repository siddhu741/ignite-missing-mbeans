package com.example.ignitemissingmbeans;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IgniteMissingMbeansApplication {

    public static void main(String[] args) {

        SpringApplication.run(IgniteMissingMbeansApplication.class, args);

        // Start an ignite instance with default configuration and create a cache.
        IgniteConfiguration config = new IgniteConfiguration();
        Ignition.start(config);
        Ignite ignite = Ignition.ignite();
        ignite.getOrCreateCache("test-cache");
        System.out.println("-----------------------------");

        ignite.dataRegionMetrics().forEach(regionMetrics -> {
                    System.out.print(String.format("dataregionmetrics.%s.totalusedpages: ",
                                                   regionMetrics.getName().toLowerCase()));
                    System.out.println(regionMetrics.getTotalUsedPages());
                });
        System.out.println("-----------------------------");
    }

}
