package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.service.ProductService;
import com.example.warehouse_management.service.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) throws URISyntaxException {
        ProductDto result = productService.create(productDto);
        return ResponseEntity.created(new URI("/api/product/create" + result.getId())).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable Long id) {
        if (productDto.getId().equals(id) && productDto.getId() == 0) {
            return ResponseEntity.badRequest().body("invalid id");
        }
        ProductDto result = productService.update(productDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<ProductDto> result = productService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Products result = productService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Products result = productService.deleteById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        long products = productService.count();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/chart")
    public ResponseEntity<?> getProductChartData() {
        Map<String, Long> result = productService.getProductChartData();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveProducts() {
        List<ProductDto> result = productService.findActiveProducts();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getDeleted() {
        List<ProductDto> result = productService.findDeleteProducts();
        return ResponseEntity.ok().body(result);
    }

}
