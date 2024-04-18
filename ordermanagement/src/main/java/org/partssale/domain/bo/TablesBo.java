package org.partssale.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.partssale.domain.BaseEntity;
import org.partssale.utils.AddGroup;
import org.partssale.utils.EditGroup;

import javax.validation.constraints.*;



@Data
@EqualsAndHashCode(callSuper = true)
public class TablesBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
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
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
