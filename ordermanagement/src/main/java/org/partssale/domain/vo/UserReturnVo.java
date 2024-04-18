package org.partssale.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserReturnVo implements Serializable {

    private String id;
    private String role;
    private Timestamp addtime;
    private String token;

}
