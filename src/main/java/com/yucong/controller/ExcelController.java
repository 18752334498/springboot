package com.yucong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.model.ExcelData;
import com.yucong.model.User;
import com.yucong.service.UserService;

import Utils.ExcelUtil;

@RestController
@RequestMapping("excel")
public class ExcelController {
	
	@Autowired
	private UserService userService;

	/**
	 * 导出到excel
	 */
	@RequestMapping("export")
	public void export(HttpServletResponse response, HttpServletRequest request) {
		List<User> userList = userService.getAllUsers();
		
		/**
		 * 这个for循环的业务和1到10000的累加业务一样简单，单线程会更快，如果每个业务都有1毫秒消耗，那么forjoin会更快
		 */
		List<String[]> list = new ArrayList<String[]>();
		String[] arrs = null;
		for (User user : userList) {
			arrs = new String[3];
			arrs[0] = String.valueOf(user.getId());
			arrs[1] = String.valueOf(user.getName());
			arrs[2] = String.valueOf(user.getAge());
			list.add(arrs);
		}

/*		ForkJoinPool pool = new ForkJoinPool(4); // 创建线程池
		ForkJoinUser task = new ForkJoinUser(userList, 0, userList.size()); // 实例化对象
		pool.submit(task); // 提交任务
		List<String[]> list = task.join(); // 阻塞线程获取结果
*/

		// 表头赋值
		String[] head = { "序列", "用户名", "密码" };
		ExcelData data = new ExcelData();
		data.setHead(head);
		data.setData(list);
		data.setFileName("dedede.xls");
		ExcelUtil.exportExcel(response, data);
	}

}
