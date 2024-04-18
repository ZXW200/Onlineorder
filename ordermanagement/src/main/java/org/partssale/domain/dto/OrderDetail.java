package org.partssale.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetail implements Serializable {
    private String id;
    private String orderId;
    private String menuItemId;
    private Integer quantity;
    private BigDecimal price;

}
