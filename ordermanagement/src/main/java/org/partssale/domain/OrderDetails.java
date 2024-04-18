package org.partssale.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("order_details")
public class OrderDetails extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 菜单项id
     */
    private Long menuItemId;
    /**
     * 质量
     */
    private Long quantity;
    /**
     * 价格
     */
    private BigDecimal price;

}
