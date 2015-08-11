package com.netdimen.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.netdimen.abstractclasses.TestObject;

/**
 * 
 * @author martin.wang
 *
 */
public class ReflectionUtils {

	private ReflectionUtils() {
		throw new AssertionError();
	}

	public static boolean containField_Recursive(final String clzName,
			final String fieldName) {
		boolean containField = false;

		try {
			Class clz = Class.forName(clzName);

			while (!containField && clz != null) {
				containField = ReflectionUtils.containField(clz, fieldName);
				clz = clz.getSuperclass();
			}

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		return containField;
	}

	public static boolean containMethod_Recursive(final String clzName,
			final String methodName) {
		boolean containMethod = false;

		try {
			Class clz = Class.forName(clzName);

			while (!containMethod && clz != null) {
				containMethod = ReflectionUtils.containMethod(clz, methodName);
				clz = clz.getSuperclass();
			}

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		return containMethod;
	}

	public static Field getField_superClz(Class<? extends TestObject> clz,
			final String fieldName) {
		Field field = null;
		final Class<? extends TestObject> orginalClz = clz;
		try {
			Class superClz = null;
			boolean containField = false;
			do {
				superClz = clz.getSuperclass();
				if (superClz != null) {
					containField = ReflectionUtils.containField(superClz,
							fieldName);
					clz = superClz;
				}
			} while (!containField && superClz != null);

			if (containField) {
				field = superClz.getDeclaredField(fieldName);
			} else if (!containField && superClz == null) {
				System.out.println("In " + orginalClz.getName()
						+ " cls,cannot find field:" + fieldName);
			}
		} catch (final NoSuchFieldException e) {
			e.printStackTrace();
		} catch (final SecurityException e) {
			e.printStackTrace();
		}

		return field;
	}

	public static boolean containField(final Class clz, final String fieldName) {
		final Field[] fields = clz.getDeclaredFields();
		boolean contain = false;
		for (final Field field : fields) {
			if (field.getName().equals(fieldName)) {
				contain = true;
				break;
			}
		}

		return contain;
	}

	public static boolean containMethod(final Class clz, final String methodName) {
		final Method[] methods = clz.getDeclaredMethods();
		boolean contain = false;
		for (final Method method : methods) {
			if (method.getName().equals(methodName)) {
				contain = true;
				break;
			}
		}

		return contain;
	}

	public static Field[] getFields(final String clzName) {
		final Class clz = ReflectionUtils.loadClass(clzName);
		return clz.getDeclaredFields();
	}

	public static Class loadClass(final String clzName) {
		Class clz = null;

		try {
			clz = Class.forName(clzName);
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		return clz;
	}

	public static Object loadObject(final String clzName) {
		Object obj = null;

		try {
			final Class clz = Class.forName(clzName);
			obj = clz.newInstance();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static Field getField(final Class clz, final String fieldName) {
		Field field = null;

		try {
			if (ReflectionUtils.containField(clz, fieldName)) {
				field = clz.getDeclaredField(fieldName);
			} else {
				// search field in super classes
				field = ReflectionUtils.getField_superClz(clz, fieldName);
			}
		} catch (final NoSuchFieldException e) {
			e.printStackTrace();
		} catch (final SecurityException e) {
			e.printStackTrace();
		}

		return field;
	}

	public static String getFieldValueAsString(final TestObject testObject,
			final String fieldName) {
		String fieldValue = "";

		try {
			final Class clz = testObject.getClass();
			final Field field = ReflectionUtils.getField(clz, fieldName);
			field.setAccessible(true);
			fieldValue = (String) field.get(testObject);
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		return fieldValue;
	}

	public static ArrayList<TestObject> getFieldValueAsTestObjectArray(
			final TestObject testObject, final String fieldName) {
		ArrayList<TestObject> fieldValue = null;

		try {
			final Class clz = testObject.getClass();
			final Field field = ReflectionUtils.getField(clz, fieldName);
			field.setAccessible(true);
			fieldValue = (ArrayList<TestObject>) field.get(testObject);
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		return fieldValue;
	}

}
