package org.partssale.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@ExcelIgnoreUnannotated
public class FeedbackVo implements Serializable {

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
     * 订单id
     */
    @ExcelProperty(value = "订单id")
    private Long orderId;

    /**
     * 评论
     */
    @ExcelProperty(value = "评论")
    private String comment;

    /**
     * 评价
     */
    @ExcelProperty(value = "评价")
    private Long rating;

    /**
     * 反馈时间
     */
    @ExcelProperty(value = "反馈时间")
    private Date feedbackTime;


}
