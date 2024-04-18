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
public class MenuItemsBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 名字
     */
    @NotBlank(message = "名字不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal price;

    /**
     * 分类
     */
    @NotBlank(message = "分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String category;

    /**
     * 可用数量
     */
    @NotNull(message = "可用数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer available;


}
