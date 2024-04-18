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
public class FeedbackBo extends BaseEntity {

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
     * 订单id
     */
    private Long orderId;

    /**
     * 评论
     */
    @NotBlank(message = "评论不能为空", groups = { AddGroup.class, EditGroup.class })
    private String comment;

    /**
     * 评价
     */
    @NotNull(message = "评价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rating;

    /**
     * 反馈时间
     */
    @NotNull(message = "反馈时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date feedbackTime;


}
