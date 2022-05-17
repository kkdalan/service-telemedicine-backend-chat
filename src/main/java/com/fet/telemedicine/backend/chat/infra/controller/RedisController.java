package com.fet.telemedicine.backend.chat.infra.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;
import com.fet.telemedicine.backend.chat.common.api.HttpResult;
import com.fet.telemedicine.backend.chat.infra.service.RedisService;
import com.fet.telemedicine.backend.chat.util.ArrayUtil;
import com.fet.telemedicine.backend.chat.util.BeanUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "RedisController", value = "Redis測試")
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private AccountService accountService;

    @ApiOperation("測試簡單快取")
    @RequestMapping(value = "/simpleTest", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult<AccountPo> simpleTest() {
	List<AccountPo> accountList = accountService.listAll();
	AccountPo account = accountList.get(0);
	String key = "redis:simple:" + account.getId();
	redisService.set(key, account);
	AccountPo cacheAccount = (AccountPo) redisService.get(key);
	return HttpResult.success(cacheAccount);
    }

    @ApiOperation("測試Hash結構的快取")
    @RequestMapping(value = "/hashTest", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult<AccountPo> hashTest() {
	List<AccountPo> accountList = accountService.listAll();
	AccountPo account = accountList.get(0);
	String key = "redis:hash:" + account.getId();
	Map<String, Object> value = BeanUtil.beanToMap(account);
	redisService.hSetAll(key, value);
	Map<Object, Object> cacheValue = redisService.hGetAll(key);
	AccountPo cacheAccount = BeanUtil.mapToBean(cacheValue, AccountPo.class);
	return HttpResult.success(cacheAccount);
    }

    @ApiOperation("測試Set結構的快取")
    @RequestMapping(value = "/setTest", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult<Set<Object>> setTest() {
	List<AccountPo> accountList = accountService.listAll();
	String key = "redis:set:all";
	redisService.sAdd(key, (Object[]) ArrayUtil.toArray(accountList, AccountPo.class));
	redisService.sRemove(key, accountList.get(0));
	Set<Object> cachedAccountList = redisService.sMembers(key);
	return HttpResult.success(cachedAccountList);
    }

    @ApiOperation("測試List結構的快取")
    @RequestMapping(value = "/listTest", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult<List<Object>> listTest() {
	List<AccountPo> accountList = accountService.listAll();
	String key = "redis:list:all";
	redisService.lPushAll(key, (Object[]) ArrayUtil.toArray(accountList, AccountPo.class));
	redisService.lRemove(key, 1, accountList.get(0));
	List<Object> cachedAccountList = redisService.lRange(key, 0, 3);
	return HttpResult.success(cachedAccountList);
    }
}