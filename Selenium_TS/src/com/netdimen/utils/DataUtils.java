package com.netdimen.utils;

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

import com.netdimen.config.Config;

/**
 * 
 * @author martin.wang
 *
 */
public class DataUtils {

	// Suppress default constructor for noninstantiability
	private DataUtils() {

		throw new AssertionError();
	}

	public static String LINESEPARATOR = "\n";
	public static String FILE_PATH_SEPARATOR = "/";

	public static String decodeURL(String URL) {
		String result = "";
		try {
			result = URLDecoder.decode(URL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String getCurrentTimeAsStr() {
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat(Config.getInstance().getProperty(
				"timeFormat")).format(cal.getTime());
	}

	public static Calendar strToCalendarDate(String dateStr) {
		Calendar cal = Calendar.getInstance();
		try {
			Date date = new SimpleDateFormat(Config.getInstance().getProperty(
					"dateFormat")).parse(dateStr);
			cal.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cal;
	}

	public static String calendarDateToString(Calendar date) {
		String dateStr = new SimpleDateFormat(Config.getInstance().getProperty(
				"dateFormat")).format(date);
		return dateStr;
	}

	public static String dateToString(Date date) {
		String dateStr = new SimpleDateFormat(Config.getInstance().getProperty(
				"dateFormat")).format(date);
		return dateStr;
	}

	public static String getTimeStamp() {
		Date now = new Date();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
				.format(now);
		return timeStamp;
	}

	/**
	 * 2014-06-04:override abstract methods
	 * 
	 * @param superClz
	 * @return
	 */
	private static String handleAbstractMethods(String superClz) {
		StringBuilder sb = new StringBuilder();

		try {

			ArrayList<Method> abstractMethods = new ArrayList<Method>();
			Class clz = Class.forName(superClz);
			Method[] methods = clz.getMethods();
			for (Method method : methods) {
				if (Modifier.isAbstract(method.getModifiers())) {
					abstractMethods.add(method);
				}
			}

			for (Method method : abstractMethods) {
				/*
				 * sb.append(method.toString()).append("{").
				 * append(LINESEPARATOR
				 * ).append("}").append(LINESEPARATOR).append(LINESEPARATOR);
				 */

				sb.append(translateReflectedMethod(method));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	private static String translateReflectedMethod(Method method) {
		StringBuilder sb = new StringBuilder();
		String modifier = Modifier.toString(method.getModifiers()
				- Modifier.ABSTRACT);
		String returnType = method.getReturnType().toString();
		String methodName = method.getName();
		Class[] paraList = method.getParameterTypes();

		sb.append(modifier).append(" ").append(returnType).append(" ")
				.append(methodName).append("(");

		int i = 0;
		for (; i < paraList.length - 1; i++) {
			Class para = paraList[i];
			sb.append(para.getName()).append(" para" + i + ",");
		}

		Class para = paraList[i];
		sb.append(para.getName()).append(" para" + i + "){")
				.append(LINESEPARATOR);

		// method body
		if (!returnType.equalsIgnoreCase("void")) {
			Class type = method.getReturnType();
			sb.append(method.getReturnType().toString()).append(" result = ")
					.append(getDefaultValue(type)).append(";")
					.append(LINESEPARATOR);

			sb.append("return result;");
		}

		sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);

		return sb.toString();
	}

	public static Object getDefaultValue(Class clazz) {
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

	public static void createClassFromExcel(String packageName,
			String superClz, String clzName, ArrayList<String> fieldList,
			ArrayList<String> methodList) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> fields = new ArrayList<String>();
		for (String field : fieldList) {
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
		sb.append("import com.netdimen.abstractclasses.TestObject;").append(
				LINESEPARATOR);
		sb.append("import com.netdimen.utils.WebDriverUtils;").append(
				LINESEPARATOR);
		sb.append("import com.netdimen.view.Navigator;").append(LINESEPARATOR)
				.append(LINESEPARATOR);

		sb.append("public class " + clzName + " extends " + superClz + "{")
				.append(LINESEPARATOR);

		if (fields.size() > 0) {
			sb.append("private String ");
			for (int i = 0; i < fields.size() - 1; i++) {
				String field = fields.get(i);
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
			String field = fields.get(i);
			sb.append("public String get" + field + "(){")
					.append(LINESEPARATOR);
			sb.append("\t\treturn " + field + ";").append(LINESEPARATOR);
			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		for (int i = 0; i < fields.size(); i++) {
			String field = fields.get(i);
			sb.append(
					"public void set" + field + "(String "
							+ field.toLowerCase() + "){").append(LINESEPARATOR);
			sb.append("\t\t" + field + "=" + field.toLowerCase() + ";").append(
					LINESEPARATOR);
			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		for (int i = 0; i < fields.size(); i++) {
			String field = fields.get(i);
			sb.append(
					"public void set" + field
							+ "_UI(WebDriver driver, String str){").append(
					LINESEPARATOR);
			sb.append("\t\tif(!str.equals(\"\")){").append(LINESEPARATOR);
			sb.append("\t\t}").append(LINESEPARATOR);
			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		ArrayList<String> methods = new ArrayList<String>();
		for (String method : methodList) {
			if (!ReflectionUtils.containMethod_Recursive(superClz, method)
					&& !methods.contains(method)) {
				methods.add(method);
			}
		}

		for (int i = 0; i < methods.size(); i++) {
			String method = methodList.get(i);
			sb.append("public void " + method + "(WebDriver driver){").append(
					LINESEPARATOR);

			sb.append(
					"\t\tNavigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(\"ManageCenter\",\"1.Overview\"));")
					.append(LINESEPARATOR);

			for (int j = 0; j < fields.size(); j++) {
				String field = fields.get(j);
				sb.append(
						"\t\tthis.set" + field + "_UI(driver, this.get" + field
								+ "());").append(LINESEPARATOR);
			}

			sb.append("}").append(LINESEPARATOR).append(LINESEPARATOR);
		}

		sb.append("}");

		String classFile = System.getProperty("user.dir") + "/src/"
				+ packageName.replace(".", "/") + "/" + clzName + ".java";

		DataUtils.saveFile(classFile, sb.toString());

	}

	public static void mapExcelToJavaClass(String sheetName) {
		DataUtils.mapExcelToJavaClass(sheetName, "com.netdimen.model",
				"com.netdimen.abstractclasses.TestObject");
	}

	public static void mapExcelToJavaClass(String sheetName,
			String packageName, String superClz) {
		try {
			FileInputStream file = new FileInputStream(Config.getInstance()
					.getProperty("testDataFile") + "");
			HSSFWorkbook wb = new HSSFWorkbook(file);
			HSSFSheet sheet = wb.getSheet(sheetName);

			wb.getCreationHelper().createFormulaEvaluator().evaluateAll();

			int rowIndex = 0;
			int columnIndex_start = 0;
			ArrayList<String> fieldList = POIUtils.getRowFromExcel(sheet,
					rowIndex, columnIndex_start);

			int columnIndex = 0;
			int rowIndex_start = 1;
			ArrayList<String> methodList = POIUtils.getColumnFromExcel(sheet,
					columnIndex, rowIndex_start);

			String clzName = sheetName;
			DataUtils.createClassFromExcel(packageName, superClz, clzName,
					fieldList, methodList);

			System.out
					.println("Done to create:"
							+ sheetName
							+ ".java. To see the result, pls press F5 to refresh package:"
							+ packageName);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveFile(String path, String content) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
			br.write(content);
			br.flush();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String sheetName = "KC";
		String packageName = "com.netdimen.model";
		String superClz = "com.netdimen.abstractclasses.TestObject";
		DataUtils.mapExcelToJavaClass(sheetName, packageName, superClz);

		/*
		 * String sheetName = "QuestionDragAndDrop"; String packageName =
		 * "com.netdimen.model"; String superClz =
		 * "com.netdimen.model.QuestionHotspot";
		 * DataUtils.mapExcelToJavaClass(sheetName, packageName, superClz);
		 */
	}

}
