package com.yucong.disruptor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventTest1 {
	public static void main(String[] args) {
		// 创建缓冲池
		ThreadFactory executor = Executors.defaultThreadFactory();
		// 创建工厂
		LongEventFactory factory = new LongEventFactory();
		// 创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
		int ringBufferSize = 1024 * 1024;
		
		

        //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
        //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
        //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		//序列的生成方式，单线程SINGLE，多线程MULTI,默认MULTI,多线程下用SINGLE会出现混乱

		// 创建disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor,
				ProducerType.SINGLE, new YieldingWaitStrategy());
		// 指定事件处理器
		disruptor.handleEventsWith(new LongEventHandler1());
		disruptor.setDefaultExceptionHandler(new DefaultEventHandler());
		// 启动，开启所有线程
		disruptor.start();

		// 获取ringbuffer
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		// 请求下一个事件序号；
		long sequence = ringBuffer.next();

		try {
			LongEvent event = ringBuffer.get(sequence);// 获取下一个序号对应的事件对象；
			event.setValue(100);
			System.out.println(event);
		} finally {
			// 发布序列号，发布以后可以被消费
			ringBuffer.publish(sequence);// 发布事件；
		}

		disruptor.shutdown();// 关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；

	}

}
