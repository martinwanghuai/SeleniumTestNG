package com.utils;

public final class Checker {

	private Checker() {
		throw new AssertionError();
	}

	public static final boolean isBlank(final String s) {

		return s == null || s.trim().equals("") || s.equalsIgnoreCase("null");
	}

	public static final boolean isNull(final String s) {

		return s == null || s.trim().equals("");
	}

	public static final boolean isEmpty(final Object[] s) {

		return s == null || s.length == 0;
	}
}
