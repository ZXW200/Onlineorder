package org.partssale.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.partssale.utils.OrderStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Order implements Serializable {
    private String id;
    private String userId;
    private Long tableId;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    private OrderStatus status;

}
