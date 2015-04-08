package com.memcached.client;

public class Sample{

	public static void main(String[] args) {
		
		InMemoryDSConnection inMemCon = SRCCSingleton.getInstance();//Get Instance and initialize cluster connection
		inMemCon.putInCache("TestKey", "TestValue", 5000);//put a value in cluster
		System.out.println("TestKey Set");//
		System.out.println("Retrieved from cache: "+inMemCon.getFromCache("TestKey"));//get the value back from the cluster
	}

}
