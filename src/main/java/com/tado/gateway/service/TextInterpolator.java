package com.tado.gateway.service;

public final class TextInterpolator {

	private TextInterpolator() {
	}
	
	public static String interpolateUri(final String str, final String[] keys, final Long... values) {
		
		String path = str;
		
		for (int i = 0; i < keys.length; i++) {
			path = path.replace(keys[i], String.valueOf(values[i]));
		}
		
		return path;
	}
}
