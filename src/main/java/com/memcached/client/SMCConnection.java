package com.memcached.client;

import java.io.IOException;
import java.util.List;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

/*
 * @author prash_mi@yahoo.com
 * 
 * This class spawns pooled connection to each memcached host
 * and implements the required methods for setting and
 * retrieving key-value pairs for the memcached cluster 
 * 
 */

public class SMCConnection implements InMemoryDSConnection{

	private List<String> memcachedHosts = 	SMCCConstants.memcachedClusterNodes;
	private int buckets;

	public SMCConnection(){
		buckets = memcachedHosts.size();
	}

	@Override
	public void putInCache(String key, String value, int expiry) {//puts a key in cache
		MemcachedClient cache = null;;
		try {
			cache = new MemcachedClient(AddrUtil.getAddresses(memcachedHosts.get(getBucketNumber(key))));
			cache.add(key, expiry, value);
			System.out.println("Added key: "+key+" in Host: "+memcachedHosts.get(getBucketNumber(key)));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			cache.shutdown();
		}
		
	}

	@Override
	public Object getFromCache(String key) {//gets a key from the cache
		MemcachedClient cache = null;;
		try {
			cache = new MemcachedClient(AddrUtil.getAddresses(memcachedHosts.get(getBucketNumber(key))));
			Object value = cache.get(key);
			System.out.println("Retrieved value for key: "+key+" from Host: "+memcachedHosts.get(getBucketNumber(key)));
			return value;
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			cache.shutdown();
		}
		return null;
	}

	@Override
	public void expireKey(String key) {//expires a key

		MemcachedClient cache = null;;
		try {

			cache = new MemcachedClient(AddrUtil.getAddresses(memcachedHosts.get(getBucketNumber(key))));
			cache.delete(key);
			System.out.println("Deleted key: "+key+" from Host: "+memcachedHosts.get(getBucketNumber(key)));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			cache.shutdown();
		}

	}


	private int getBucketNumber(String key){//get bucket number for each key
		
		//This method returns a bucket number such that bucketNumber >= 0 and bucketNumber<TotalMemcachedNodes
		//This method uses hascode of the key for computing the bucket number
		
			return ((key.hashCode()<0) ? (-1*key.hashCode()) : key.hashCode() )%buckets;

	}

}
