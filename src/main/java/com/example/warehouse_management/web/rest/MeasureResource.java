package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.entity.Measure;
import com.example.warehouse_management.service.MeasureService;
import com.example.warehouse_management.service.dto.MeasureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measure")
public class MeasureResource {
    private final MeasureService measureService;

    public MeasureResource(MeasureService measureService) {
        this.measureService = measureService;
    }

    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody MeasureDto measureDto) {
        MeasureDto result = measureService.create(measureDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update/{id}")
    private ResponseEntity<?> updateMeasure(@RequestBody MeasureDto measureDto, @PathVariable Long id) {
        if (!measureDto.getId().equals(id) && measureDto.getId() != 0) {
            return ResponseEntity.badRequest().body(measureDto);
        }
        MeasureDto result = measureService.update(measureDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findByList() {
        List<MeasureDto> result = measureService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Measure result = measureService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        measureService.deleteById(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
