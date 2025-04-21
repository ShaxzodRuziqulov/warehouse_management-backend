package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.service.WareHouseService;
import com.example.warehouse_management.service.dto.WareHouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WareHouseResource {
    private final WareHouseService wareHouseService;

    public WareHouseResource(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody WareHouseDto wareHouseDto) throws URISyntaxException {
        WareHouseDto result = wareHouseService.create(wareHouseDto);
        return ResponseEntity.created(new URI("/api/order/create")).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody WareHouseDto wareHouseDto, @PathVariable Long id) throws URISyntaxException {
        if (wareHouseDto.getId().equals(id) && wareHouseDto.getId() != 0) {
            return ResponseEntity.badRequest().body("invalid id");
        }
        WareHouseDto result = wareHouseService.update(wareHouseDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<WareHouseDto> result = wareHouseService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        WareHouseDto result = wareHouseService.findById(id);
        return ResponseEntity.ok().body(result);
    }
}
