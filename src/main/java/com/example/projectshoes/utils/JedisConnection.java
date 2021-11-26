package com.example.projectshoes.utils;


import redis.clients.jedis.Jedis;

public class JedisConnection {

  public static Jedis getConnection() {

//    Jedis jedis = new Jedis(System.getenv("HOST_REDIS"), 6379);
//    jedis.auth(System.getenv("AUTH_REDIS"));
    Jedis jedis = new Jedis("localhost", 6379);
    try {
      System.out.println("Hello Redis: " + jedis.ping());
//      jedis.flushAll();
      return jedis;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
