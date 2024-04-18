package org.partssale.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tables")
public class Tables extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 数量
     */
    private Long number;
    /**
     * 座位数量
     */
    private Long seatingCapacity;
    /**
     * 状态
     */
    private String status;

}
