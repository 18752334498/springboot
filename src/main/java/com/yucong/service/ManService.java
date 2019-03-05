package com.yucong.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.entity.Man;
import com.yucong.mapper.ManMapper;

@Service
public class ManService {

	@Autowired
	private ManMapper manMapper;

	public List<Man> selectById(int id) {
		return manMapper.selectById(id);
	}

	public List<Man> selectManAndWifeById(int id) {
		return manMapper.selectManAndWifeById(id);
	}

	public List<Man> selectManAndWifeById1(int id) {
		return manMapper.selectManAndWifeById1(id);
	}

	public List<Man> selectAddressOfMan(@Param("id") int id) {
		return manMapper.selectAddressOfMan(id);
	}

	public List<Man> selectAddressOfMan1(@Param("id") int id) {
		return manMapper.selectAddressOfMan1(id);
	}

}
