package com.memcached.client;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/*
 * @author prash_mi@yahoo.com
 * 
 * Constants file
 * 
 */

public class SMCCConstants {




	private SMCCConstants() {
		//Utility classes should not have a default constructor
	}

	/*Memcached Keys and constants start*/
	public final static List<String> memcachedClusterNodes = new ArrayList<String>();
	private static final String PROPERTIES_FILE_LOCAL = "smcc";



	/*
	 *Static initialization block
	 * 
	 */


	static{//loads properties from srcc properties file

		ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_FILE_LOCAL, Locale.ENGLISH);

		/*Property Loading for Inmemdeb start*/	
		Enumeration<String> keys =  bundle.getKeys();
		while(keys.hasMoreElements()){//load hosts and ports from property file
			String key = keys.nextElement();
			if(key!= null && key.toLowerCase().startsWith("host-and-port")){
				String value = bundle.getString(key);
				memcachedClusterNodes.add(value);
					System.out.println("Read Cluster Node: "+value);
			}
		}
		/*Property Loading for Inmemdeb end*/	

	}
}
