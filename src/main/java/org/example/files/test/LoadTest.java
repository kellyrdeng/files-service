package org.example.files.test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class LoadTest {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request  = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/status"))
            .GET()
            .build();

        TestThread[] threads = new TestThread[50];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TestThread("thread" + i, client, request);
            threads[i].start();
        }

        System.out.println("Waiting for threads to finish...");
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }
}

class TestThread extends Thread {
    private final String name;
    private final HttpClient client;
    private final HttpRequest request;

    public TestThread(String name, HttpClient client, HttpRequest request) {
        this.name = name;
        this.client = client;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                String body = response.body();
                System.out.println(body);
            }
            System.out.println(name + " is done.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
