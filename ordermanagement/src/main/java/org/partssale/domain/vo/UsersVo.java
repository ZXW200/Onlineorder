package org.partssale.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图对象 users
 *
 * @author ruoyi
 * @date 2023-12-01
 */
@Data
@ExcelIgnoreUnannotated
public class UsersVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码")
    private String password;

    /**
     * 角色
     */
    @ExcelProperty(value = "角色")
    private String role;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date addtime;


}
