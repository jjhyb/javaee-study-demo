package com.yibo.utils;

import java.util.ResourceBundle;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	private static int port;
	private static String host;
	private static JedisPool pool;
	static{
		//读取jedisconfig.properties配置文件，获取数据
		//另外一种读取properties文件的方法
		ResourceBundle bundle = ResourceBundle.getBundle("jedisconfig");
		port = Integer.parseInt(bundle.getString("port"));
		host = bundle.getString("host");
		
		GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
		//1.创建连接池对象
		pool = new JedisPool(poolConfig , host, port);
	}
	public static Jedis getJedis(){
		//2.调用连接池对象的方法，获取jedis连接对象
		Jedis jedis = pool.getResource();
		return jedis;
	}
}
