package com.example.abstractionizer.login.session1.db.rmdb.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByIdOrUserName(@Param("id") Integer id, @Param("user_name") String userName);

    int updateStatus(@Param("id") Integer id, @Param("status") boolean status);

    int updateUser( @Param("id") Integer id, @Param("user_name") String userName, @Param("password") String password,
                    @Param("email") String email, @Param("phone") String phone, @Param("last_login_time") Date date);
}
