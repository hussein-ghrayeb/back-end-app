package com.solution.grapeApp.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.grapeApp.entities.Product;
import com.solution.grapeApp.repositories.ProductRepository;

public class ProductDeserialization extends JsonDeserializer<List<Product>> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        // TODO Auto-generated method stub
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        String stringArray = objectMapper.writeValueAsString(root);
        List<Product> productList = objectMapper.readValue(stringArray, new TypeReference<List<Product>>() {
        });
        List<Product> products = new ArrayList<>();
        productList.forEach(product -> {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
            products.add(optionalProduct.get());
        });
        return products;
    }

}
