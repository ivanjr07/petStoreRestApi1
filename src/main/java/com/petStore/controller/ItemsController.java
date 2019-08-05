package com.petStore.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petStore.dto.ItemsDto;
import com.petStore.dto.UsersDto;
import com.petStore.model.Items;
import com.petStore.service.ItemsService;
import com.petStore.utils.PagingParameters;


@Controller
@RequestMapping("/items") 
public class ItemsController extends BaseController{
	@Autowired
	private ItemsService itemsService;
	
	private static final Logger LOGGER = Logger.getLogger(ItemsController.class);
	
	@RequestMapping(value = "/findItems.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findItems(String query,Integer start, Integer limit){
		LOGGER.info("Getting information from Items table");
		UsersDto user = getCurrentUserDetails();
		try{
			PagingParameters pagingParameters= null;
	    	if (limit !=null) pagingParameters = new PagingParameters(limit, start);
	    	List<Items>  elements = itemsService.findBySearchDescr(query, pagingParameters);
	    	Map<String, Object> result = getResponseMap(elements,  pagingParameters !=null ?pagingParameters.getTotal():0);
	    	return result;
		}
		catch(Exception e) {
	    	LOGGER.error("error", e);
	        return getModelMapError(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/findListItems.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findListItems(String query,Integer idCompany,Integer idBranch,Integer start, Integer limit){
		LOGGER.info("Getting information from Items table");
		UsersDto user = getCurrentUserDetails();
		try{
			PagingParameters pagingParameters= null;
			Map<String, Object> result = null;
	    	if (limit !=null) pagingParameters = new PagingParameters(limit, start);
	    	if (idBranch != null && idCompany != null){
	    		List<ItemsDto>  elements = itemsService.findListBySearchDescr(query,(Integer) (idBranch != null ? idBranch: user.getIdBranch()) , pagingParameters);
		    	 result = getResponseMap(elements,  pagingParameters !=null ?pagingParameters.getTotal():0);
	    	}
	    	
	    	
	    	return result;
		}
		catch(Exception e) {
	    	LOGGER.error("error", e);
	        return getModelMapError(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/findItemById.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findItemById(Integer idItem){
		LOGGER.info("Getting information from Items table");
		try{
			Items items = itemsService.findById(idItem);
			Map<String, Object> modelMap = getResponseMapFromObject(items);
			 return modelMap;
		}catch(Exception e) {
			LOGGER.error("error", e);
			return getModelMapError(e.getMessage());
		}
	} 
	
	@RequestMapping(value = "/saveItems.action", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, ? extends Object> saveItems(@ModelAttribute Items items){
		try {
			UsersDto user = getCurrentUserDetails();
			items.setIdUserUpdate(user.getIdUsers());
			itemsService.save(items);
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	@RequestMapping(value = "/findListItemsByFamily.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>findListItemsByFamily(String query,Integer idCategory, Integer idSubCategory,Integer start, Integer limit){
		LOGGER.info("Getting information from Items table");
		try{
			PagingParameters pagingParameters= null;
	    	if (limit !=null) pagingParameters = new PagingParameters(limit, start);
	    	List<Items>  elements = itemsService.findByCategory(query,idCategory,idSubCategory, pagingParameters);
	    	Map<String, Object> result = getResponseMap(elements,  pagingParameters !=null ?pagingParameters.getTotal():0);
	    	return result;
		}
		catch(Exception e) {
	    	LOGGER.error("error", e);
	        return getModelMapError(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/findItemForProduct.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findItemForProduct(Integer idItem){
		LOGGER.info("Getting information from Items table");
		try{
			ItemsDto items = itemsService.findItem(idItem,null);
			Map<String, Object> modelMap = getResponseMapFromObject(items);
			 return modelMap;
		}catch(Exception e) {
			LOGGER.error("error", e);
			return getModelMapError(e.getMessage());
		}
	} 
	
}
