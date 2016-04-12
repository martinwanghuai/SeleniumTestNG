package com.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.utils.Checker;
import com.utils.MapFormatUtils;

/**
 * @author lester.li This is config. class which is used to load all config.
 *         item with key value pair This is also a singleton class which has one
 *         instance only in whole system
 */
public class Config {

	public static boolean DEBUG_MODE = true;

	public static boolean enableHighlighter = false;

	public static boolean PRINTELEMENTNOTFOUNDMSG = false;

	public static String DELIMIT = "|";

	private static Properties testingProperties;

	private final Properties allProperties;

	private static final Config instance = new Config();

	private Locale userLocale;

	public Locale getUserLocale() {
		return this.userLocale;
	}

	public static Config getInstance() {
		return instance;
	}

	public String getProperty(final String key) {
		final String value = allProperties.getProperty(key);
		return Checker.isBlank(value) ? "Cannot find the property value with key="
				+ key
				: value;
	}

	public void setProperty(final String key, final String value) {
		allProperties.setProperty(key, value);
	}

	private static void loadProperties(final Properties prop, final String sProperties) {

		InputStream input = null;
		try {
			input = new FileInputStream(sProperties);
			prop.load(input);

			if (prop.getProperty("test.report.dir") != null) {

				Iterator<String> keySet = prop.stringPropertyNames().iterator();
				
				final ImmutableMap.Builder<String, String> mapBuilder = ImmutableMap
						.<String, String> builder();
				
				while(keySet.hasNext()){
					
					final String key = keySet.next();
					mapBuilder.put(key, prop.getProperty(key));
				}
				
				final Map<String, String> map = mapBuilder.build();
				for (final Iterator<String> iter = prop.stringPropertyNames().iterator(); iter.hasNext();) {
					final String key = (String) iter.next();
					prop.setProperty(key,
							MapFormatUtils.format(prop.getProperty(key), map));
				}
				
				if (Boolean.parseBoolean(prop.getProperty("enableHighlighter"))) {
					enableHighlighter = true;
				}
				
				if (Boolean.parseBoolean(prop.getProperty("DEBUG_MODE"))) {
					DEBUG_MODE = true;
				}
			}

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Config() {
		testingProperties = new Properties();
		allProperties = new Properties();
		loadProperties(testingProperties, "./conf/config.properties");
		allProperties.putAll(testingProperties);

	}

}
