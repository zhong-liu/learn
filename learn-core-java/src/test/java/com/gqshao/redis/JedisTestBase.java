package com.gqshao.redis;

import com.gqshao.redis.utils.JedisUtils;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class JedisTestBase {

    protected static JedisPool pool;
    protected Jedis jedis = null;
    protected static Logger logger = LoggerFactory.getLogger(JedisTestBase.class);

    @BeforeClass
    public static void init() {
        JedisPoolConfig poolConfig = JedisUtils.getMyDefaultJedisPoolConfig();
        // timeout设置为0，解决JedisConnectionException
        pool = new JedisPool(poolConfig, "192.168.158.128", 8380, 0);
        logger.info("----------------------create resource----------------------");
    }

    @AfterClass
    public static void destroy() {
        pool.destroy();
        logger.info("----------------------destroy resource---------------------");
    }

    @Before
    public void initJedis() {
        jedis = pool.getResource();
        logger.info("----------------------get resource-------------------------");
    }

    @After
    public void destroyJedis() {
        jedis.close();
        logger.info("----------------------return resource----------------------");
    }

}