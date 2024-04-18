package org.partssale.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Timestamp addtime;


}
