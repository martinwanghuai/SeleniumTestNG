package com.netdimen.winservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyBuild {
	public static String destDir="//172.18.77.210/Technology/EKP Master Files/EKP 11.2/builds";
	public static String sourceDir="./build";
	// If targetLocation does not exist, it will be created.
	 public static void copyDirectory(File sourceLocation , File targetLocation)
	  {
		 try {
	      
	     if (sourceLocation.isDirectory()) {
	         if (!targetLocation.exists()) {
	             targetLocation.mkdir();
	         }
	          
	         String[] children = sourceLocation.list();
	         for (int i=0; i<children.length; i++) {
	             copyDirectory(new File(sourceLocation, children[i]),
	                     new File(targetLocation, children[i]));
	         }
	     } else {
	          
	         InputStream in = new FileInputStream(sourceLocation);
	         OutputStream out = new FileOutputStream(targetLocation);
	          
	         // Copy the bits from instream to outstream
	         byte[] buf = new byte[1024];
	         int len;
	         while ((len = in.read(buf)) > 0) {
	             out.write(buf, 0, len);
	         }
	         in.close();
	         out.close();
	     }
		 }catch (IOException io){
			 io.printStackTrace();
		 }
	 }
	public static void main(String[] args){
		//copyDirectory(new File(fileName),new File(destDir));
		
	}

}
