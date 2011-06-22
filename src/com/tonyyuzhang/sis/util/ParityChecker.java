package com.tonyyuzhang.sis.util;

public class ParityChecker {
	public byte checksum(byte[] data) {
		byte checksum = data[0];
		for(int i = 1; i < data.length; i++)
			checksum ^= data[i];
		return checksum;
	}
}