package com.netdimen.config;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Function;
import com.netdimen.abstractclasses.TestObject;

public final class Resources {

	private static final Map<Locale, ResourceBundleImpl> bundleMap = new ConcurrentHashMap<Locale, ResourceBundleImpl>();

	private Resources() {

		throw new AssertionError();
	}

	public static final ResourceBundleImpl bundle(final Locale locale) {

		final Locale nonNullLocale = TestObject.defaultIfNull(locale,
				Locale.getDefault());

		synchronized (bundleMap) {
			if (bundleMap.containsKey(nonNullLocale))
				return bundleMap.get(nonNullLocale);
			else {
				final ResourceBundleImpl result = new ResourceBundleImpl(
						nonNullLocale);
				bundleMap.put(nonNullLocale, result);
				return result;
			}
		}
	}

	public static final String string(final String key, final Locale locale) {

		return bundle(locale).getString(key);
	}

	public static final String string(final String key, final Locale locale,
			final Object... parameters) {

		return new MessageFormat(string(key, locale)).format(parameters);
	}

	public static final Locale locale(final String countryCode) {

		final Locale[] locales = Locale.getAvailableLocales();
		for (int i = 0; i < locales.length; i++)
			if (locales[i].getISO3Country().equalsIgnoreCase(countryCode))
				return locales[i];
		return Locale.getDefault();
	}

	public static final Function<Locale, String> message(final String key) {

		return new Function<Locale, String>() {

			@Override
			public final String apply(final Locale locale) {

				return string(key, locale);
			}
		};
	}

}
