package org.partssale.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.partssale.domain.BaseEntity;
import org.partssale.utils.AddGroup;
import org.partssale.utils.EditGroup;

import javax.validation.constraints.*;
import java.math.BigDecimal;



@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDetailsBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
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
    @NotNull(message = "质量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long quantity;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal price;


}
