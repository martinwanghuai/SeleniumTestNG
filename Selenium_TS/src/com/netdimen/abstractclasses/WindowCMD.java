package com.netdimen.abstractclasses;

import java.util.ArrayList;

import com.google.common.collect.Lists;

/**
 * @author lester.li This is abstract class which can be extend to implement
 *         different Window Command
 */
public abstract class WindowCMD {

	protected ArrayList<String> commandScript;

	public WindowCMD() {
		this.commandScript = Lists.newArrayList();
		this.commandScript.add("cmd.exe");
		this.commandScript.add("/c");
	}

	protected abstract void executeCMD();
}
