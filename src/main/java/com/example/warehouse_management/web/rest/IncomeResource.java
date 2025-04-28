package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.service.IncomeService;
import com.example.warehouse_management.service.dto.IncomeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeResource {
    private final IncomeService incomeService;

    public IncomeResource(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody IncomeDto incomeDto) throws URISyntaxException {
        IncomeDto income = incomeService.create(incomeDto);
        return ResponseEntity.created(new URI("/api/income/create" + income.getId())).body(income);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody IncomeDto incomeDto) throws URISyntaxException {
        if (!incomeDto.getId().equals(id) && incomeDto.getId() != 0) {
            return ResponseEntity.notFound().build();
        }
        IncomeDto result = incomeService.update(id,incomeDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<IncomeDto> result = incomeService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        IncomeDto result = incomeService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/allActive")
    public ResponseEntity<?> activeIncome() {
        List<IncomeDto> result = incomeService.findByActiveIncome();
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Income result = incomeService.delete(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        Long count = incomeService.count();
        return ResponseEntity.ok().body(count);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestIncomes(@RequestParam(defaultValue = "10") int limit) {
        List<IncomeDto> result = incomeService.getLatestIncomes(limit);
        return ResponseEntity.ok().body(result);
    }
}
