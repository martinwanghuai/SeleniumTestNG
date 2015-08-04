package com.netdimen.abstractclasses;

import java.util.ArrayList;


/**
 * @author lester.li
 * This is abstract class which can be extend to implement different Window Command
 */
public abstract class WindowCMD {
	
	protected ArrayList<String> commandScript;
	
	public WindowCMD(){
		this.commandScript= new ArrayList<String>();
		this.commandScript.add("cmd.exe");
		this.commandScript.add("/c");
	}
	
	protected abstract void executeCMD();
}
