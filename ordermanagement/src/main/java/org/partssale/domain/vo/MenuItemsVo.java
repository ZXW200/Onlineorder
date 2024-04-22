package org.partssale.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ExcelIgnoreUnannotated
public class MenuItemsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 名字
     */
    @ExcelProperty(value = "名字")
    private String name;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 价格
     */
    @ExcelProperty(value = "价格")
    private BigDecimal price;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类")
    private String category;

    /**
     * 可用数量
     */
    @ExcelProperty(value = "可用数量")
    private Integer available;


}
