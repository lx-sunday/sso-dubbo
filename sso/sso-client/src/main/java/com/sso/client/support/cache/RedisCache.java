package com.sso.client.support.cache;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sso.client.authc.AuthenticationCache;
import com.sso.client.support.SerializeUtils;
import com.sso.interfaces.model.SsoUserVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache implements AuthenticationCache {

    private String host = "127.0.0.1";
    private int port = 6379;
    private int expire = 0;
    private int timeout = 0;
    private String keyPrefix;
    private Logger logger;
    private static JedisPool jedisPool = null;

    public void init() {

        this.logger = LoggerFactory.getLogger(this.getClass());
        this.keyPrefix = "sso_client_session:";
        if(jedisPool == null) {
            if(this.timeout != 0) {
                jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port, this.timeout);
            } else {
                jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port);
            }
        }

    }

    private byte[] get(byte[] key) {
        Object value = null;
        Jedis jedis = (Jedis)jedisPool.getResource();

        byte[] value1;
        try {
            value1 = jedis.get(key);
        } finally {
            jedisPool.returnResource(jedis);
        }

        return value1;
    }

    private byte[] set(byte[] key, byte[] value) {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.set(key, value);
            if(this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }

        return value;
    }

    private byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.set(key, value);
            if(expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }

        return value;
    }

    private void del(byte[] key) {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.del(key);
        } finally {
            jedisPool.returnResource(jedis);
        }

    }

    private void flushDB() {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.flushDB();
        } finally {
            jedisPool.returnResource(jedis);
        }

    }

    public Long dbSize() {
        Long dbSize = Long.valueOf(0L);
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            dbSize = jedis.dbSize();
        } finally {
            jedisPool.returnResource(jedis);
        }

        return dbSize;
    }

    public Set<byte[]> keys(String pattern) {
        Set keys = null;
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            jedisPool.returnResource(jedis);
        }

        return keys;
    }

    private byte[] getByteKey(String key) {
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExpire() {
        return this.expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    @Override
    public void putSession(String key, SsoUserVo value) {
        this.logger.debug("存储 key [" + key + "]");
         set(getByteKey(key), SerializeUtils.serialize(value));
    }

    @Override
    public SsoUserVo getSession(String key) {
        this.logger.debug("根据key从存储 key [" + key + "]");
        byte[] t = get(this.getByteKey(key));
        Object value = SerializeUtils.deserialize(t);
        return value == null ? null : (SsoUserVo) value;
    }

    public void putSession(String key,Object obj,int expireTime){
        this.logger.debug("存储 key [" + key + "]");
        set(getByteKey(key), SerializeUtils.serialize(obj),expireTime);
    }

    @Override
    public void putSession(String key, Object obj) {
        this.logger.debug("存储 key [" + key + "]");
        set(getByteKey(key), SerializeUtils.serialize(obj));
    }

    public Object getSessionBykey(String key){
        this.logger.debug("根据key从存储 key [" + key + "]");
        byte[] t = get(this.getByteKey(key));
        Object value = SerializeUtils.deserialize(t);
        return value == null ? null : value;
    }

    @Override
    public void delSession(String key) {
        this.logger.info("根据key删除session [" + key + "]");
        del(this.getByteKey(key));
    }
}
