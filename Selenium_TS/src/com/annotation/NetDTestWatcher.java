package com.annotation;

import java.lang.reflect.Method;

import org.junit.rules.TestWatcher;

import com.abstractclasses.TestObject;

public abstract class NetDTestWatcher extends TestWatcher implements
		INetDTestWatcher {

	@Override
	public abstract boolean isSkipClass(TestObject obj);

	@Override
	public abstract boolean isSkipMethod(Method method);

	@Override
	public void start(final TestObject obj) {

	}

	@Override
	public void failed(final Throwable e, final TestObject obj) {

	}

	@Override
	public void succeeded(final TestObject obj) {

	}

	@Override
	public void finished(final TestObject obj) {

	}

}
