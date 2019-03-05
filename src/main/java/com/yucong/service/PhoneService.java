package com.yucong.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yucong.mapper.PhoneMapper;
import com.yucong.model.Phone;

import Utils.IDUtil;

@Service
public class PhoneService {

	private AtomicInteger count = new AtomicInteger(0);

	@Autowired
	private PhoneMapper phoneMapper;

	public String buy(long id, int count) {
		Phone phone = phoneMapper.selectByPhoneId(id);
		if (phone.getAmount() < count) {
			return "数量不足";
		}

		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("version", phone.getVersion());
		param.put("count", count);
		boolean flag = phoneMapper.updateByVersion(param);
		if (flag) {
			return "购买成功。。。。：" + this.count.incrementAndGet();
		}

		sleepSecond();

		return buy(id, count);
	}

	public String buyDirect(long id, int count) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("count", count);
		boolean flag = phoneMapper.buyDirect(param);
		if (flag) {
			return "购买成功。。。。：" + this.count.incrementAndGet();
		} else {
			return "购买失败";
		}
	}

	private void sleepSecond() {
		try {
			Thread.sleep(new Random().nextInt(10) + 1);
		} catch (Exception e) {

		}
	}

	public boolean insertBatch() {
		Phone p1 = new Phone(IDUtil.generateID(), "小米", 100, 0);
		Phone p2 = new Phone(IDUtil.generateID(), "荣耀", 150, 0);
		Phone p3 = new Phone(IDUtil.generateID(), "vivo", 567, 0);
		Phone p4 = new Phone(IDUtil.generateID(), "oppo", 421, 0);
		Phone p5 = new Phone(IDUtil.generateID(), "苹果", 321, 0);
		Phone p6 = new Phone(IDUtil.generateID(), "锤子", 234, 0);
		Phone p7 = new Phone(IDUtil.generateID(), "魅族", 12, 0);
		Phone p8 = new Phone(IDUtil.generateID(), "诺基亚", 44, 0);
		Phone p9 = new Phone(IDUtil.generateID(), "美图", 98, 0);
		Phone p10 = new Phone(IDUtil.generateID(), "摩托诺拉", 765, 0);
		Phone p11 = new Phone(IDUtil.generateID(), "黑莓", 435, 0);
		Phone p12 = new Phone(IDUtil.generateID(), "坚果", 96, 0);
		Phone p13 = new Phone(IDUtil.generateID(), "华为", 653, 0);
		Phone p14 = new Phone(IDUtil.generateID(), "努比亚", 123, 0);
		Phone p15 = new Phone(IDUtil.generateID(), "三星", 431, 0);
		Phone p16 = new Phone(IDUtil.generateID(), "索尼", 23, 0);
		Phone p17 = new Phone(IDUtil.generateID(), "中兴", 578, 0);
		Phone p18 = new Phone(IDUtil.generateID(), "联想", 90, 0);
		Phone p19 = new Phone(IDUtil.generateID(), "红米", 677, 0);
		Phone p20 = new Phone(IDUtil.generateID(), "天语", 32, 0);
		Phone p21 = new Phone(IDUtil.generateID(), "魅蓝", 78, 0);
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(p1);
		phoneList.add(p2);
		phoneList.add(p3);
		phoneList.add(p4);
		phoneList.add(p5);
		phoneList.add(p6);
		phoneList.add(p7);
		phoneList.add(p8);
		phoneList.add(p9);
		phoneList.add(p10);
		phoneList.add(p11);
		phoneList.add(p12);
		phoneList.add(p13);
		phoneList.add(p14);
		phoneList.add(p15);
		phoneList.add(p16);
		phoneList.add(p17);
		phoneList.add(p18);
		phoneList.add(p19);
		phoneList.add(p20);
		phoneList.add(p21);
		return phoneMapper.insertBatch(phoneList);
	}

	public List<Map<String, Object>> selectWithPage() {
		Map<String, Object> param = new HashMap<>();

		param.put("limit", 2);
		param.put("offset", 1);
		param.put("column", "amount");

		return phoneMapper.selectWithPage(param);
	}

	public List<Map<String, Object>> selectByPagination(Map<String, Object> map) {
		return phoneMapper.selectByPagination(map);
	}

	public int selectAll() {
		return phoneMapper.selectAll();
	}

	public Phone selectByPhoneId(long id) {
		return phoneMapper.selectByPhoneId(id);
	}

	/**
	 * 没有配置异步线程，会创建SimpleAsyncTaskExecutor， 只配置一个线程池，会默认使用那个，如果配置多个，需要指定名称
	 * @param param
	 */
	@Async("threadPoolB")
	@EventListener
	public void updateByName(Phone phone) {
		System.out.println("参数是： " + phone);
		System.out.println("线程名： " + Thread.currentThread().getName());
		phoneMapper.updateByName(phone);
	}

}
