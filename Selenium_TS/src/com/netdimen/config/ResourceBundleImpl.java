package com.netdimen.config;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringEscapeUtils;

import com.netdimen.utils.Checker;

public final class ResourceBundleImpl extends ResourceBundle {

	private final Map<String, Object> map = new HashMap<String, Object>();

	private final Locale locale;

	public ResourceBundleImpl(final Locale locale) {

		super();

		final ResourceBundle standardBundle = getBundle(
				"com.netdimen.locale.standard", locale);
		putAll(standardBundle);

		this.locale = standardBundle.getLocale();
	}

	private final void putAll(final ResourceBundle bundle) {

		for (final Enumeration<String> e = bundle.getKeys(); e
				.hasMoreElements();) {
			final String key = e.nextElement();
			map.put(key, bundle.getObject(key));
		}
	}

	@Override
	public final Enumeration<String> getKeys() {

		return Collections.enumeration(map.keySet());
	}

	@Override
	protected final Object handleGetObject(final String key) {

		return Checker.isBlank(key) ? "" : (map.containsKey(key) ? map
				.get(key) : key);
	}

	@Override
	public final Locale getLocale() {

		return locale;
	}

	public String getEscapedJavascriptString(final String key) {

		return StringEscapeUtils.escapeEcmaScript(getString(key));
	}

	public synchronized final void replace(final String key, final String value) {
		map.put(key, value);
	}
}
