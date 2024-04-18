package org.partssale.mapper;

import org.apache.ibatis.annotations.*;
import org.partssale.domain.dto.User;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> findAllUsers();

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO users(username, password, role, addtime) VALUES(#{username}, #{password}, #{role}, #{addtime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE users SET username=#{username}, password=#{password}, role=#{role}, addtime=#{addtime} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void deleteUser(Long id);
//    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
//    int resetPassword(@Param("username") String username, @Param("password") String password);
}


