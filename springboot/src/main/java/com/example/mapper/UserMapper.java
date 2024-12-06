package com.example.mapper;

import com.example.entity.Account;
import com.example.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作user相关数据接口
*/
public interface UserMapper {

    /**
      * 新增
    */
    int insert(User user);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(User user);

    /**
      * 根据用户姓名查询
     */
    @Select("select * from user where username = #{username}")
    User selectByUserName(String username);

    /**
      * 查询所有
    */
    List<User> selectAll(User user);

    /**
      * 根据id查询
    */
    User selectById(Integer id);

    /**
      * 更该密码
    */
    void updatePassword(Account account);

}