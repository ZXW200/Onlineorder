package org.partssale.service.impl;

import org.partssale.domain.dto.Table;
import org.partssale.mapper.TableMapper;
import org.partssale.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public List<Table> findAll() {
        return tableMapper.findAll();
    }

    @Override
    public Table findById(Long id) {
        return tableMapper.findById(id);
    }

    @Override
    public void create(Table table) {
        tableMapper.insert(table);
    }

    @Override
    public void update(Table table) {
        tableMapper.update(table);
    }

    @Override
    public void delete(Long id) {
        tableMapper.delete(id);
    }
}
