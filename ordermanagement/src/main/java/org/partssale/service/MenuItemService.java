package org.partssale.service;

import org.partssale.domain.dto.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> findAll();
    MenuItem findById(Long id);
    void save(MenuItem menuItem); // For both insert and update
    void delete(Long id);
}
