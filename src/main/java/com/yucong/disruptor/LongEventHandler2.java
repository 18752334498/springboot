package com.yucong.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler2 implements EventHandler<LongEvent> {

	/**
	 * 发布到ringbuffer中的事件 
	 * 当前正在处理的事件序列号 
	 * 是否为ringbuffer中的最后一个
	 */
	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("2_" + Thread.currentThread().getName() + "\t" + event);

	}

}
