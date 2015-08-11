package com.netdimen.annotation;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.junit.TestReport;
import com.netdimen.utils.Checker;

public class TimeLogger extends NetDTestWatcher {
	private WebDriver driver;

	public TimeLogger() {

	}

	public TimeLogger(final WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public boolean isSkipClass(final TestObject obj) {
		return false;
	}

	@Override
	public boolean isSkipMethod(final Method method) {

		boolean isSkipNeeded = false;
		if (method.isAnnotationPresent(Schedule.class)) {
			final Annotation annotation = method.getAnnotation(Schedule.class);
			final Schedule period = (Schedule) annotation;
			if (period.Monthly()) {
				isSkipNeeded = true; // turn on skip first, and if skip
										// condition matches, then skip it
				final DateFormat dateFormat = new SimpleDateFormat("dd");
				final Calendar cal = Calendar.getInstance();
				final int day = Integer.valueOf(dateFormat.format(cal.getTime()));
				// when day ==1, run the testing
				if (day == 10 || day == 20)
					isSkipNeeded = false;
			}
			if (period.Weekly()) {
				isSkipNeeded = true;// turn on skip first, and if skip condition
									// matches, then skip it
				final Calendar cal = Calendar.getInstance();
				final int day = cal.get(Calendar.DAY_OF_WEEK);
				// when day ==Sunday(1), run the testing
				if (day == 1)
					isSkipNeeded = false;
			}
			if (isSkipNeeded){
				System.out
						.println(method.getName()
								+ " is skipped on purpose because today is not its scheduled day either monthy = day 1, 20 or weekly = sunday");
			}
		}
		return isSkipNeeded;
	}

	public static String TCStartTime;
	public static String UIstatus;

	@Override
	public void start(final TestObject obj) {

		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		final Date date = new Date();
		TCStartTime = dateFormat.format(date);
	}

	@Override
	public void finished(final TestObject obj) {

		final String logdetails = this.retreiveEKPlog();
		
		if(!Checker.isBlank(logdetails)){
			final TestReport logger_testrpt = new TestReport(driver);
			logger_testrpt.SaveEKPErrToExcel(logdetails
					+ System.lineSeparator());
		}
	}

	@Override
	public void failed(final Throwable e, final TestObject obj) {
		UIstatus = "FAIL";
	}

	@Override
	public void succeeded(final TestObject obj) {
		UIstatus = "PASS";
	}

	private String retreiveEKPlog() {

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		final String TodayDate = TimeLogger.TCStartTime.substring(0, 11);
		String sEKPExpt = "";
		final File EKPLogFile = new File(Config.getInstance().getProperty("ekp.log"));
		
		try {
			final Scanner scnr = new Scanner(EKPLogFile).useDelimiter("\t");
			try {
				final Date startTime = sdf.parse(TimeLogger.TCStartTime);
				while (scnr.hasNextLine()) {
					String line = scnr.nextLine();
					
					if(!line.contains(TodayDate)){
						continue;
					}
					
					final Date logTime = sdf.parse(line.substring(0, 20));
					if(!logTime.after(startTime)){
						continue;
					}
					
					
					if (!line.contains("SYSTEM EXCEPTION")) {
						continue;
					}
					
					sEKPExpt = line;
					while (scnr.hasNextLine()) {
						line = scnr.nextLine();
						sEKPExpt += line;
					}
				}
			} catch (final ParseException e) {
				e.printStackTrace();
			}
		} catch (final FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return sEKPExpt;
	}
}
