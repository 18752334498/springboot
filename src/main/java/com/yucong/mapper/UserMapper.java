package com.yucong.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yucong.model.User;

public interface UserMapper {

    List<User> getAllUsers();

	boolean updateAgeById(@Param("id") int id);

	User selectUserById(@Param("id") int id);

	List<User> selectUsersByCondition(@Param("age") int age);

	Integer insertBatch(User user);

	Map<String, Object> selectMapById();

}
