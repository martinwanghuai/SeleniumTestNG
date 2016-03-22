package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * use to save properties as text file
 * 
 * @author lester.li
 *
 */
public class PropertiesFileUtils {

	private PropertiesFileUtils() {
		throw new AssertionError();
	}

	public static boolean SaveAsPropertiesFile(final String fullFileName,
			final Map<String, String> table) {
		final Properties prop = new Properties();
		OutputStream output = null;
		final boolean saveSucccess = false;
		try {

			output = new FileOutputStream(fullFileName, false);
			// set the properties value
			for (final Iterator<String> iter = table.keySet().iterator(); iter
					.hasNext();) {
				final String key = iter.next();
				final String value = table.get(key);
				prop.setProperty(key, value);
			}

			// save properties to project root folder
			prop.store(output, null);

		} catch (final IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.flush();
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}
		return saveSucccess;
	}

	public static Properties loadProperties(final String fullFileName) {
		final Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(fullFileName);
			// load a properties file
			prop.load(input);

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
		return prop;
	}
}
