package org.partssale.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MenuItem implements Serializable {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Boolean available;
    private String productImage;


}
