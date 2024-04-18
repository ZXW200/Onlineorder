package org.partssale.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.partssale.domain.BaseEntity;
import org.partssale.utils.AddGroup;
import org.partssale.utils.EditGroup;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationsBo extends BaseEntity {

    /**
     * 预订时间
     */
    @NotNull(message = "预订时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Timestamp reservationTime;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
