package com.rit.devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class QuoteController {
    private final List<String> quotes = List.of(
        "Automate everything that you do more than twice.",
        "It works on my machine is not a deployment strategy.",
        "Write your own quote here"
    );

    @GetMapping("/quote")
    public Map<String, String> quote() {
        return Map.of("quote", quotes.get(ThreadLocalRandom.current().nextInt(quotes.size())));
    }

    @GetMapping("/quotes")
    public List<String> quotes() {
        return quotes;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }
}
