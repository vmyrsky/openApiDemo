package com.org.product.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ordinary rest controller.
 */
@RestController
public class ExampleOfTraditionalRestController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World", required = false) String name) {
        System.out.println("Hello called");
        return String.format("Hello %s!", name);
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Entity json(@RequestParam(value = "name", defaultValue = "World", required = false) String name) {
        System.out.println("Json called");
        return new Entity("Hello", name);
    }

    private class Entity {
        private String message;
        private String value;

        public Entity(String message, String value) {
            this.message = message;
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("%1$s %2$s!", this.message, this.value);
        }
    }
}
