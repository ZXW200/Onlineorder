package org.partssale.controller;

import org.partssale.domain.dto.Table;
import org.partssale.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public ResponseEntity<List<Table>> getAllTables() {
        return ResponseEntity.ok(tableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Table> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        tableService.create(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(table);
    }

    @PutMapping
    public ResponseEntity<Void> updateTable(@RequestBody Table table) {
        tableService.update(table);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.delete(id);
        return ResponseEntity.ok().build();
    }
}
