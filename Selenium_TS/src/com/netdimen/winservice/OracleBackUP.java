package com.netdimen.winservice;

import java.io.File;

import com.netdimen.abstractclasses.WindowCMD;


/**
 * @author lester.li
 *
 */
public final class OracleBackUP extends WindowCMD {
	@SuppressWarnings("unused")
	private final String userId;
	@SuppressWarnings("unused")
	private final String userPass;
	@SuppressWarnings("unused")
	private final String file;
	@SuppressWarnings("unused")
	private final String folderPath;
	
	public OracleBackUP()throws Exception {
		 throw new Exception("Default constructor is not allowed.");
	}
	
	public OracleBackUP(String userId, String userPass, String folderPath,String file)throws Exception {
		this.userId=userId;
		this.userPass=userPass;
		this.file=file;
		this.folderPath=folderPath;
		this.commandScript.add("exp");
		File checkFolder = new File(this.folderPath);
		if (!checkFolder.exists()) {
			 throw new Exception("Folder is not existed.");
		}
	}

	/**
	 * ***** NOT Ready yet****
	 *
	 */
	@Override
	protected void executeCMD() {
  
	}

}
