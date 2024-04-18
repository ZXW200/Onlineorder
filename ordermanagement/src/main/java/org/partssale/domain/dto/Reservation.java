package org.partssale.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.partssale.utils.ReservationStatus;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("reservations")
public class Reservation implements Serializable {
    @TableId
    private Long id;
    private String userId;
    private String tableId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reservationTime;
    private ReservationStatus status;


}
