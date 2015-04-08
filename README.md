## What is simple-memcached-cluster-client (SMCC)?
SMCC is a Java API on top of SpyMemcahced 2.8.12 which distributes keys between Memcached hosts running in stand alone mode. It allows Memcached nodes which doesn't support clustering out of the box to horizontally scale like a cluster

##How it works?
This API simply distributes the keys to memcached nodes. Basically the logic map each key to a memcached host on the basis of key's hashcode.

## How to Use It?
You can use this API as follows:

1> Start Memcached hosts.

2> Enter the Memcached Hosts and ports details in smcc_en_US.properties which is found at src/main/resources and Build the project.

3> Simply consome SMCC using a Java code like:

```java
InMemoryDSConnection inMemCon = SRCCSingleton.getInstance();//Get Instance and initialize cluster connection
inMemCon.putInCache("TestKey", "TestValue", 5000);//put a value in cluster
System.out.println("TestKey Set");//
System.out.println("Retrieved from cache: "+inMemCon.getFromCache("TestKey"));//get the value back from the cluster
```

## License
Open source under Apache License Version 2.0
