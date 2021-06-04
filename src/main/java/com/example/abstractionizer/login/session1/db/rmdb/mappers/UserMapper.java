package com.example.abstractionizer.login.session1.db.rmdb.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
