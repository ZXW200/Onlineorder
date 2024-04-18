package org.partssale.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预定视图对象 reservations
 *
 * @author ruoyi
 * @date 2023-12-01
 */
@Data
@ExcelIgnoreUnannotated
public class ReservationsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 餐桌id
     */
    @ExcelProperty(value = "餐桌id")
    private Long tableId;

    /**
     * 预订时间
     */
    @ExcelProperty(value = "预订时间")
    private Date reservationTime;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;


}
