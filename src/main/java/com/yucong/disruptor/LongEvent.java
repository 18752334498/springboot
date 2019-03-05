package com.yucong.disruptor;

public class LongEvent {

	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public LongEvent() {
	}

	public LongEvent(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[获取：" + this.value + "]";
	}

}
