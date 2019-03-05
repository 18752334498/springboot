package com.yucong.disruptor;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventTest2 {
	public static void main(String[] args) throws InterruptedException {
		ThreadFactory executor = Executors.defaultThreadFactory();
		LongEventFactory factory = new LongEventFactory();
		int ringBufferSize = 1024 * 1024;
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor,
				ProducerType.MULTI, new YieldingWaitStrategy());
		// 指定两个消费者并行
//		disruptor.handleEventsWith(new LongEventHandler1(), new LongEventHandler2());
		// 指定两个消费者串行
		disruptor.handleEventsWith(new LongEventHandler1()).handleEventsWith(new LongEventHandler2());
		disruptor.start();
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducer producer = new LongEventProducer(ringBuffer);

		// 模拟多线程，多线程下使用 SINGLE 会出错
		ExecutorService exec = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(10);
		for (int i = 0; i < 10; i++) {
			final int value = i;
			exec.submit(() -> {
				try {
					System.out.println(value + "  is ready");
					barrier.await();
					producer.onData(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

}
