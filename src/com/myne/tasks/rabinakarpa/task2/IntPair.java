package com.myne.tasks.rabinakarpa.task2;

public class IntPair {
	private int first, second;

	public IntPair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "IntPair [first=" + first + ", second=" + second + "]";
	}
	
	
}