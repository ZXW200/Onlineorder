package org.partssale.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.partssale.domain.BaseEntity;
import org.partssale.utils.AddGroup;
import org.partssale.utils.EditGroup;

import javax.validation.constraints.*;
import java.util.Date;



@Data
@EqualsAndHashCode(callSuper = true)
public class OrdersBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 餐桌id
     */
    private Long tableId;

    /**
     * 订单时间
     */
    @NotNull(message = "订单时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date orderTime;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
