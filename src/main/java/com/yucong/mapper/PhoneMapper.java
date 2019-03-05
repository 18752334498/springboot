package com.yucong.mapper;

import java.util.List;
import java.util.Map;

import com.yucong.model.Phone;

public interface PhoneMapper {

	boolean insertBatch(List<Phone> phoneList);

	List<Map<String, Object>> selectWithPage(Map<String, Object> param);

	List<Map<String, Object>> selectByPagination(Map<String, Object> map);

	int selectAll();

	Phone selectByPhoneId(long id);

	boolean updateByVersion(Map<String, Object> param);

	boolean updateByName(Phone phone);

	boolean buyDirect(Map<String, Object> param);
}
