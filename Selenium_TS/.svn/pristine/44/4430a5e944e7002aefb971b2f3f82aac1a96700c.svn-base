/*
 * 
 * Copyright (c) 1999-2012 NetDimensions Ltd.
 * 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of NetDimensions Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with NetDimensions.
 */
package com.netdimen.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.netdimen.config.Config;

/**
 * 
 * @author Lester
 */
public class DBManager {



	private String driverName;
	private String connectURL;
	private String dbType;
    private String dbaUser;
    private String dbaPassword;
    private Connection conn;

    public DBManager() {
    	this.connectURL= Config.getInstance().getProperty("default.connectURL");
    	this.dbaUser= Config.getInstance().getProperty("default.user");
    	this.dbaPassword= Config.getInstance().getProperty("default.password");
    	this.dbType= Config.getInstance().getProperty("default.dbType");
    	this.driverName= Config.getInstance().getProperty("default.driverName");
    	System.out.println("connectURL="+this.connectURL);
    	System.out.println("dbaUser="+this.dbaUser);
    	System.out.println("dbaPassword="+this.dbaPassword);
    	System.out.println("dbType="+this.dbType);
    	
		try {
			// load the Driver Class
            Class.forName(this.driverName);
         // create the connection now
            setConn(DriverManager.getConnection(this.connectURL,this.dbaUser,dbaPassword));	
            
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public final String getDriverName() {

		return driverName;
	}

	public final void setDriverName(String driverName) {

		this.driverName = driverName;
	}


    public String getDbaUser() {
        return dbaUser;
    }

    public void setDbaUser(String dbaUser) {
        this.dbaUser = dbaUser;
    }

    public String getDbaPassword() {
        return dbaPassword;
    }

    public void setDbaPassword(String dbaPassword) {
        this.dbaPassword = dbaPassword;
    }

	public String getConnectURL() {
		return connectURL;
	}

	public void setConnectURL(String connectURL) {
		this.connectURL = connectURL;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
//	public static void main(String[] args)  {
//		DBManager dbmgr= new DBManager();
//	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
}
