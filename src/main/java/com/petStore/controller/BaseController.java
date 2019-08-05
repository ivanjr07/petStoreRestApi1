package com.petStore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.petStore.dto.UsersDto;
import com.petStore.utils.Util;




public class BaseController {
	
	protected static UsersDto getCurrentUserDetails() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        UsersDto result = Util.getCurrentUserDetails(request.getSession());

        return result;
    
	}
	protected static Map<String, Object> getResponseMap(List data) {
        return getResponseMap(data, data != null ? data.size() : 0);
    }
	
	/**
     * Generates modelMap to return in the modelAndView
     *
     * @param total
     * @return
     */
    protected static Map<String, Object> getResponseMap(List data, int total) {

        Map<String, Object> modelMap = new HashMap<String, Object>(4);
        modelMap.put("total", total);
        modelMap.put("data", data);
        modelMap.put("success", true);
        modelMap.put("msg", "Successful");

        return modelMap;
    }
    
    /**
     * Generates modelMap to return in the modelAndView in case
     * of exception
     *
     * @param msg message
     * @return
     */
    protected static Map<String, Object> getModelMapError(String msg) {

        Map<String, Object> modelMap = new HashMap<String, Object>(2);
        modelMap.put("success", false);
        modelMap.put("msg", msg);

        return modelMap;
    }

    protected static Map<String, Object> getModelMapSuccess(String msg) {

        Map<String, Object> modelMap = new HashMap<String, Object>(2);
        modelMap.put("success", true);
        modelMap.put("msg", msg);

        return modelMap;
    }
    
    protected static Map<String, Object> getResponseMapFromObject(Object data) {

        Map<String, Object> modelMap = new HashMap<String, Object>(4);
        modelMap.put("data", data);
        modelMap.put("success", true);
        modelMap.put("msg", "Successful");

        return modelMap;
    }
    
    protected static Map<String, Object> getModelMapSuccessId(String msg,Integer id) {

        Map<String, Object> modelMap = new HashMap<String, Object>(3);
        modelMap.put("id", id);
        modelMap.put("success", true);
        modelMap.put("msg", msg);

        return modelMap;
    }
    


}
