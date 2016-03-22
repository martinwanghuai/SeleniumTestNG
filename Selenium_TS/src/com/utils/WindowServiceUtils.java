package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.abstractclasses.WindowCMD;

/**
 * @author lester.li This is a class which implements to handle Window Service
 */
public final class WindowServiceUtils extends WindowCMD {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	private final String strStart = "Start";
	@SuppressWarnings("unused")
	private final String strStop = "Stop";
	@SuppressWarnings("unused")
	private String strServiceName;

	@SuppressWarnings("unused")
	public WindowServiceUtils() throws Exception {
		throw new Exception("Default constructor is not allowed.");
	}

	public WindowServiceUtils(final String ServiceName) {
		this.strServiceName = ServiceName;
		this.commandScript.add("sc");

	}

	public void StartService() {

		this.commandScript.add(strStart);
		commandScript.add(this.strServiceName);
		executeCMD();
		this.commandScript.remove(strStart);
		this.commandScript.remove(strServiceName);
	}

	public void StopService() {
		this.commandScript.add(strStop);
		commandScript.add(this.strServiceName);
		executeCMD();
		this.commandScript.remove(strStop);
		this.commandScript.remove(strServiceName);
	}

	public void ReStartService() {
		StopService();
		StartService();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void executeCMD() {

		String[] commmandArr = new String[commandScript.size()];
		commmandArr = commandScript.toArray(commmandArr);
		for (final String s : commmandArr)
			System.out.println(s);
		// You will probably be prompted for the password with following command
		// String startCommandScript[] = { "runas.exe", "/user:Administrator",
		// "sc", "Start", "OracleServiceXE" };
		System.out.println("Cmd end .......");
		Process process;
		try {
			process = new ProcessBuilder(commmandArr).start();
			final InputStream is = process.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;

			while ((line = br.readLine()) != null) {
				if (line.matches("controlservice failed")) {
					System.out.println(".......");
				}
				System.out.println(line);
			}

			br.close();
			isr.close();
			is.close();
			process.destroy();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		final WindowServiceUtils OracleService = new WindowServiceUtils(
				"OracleServiceXE");
		OracleService.ReStartService();
	}

}
