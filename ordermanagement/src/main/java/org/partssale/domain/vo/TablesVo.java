package org.partssale.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 餐桌视图对象 tables
 *
 * @author ruoyi
 * @date 2023-12-01
 */
@Data
@ExcelIgnoreUnannotated
public class TablesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private Long number;

    /**
     * 座位数量
     */
    @ExcelProperty(value = "座位数量")
    private Long seatingCapacity;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;


}
