package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yucong.entity.Man;

public interface ManMapper {

	List<Man> selectById(@Param("id") int id);

	List<Man> selectManAndWifeById(@Param("id") int id);

	List<Man> selectManAndWifeById1(@Param("id") int id);

	List<Man> selectAddressOfMan(@Param("id") int id);

	List<Man> selectAddressOfMan1(@Param("id") int id);

}
