package com.hilmatrix.fresh_goodies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class JsonData {
    public List<Map<String, Object>> products;
    public List<Map<String, Object>> cart;

    public static JsonData instance = null;

    @PostConstruct
    public void init() throws IOException {
        instance = this;

        ClassPathResource resource = new ClassPathResource("static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = resource.getInputStream()) {
            Map<String, List<Map<String, Object>>> jsonData = objectMapper.readValue(inputStream,
                    new TypeReference<Map<String, List<Map<String, Object>>>>() {
                    });
            this.products = jsonData.get("products");
        }

        try (InputStream inputStream = resource.getInputStream()) {
            Map<String, List<Map<String, Object>>> jsonData = objectMapper.readValue(inputStream,
                    new TypeReference<Map<String, List<Map<String, Object>>>>() {
                    });
            this.cart = jsonData.get("cart");
        }
    }
}
