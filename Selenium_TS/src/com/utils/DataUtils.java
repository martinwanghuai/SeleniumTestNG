package com.utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.config.Config;

/**
 * 
 * @author martin.wang
 *
 */
public class DataUtils {

	private DataUtils() {
		throw new AssertionError();
	}

	public static String LINESEPARATOR = "\n";
	public static String FILE_PATH_SEPARATOR = "/";

	public static String decodeURL(final String URL) {
		String result = "";
		try {
			result = URLDecoder.decode(URL, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getCurrentTimeAsStr() {
		final Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat(Config.getInstance().getProperty(
				"timeFormat")).format(cal.getTime());
	}

	public static Calendar strToCalendarDate(final String dateStr) {
		final Calendar cal = Calendar.getInstance();
		try {
			final Date date = new SimpleDateFormat(Config.getInstance().getProperty(
					"dateFormat")).parse(dateStr);
			cal.setTime(date);
		} catch (final ParseException e) {
			e.printStackTrace();
		}

		return cal;
	}

	public static String calendarDateToString(final Calendar date) {
		final String dateStr = new SimpleDateFormat(Config.getInstance().getProperty(
				"dateFormat")).format(date);
		return dateStr;
	}

	public static String dateToString(final Date date) {
		final String dateStr = new SimpleDateFormat(Config.getInstance().getProperty(
				"dateFormat")).format(date);
		return dateStr;
	}

	public static String getTimeStamp() {
		final Date now = new Date();
		final String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
				.format(now);
		return timeStamp;
	}

	/**
	 * 2014-06-04:override abstract methods
	 * 
	 * @param superClz
	 * @return
	 */
	private static String handleAbstractMethods(final String superClz) {
		final StringBuilder sb = new StringBuilder();

		try {

			final ArrayList<Method> abstractMethods = new ArrayList<Method>();
			final Class clz = Class.forName(superClz);
			final Method[] methods = clz.getMethods();
			for (final Method method : methods) {
				if (Modifier.isAbstract(method.getModifiers())) {
					abstractMethods.add(method);
				}
			}

			for (final Method method : abstractMethods) {
				sb.append(translateReflectedMethod(method));
			}

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final SecurityException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	private static String translateReflectedMethod(final Method method) {
		final StringBuilder sb = new StringBuilder();
		final String modifier = Modifier.toString(method.getModifiers()
				- Modifier.ABSTRACT);
		final String returnType = method.getReturnType().toString();
		final String methodName = method.getName();
		final Class[] paraList = method.getParameterTypes();

		sb.append(modifier).append(" ").append(returnType).append(" ")
				.append(methodName).append("(");

		int i = 0;
		for (; i < paraList.length - 1; i++) {
			final Class para = paraList[i];
			sb.append(para.getName()).append(" para" + i + ",");
		}

		final Class para = paraList[i];
		sb.append(para.getName()).append(" para" + i + "){")
				.append(LINESEPARATOR);

		// method body
		if (!returnType.equalsIgnoreCase("void")) {
			final Class type = method.getReturnType();
			sb.append(method.getReturnType().toString()).append(" result = ")
					.append(getDefaultValue(type)).append(";")
					.append(LINESEPARATOR);

			sb.append("return result;");
		}

		sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);

		return sb.toString();
	}

	public static Object getDefaultValue(final Class clazz) {
		Object result = null;
		if (clazz.equals(boolean.class)) {
			result = false;
		} else if (clazz.equals(byte.class)) {
			result = 0;
		} else if (clazz.equals(short.class)) {
			result = 0;
		} else if (clazz.equals(int.class)) {
			result = 0;
		} else if (clazz.equals(long.class)) {
			result = 0;
		} else if (clazz.equals(float.class)) {
			result = 0.0f;
		} else if (clazz.equals(double.class)) {
			result = 0.0d;
		} else {
			result = null;
		}

		return result;
	}

	public static void createClassFromExcel(final String packageName,
			final String superClz, final String clzName, final ArrayList<String> fieldList,
			final ArrayList<String> methodList) {
		final StringBuilder sb = new StringBuilder();
		final ArrayList<String> fields = new ArrayList<String>();
		for (final String field : fieldList) {
			if (!ReflectionUtils.containField_Recursive(superClz, field)) {
				fields.add(field);
			}
		}

		sb.append("package " + packageName + ";").append(LINESEPARATOR);

		sb.append("import org.openqa.selenium.By;").append(LINESEPARATOR);
		sb.append("import org.openqa.selenium.WebDriver;")
				.append(LINESEPARATOR);
		sb.append("import org.openqa.selenium.WebElement;").append(
				LINESEPARATOR);
		sb.append("import org.openqa.selenium.support.ui.Select;").append(
				LINESEPARATOR);
		sb.append("import com.abstractclasses.TestObject;").append(
				LINESEPARATOR);
		sb.append("import com.utils.WebDriverUtils;").append(
				LINESEPARATOR);
		sb.append("import com.view.Navigator;").append(LINESEPARATOR)
				.append(LINESEPARATOR);

		sb.append("public class " + clzName + " extends " + superClz + "{")
				.append(LINESEPARATOR);

		if (fields.size() > 0) {
			sb.append("private String ");
			for (int i = 0; i < fields.size() - 1; i++) {
				final String field = fields.get(i);
				sb.append(field + "=\"\",");
				if (i % 4 == 0) {
					sb.append(LINESEPARATOR);
				}
			}
			sb.append(fields.get(fields.size() - 1) + "=\"\";")
					.append(LINESEPARATOR).append(LINESEPARATOR);
		}

		// 2014-06-04: handle abstract methods
		sb.append(handleAbstractMethods(superClz));

		sb.append("public ").append(clzName).append("(){")
				.append(LINESEPARATOR);
		sb.append("\t\tsuper();").append(LINESEPARATOR);
		sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);

		for (int i = 0; i < fields.size(); i++) {
			final String field = fields.get(i);
			sb.append("public String get" + field + "(){")
					.append(LINESEPARATOR);
			sb.append("\t\treturn " + field + ";").append(LINESEPARATOR);
			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		for (int i = 0; i < fields.size(); i++) {
			final String field = fields.get(i);
			sb.append(
					"public void set" + field + "(String "
							+ field.toLowerCase() + "){").append(LINESEPARATOR);
			sb.append("\t\t" + field + "=" + field.toLowerCase() + ";").append(
					LINESEPARATOR);
			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		for (int i = 0; i < fields.size(); i++) {
			final String field = fields.get(i);
			sb.append(
					"public void set" + field
							+ "_UI(WebDriver driver, String str){").append(
					LINESEPARATOR);
			sb.append("\t\tif(!str.equals(\"\")){").append(LINESEPARATOR);
			sb.append("\t\t}").append(LINESEPARATOR);
			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		final ArrayList<String> methods = new ArrayList<String>();
		for (final String method : methodList) {
			if (!ReflectionUtils.containMethod_Recursive(superClz, method)
					&& !methods.contains(method)) {
				methods.add(method);
			}
		}

		for (int i = 0; i < methods.size(); i++) {
			final String method = methodList.get(i);
			sb.append("public void " + method + "(WebDriver driver){").append(
					LINESEPARATOR);

			sb.append(
					"\t\tNavigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(\"ManageCenter\",\"1.Overview\"));")
					.append(LINESEPARATOR);

			for (int j = 0; j < fields.size(); j++) {
				final String field = fields.get(j);
				sb.append(
						"\t\tthis.set" + field + "_UI(driver, this.get" + field
								+ "());").append(LINESEPARATOR);
			}

			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		sb.append("}");

		final String classFile = System.getProperty("user.dir") + "/src/"
				+ packageName.replace(".", "/") + "/" + clzName + ".java";

		DataUtils.saveFile(classFile, sb.toString());

	}

	public static void mapExcelToJavaClass(final String sheetName) {
		DataUtils.mapExcelToJavaClass(sheetName, "com.netdimen.model",
				"com.netdimen.abstractclasses.TestObject");
	}

	public static void mapExcelToJavaClass(final String sheetName,
			final String packageName, final String superClz) {
		try {
			final FileInputStream file = new FileInputStream(Config.getInstance()
					.getProperty("testDataFile") + "");
			final HSSFWorkbook wb = new HSSFWorkbook(file);
			final HSSFSheet sheet = wb.getSheet(sheetName);

			wb.getCreationHelper().createFormulaEvaluator().evaluateAll();

			final int rowIndex = 0;
			final int columnIndex_start = 0;
			final ArrayList<String> fieldList = POIUtils.getRowFromExcel(sheet,
					rowIndex, columnIndex_start);

			final int columnIndex = 0;
			final int rowIndex_start = 1;
			final ArrayList<String> methodList = POIUtils.getColumnFromExcel(sheet,
					columnIndex, rowIndex_start);

			final String clzName = sheetName;
			DataUtils.createClassFromExcel(packageName, superClz, clzName,
					fieldList, methodList);

			System.out
					.println("Done to create:"
							+ sheetName
							+ ".java. To see the result, pls press F5 to refresh package:"
							+ packageName);

		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveFile(final String path, final String content) {
		try {
			final BufferedWriter br = new BufferedWriter(new FileWriter(path));
			br.write(content);
			br.flush();
			br.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {

		final String sheetName = "PersonalLoan";
		final String packageName = "com.model";
		final String superClz = "com.abstractclasses.TestObject";
		DataUtils.mapExcelToJavaClass(sheetName, packageName, superClz);
	}
}
