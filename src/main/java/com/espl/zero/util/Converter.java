package com.espl.zero.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Converter {
	public static final <T> List<T> convertToList(T[] tArray) {
		List<T> list = new ArrayList<T>();
		for (T t : tArray) {
			list.add(t);
		}
		return list;
	}
	
}
