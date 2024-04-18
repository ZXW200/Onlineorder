package org.partssale.controller;


import org.partssale.domain.dto.MenuItem;
import org.partssale.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        MenuItem menuItem = menuItemService.findById(id);
        return menuItem != null ? ResponseEntity.ok(menuItem) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        menuItemService.save(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    @PutMapping
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuItem menuItem) {
        System.out.println(menuItem);
        MenuItem existingMenuItem = menuItemService.findById(Long.valueOf(menuItem.getId()));
        if (existingMenuItem != null) {
//            menuItem.setId(id);
            menuItemService.save(menuItem);
            return ResponseEntity.ok(menuItem);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemService.findById(id);
        if (menuItem != null) {
            menuItemService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            System.out.println("The file is empty");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        File directory = new File("D:\\temp-rainy");
        if (!directory.exists()){
            directory.mkdirs(); // This will create the directory including any necessary but nonexistent parent directories.
        }
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);
        return fileName;
    }
}
