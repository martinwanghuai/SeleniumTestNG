package com.netdimen.utils;

import java.io.File;

import org.sikuli.basics.ImageLocator;
import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

/**
 * 
 * @author martin.wang
 *
 */
public class SikuliUtils {
	static {
		final String fullPath = new File(System.getProperty("user.dir"),
				"images.sikuli").getAbsolutePath();
		ImageLocator.setBundlePath(fullPath);
		Settings.MinSimilarity = 0.9;
	}

	private SikuliUtils() {

		throw new AssertionError();
	}

	/**
	 * Find an image in a window with Sikuli
	 * 
	 * @param screenshotFile
	 * @return
	 */
	public static boolean screenshotExistInWin(final String screenshotFile) {

		boolean exist = false;
		try {
			final Region win = App.focusedWindow();

			final int debugLightSecs = 3; // set to 0 to switch off highlighting
			final Screen s = new Screen();

			final Pattern target = new Pattern(screenshotFile);
			target.setTimeAfter(50);
			target.similar(0.85f);

			final Match m = s.find(target);
			if (m != null) {
				m.highlight(debugLightSecs);
				exist = true;
			}
		} catch (final FindFailed e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return exist;
	}

}
