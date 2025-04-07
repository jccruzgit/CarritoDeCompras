package com.shopcart.productservice.service;

import com.shopcart.productservice.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RestTemplate restTemplate;

    @Value("${external.fakestore.base-url}")
    private String baseUrl;

    public List<ProductDTO> getAllProducts() {
        ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(baseUrl + "/products", ProductDTO[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }

    public ProductDTO getProductById(Long productId) {
        return restTemplate.getForObject(baseUrl + "/products/{id}" , ProductDTO.class, productId);

    }
}
