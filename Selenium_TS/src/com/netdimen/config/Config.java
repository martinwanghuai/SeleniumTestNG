package com.netdimen.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.netdimen.utils.Checker;
import com.netdimen.utils.MapFormatUtils;

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

	private final Properties ekpProperties;

	private final Properties allProperties;

	private static final Config instance = new Config();

	private final Locale defaultLocale = new Locale("en");
	private Locale userLocale;

	private ResourceBundleImpl userLocaleBundle;

	public void setUserLocale(final Locale newLocale) {
		this.userLocale = newLocale;
		userLocaleBundle = new ResourceBundleImpl(this.userLocale);
	}

	public Locale getUserLocale() {
		return this.userLocale;
	}

	public static Config getInstance() {
		return instance;
	}

	public String getProperty(final String key) {
		final String value = allProperties.getProperty(key);
		if (Checker.isBlank(value)) {
			if (Checker.isBlank(this.userLocaleBundle.getString(key))) {
				return "Cannot find the property value with key=" + key;
			}
			return this.userLocaleBundle.getString(key);
		} else {
			return value;
		}
	}

	public void setProperty(final String key, final String value) {
		allProperties.setProperty(key, value);
	}

	private static void loadProperties(final Properties prop, final String sProperties) {

		InputStream input = null;
		try {
			input = new FileInputStream(sProperties);
			prop.load(input);

			if (!(prop.getProperty("test.report.dir") == null)) {
				final Map<String, String> map = new HashMap<String, String>();
				map.put("IP", prop.getProperty("IP"));
				map.put("port", prop.getProperty("port"));
				map.put("domain", prop.getProperty("domain"));
				map.put("configDir", prop.getProperty("configDir"));
				map.put("resourceDir", prop.getProperty("resourceDir"));
				map.put("test.report.dir", prop.getProperty("test.report.dir"));
				map.put("screenShotDir", prop.getProperty("screenShotDir"));
				map.put("skikuliDir", prop.getProperty("skikuliDir"));
				map.put("tomcatDir", System.getenv("CATALINA_HOME")
						+ "/webapps");
				map.put("ImplicitWait_millis",
						prop.getProperty("ImplicitWait_millis"));
				map.put("ExplicitWait_millis",
						prop.getProperty("ExplicitWait_millis"));
				map.put("HighlightElement_millis",
						prop.getProperty("HighlightElement_millis"));
				map.put("dateFormat", prop.getProperty("dateFormat"));
				prop.setProperty("baseURL",
						MapFormatUtils.format(prop.getProperty("baseURL"), map));
				map.put("baseURL", prop.getProperty("baseURL"));

				// replace the {baseURL} with = prop.getProperty("baseURL") in
				// loginURL property
				for (final Iterator iter = prop.keySet().iterator(); iter.hasNext();) {
					final String key = (String) iter.next();
					prop.setProperty(key,
							MapFormatUtils.format(prop.getProperty(key), map));
				}
				if (Boolean.getBoolean(prop.getProperty("enableHighlighter"))) {
					enableHighlighter = true;
				}
				if (Boolean.getBoolean(prop.getProperty("DEBUG_MODE"))) {
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
		ekpProperties = new Properties();
		testingProperties = new Properties();
		allProperties = new Properties();
		loadProperties(testingProperties, "./conf/config.properties");
		allProperties.putAll(testingProperties);

		loadProperties(ekpProperties, getProperty("ekp.properties"));
		allProperties.putAll(ekpProperties);
		setUserLocale(defaultLocale);
	}

}
