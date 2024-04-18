package org.partssale.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单详细视图对象 order_details
 *
 * @author ruoyi
 * @date 2023-12-01
 */
@Data
@ExcelIgnoreUnannotated
public class OrderDetailsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 订单id
     */
    @ExcelProperty(value = "订单id")
    private Long orderId;

    /**
     * 菜单项id
     */
    @ExcelProperty(value = "菜单项id")
    private Long menuItemId;

    /**
     * 质量
     */
    @ExcelProperty(value = "质量")
    private Long quantity;

    /**
     * 价格
     */
    @ExcelProperty(value = "价格")
    private BigDecimal price;


}
