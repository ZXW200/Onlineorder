package org.partssale.service;


import org.partssale.domain.dto.Table;

import java.util.List;

public interface TableService {
    List<Table> findAll();
    Table findById(Long id);
    void create(Table table);
    void update(Table table);
    void delete(Long id);
}

