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
public class UsersBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String password;

    /**
     * 角色
     */
    @NotBlank(message = "角色不能为空", groups = { AddGroup.class, EditGroup.class })
    private String role;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date addtime;


}
