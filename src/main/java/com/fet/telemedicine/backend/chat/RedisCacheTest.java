package com.fet.telemedicine.backend.chat;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class RedisCacheTest {

    /**
     * 快取test 快取生成規則: conf:redis:類名方法名引數hashcode
     * 
     * @param param1
     * @param param2
     * @return
     * 
     *         注意: @Cacheable註解生成的型別在redis中預設都是string
     *         在每次請求的時候,都是先根據key到redis查詢是否存在,如不存在則執行方法中的程式碼
     */
    @Cacheable(value = "redis", keyGenerator = "cacheKeyGenerator")
    public String getRedisString(String param1, String param2) {
	return param1 + ":" + param2;
    }

    /**
     * 清除快取
     * 
     * @return
     */
    @CacheEvict(value = "redis", allEntries = true)
    public String cleanCache() {
	return "success";
    }

}
