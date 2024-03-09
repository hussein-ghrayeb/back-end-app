package com.solution.grapeApp.config;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.solution.grapeApp.entities.Product;

public class ProductSerialization extends JsonSerializer<List<Product>> {

    @Override
    public void serialize(List<Product> products, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        // TODO Auto-generated method stub
        jsonGenerator.writeStartArray();
        products.forEach(product -> {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("id", product.getId());
                jsonGenerator.writeStringField("englishName", product.getEnglishName());
                jsonGenerator.writeStringField("englishDescription", product.getEnglishDescription());
                jsonGenerator.writePOJOField("price", product.getPrice());
                jsonGenerator.writePOJOField("shelfAvailable", product.getShelfAvailable());
                jsonGenerator.writeBooleanField("isFavorite", product.getIsFavorite());
                jsonGenerator.writeStringField("imageUrl", product.getImageUrl());
                jsonGenerator.writeEndObject();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });
        jsonGenerator.writeEndArray();
    }

}
