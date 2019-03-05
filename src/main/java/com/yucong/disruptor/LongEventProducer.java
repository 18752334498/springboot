package com.yucong.disruptor;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(long value) throws Exception {
		// 获取下一个序号
		long sequence = ringBuffer.next();
		try {
			Thread.sleep(3000);
			LongEvent event = ringBuffer.get(sequence);// 根据序号获取事件对象；
			event.setValue(value);
		} finally {
			// 发布序列号，发布以后可以被消费
			ringBuffer.publish(sequence);// 发布事件；
		}
	}

}
