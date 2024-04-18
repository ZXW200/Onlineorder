package org.partssale.service.impl;


import org.partssale.domain.dto.MenuItem;
import org.partssale.mapper.MenuItemMapper;
import org.partssale.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemMapper menuItemMapper;

    @Override
    public List<MenuItem> findAll() {
        return menuItemMapper.findAll();
    }

    @Override
    public MenuItem findById(Long id) {
        return menuItemMapper.findById(id);
    }

    @Override
    public void save(MenuItem menuItem) {
        if (menuItem.getId() == null) {
            menuItemMapper.insert(menuItem);
        } else {
            menuItemMapper.update(menuItem);
        }
    }

    @Override
    public void delete(Long id) {
        menuItemMapper.delete(id);
    }
}
