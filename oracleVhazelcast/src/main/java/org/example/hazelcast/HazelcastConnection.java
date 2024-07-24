package org.example.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HazelcastConnection {

    public static void main(String[] args) {
        String hazelcastServerIp = "172.20.224.1";
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(hazelcastServerIp + ":5701");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Integer, Integer> map = client.getMap("my-distributed-map");

        int n = 100000;

        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        putRandomNumbers(map, n, executor);
        getAllNumbers(map, n, executor);

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        client.shutdown();
    }

    private static void putRandomNumbers(IMap<Integer, Integer> map, int n, ExecutorService executor) {
        Random random = new Random();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            final int index = i;
            executor.submit(() -> {
                int randomNumber = random.nextInt(n) + 1;
                map.put(index, randomNumber);
            });
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Inserted " + n + " random integers between 1 and " + n);
        System.out.println("Time taken to put: " + duration + " milliseconds");
    }

    private static void getAllNumbers(IMap<Integer, Integer> map, int n, ExecutorService executor) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            final int index = i;
            executor.submit(() -> map.get(index));
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Retrieved " + n + " integers");
        System.out.println("Time taken to get: " + duration + " milliseconds");
    }
}
