package com.yucong.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class DefaultEventHandler implements ExceptionHandler<LongEvent> {

	@Override
	public void handleEventException(Throwable ex, long sequence, LongEvent event) {
		System.out.println("handleEventException......");

	}

	@Override
	public void handleOnStartException(Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println("handleOnStartException......");

	}

	@Override
	public void handleOnShutdownException(Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println("handleOnShutdownException......");

	}

}
