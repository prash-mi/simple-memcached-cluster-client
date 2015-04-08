/**
 * 
 */
package com.memcached.client;

/*
 * @author prash_mi@yahoo.com
 * 
 * Interface
 * 
 */
public interface InMemoryDSConnection {

	public void putInCache(String key, String value, int expiry);
	public Object getFromCache(String key);
	public void expireKey(String key);
	
	
}
