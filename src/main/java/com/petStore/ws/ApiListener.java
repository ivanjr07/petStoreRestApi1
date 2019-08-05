package com.petStore.ws;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.petStore.controller.BaseController;
import com.petStore.dto.UsersDto;
import com.petStore.model.Items;
import com.petStore.service.ItemsService;
import com.petStore.service.UsersService;
import com.petStore.utils.PagingParameters;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Controller
@RequestMapping(value = "/ApiListener")
public class ApiListener  extends BaseController{
	
	private static final Logger LOG = Logger.getLogger(ApiListener.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private UsersService usersService;
	
	
	private static final Logger LOGGER = Logger.getLogger(ApiListener.class);
	
	@RequestMapping(value = "/findItems.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findItems(String query,Integer start, Integer limit){
		LOGGER.info("Getting information from Items table");
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
	
	@RequestMapping(value = "/saveItems.action", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, ? extends Object> saveItems(@RequestBody Items items){
		try {
			items.setIdUserUpdate(1);
			itemsService.save(items);
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	
	@RequestMapping(value = "/saveForm.action", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public
    @ResponseBody
    Map<String, ? extends Object> saveForm(@ModelAttribute Items items,@RequestPart(value ="imageUpload", required=false) MultipartFile file){
		try {
//			String uploadsDir =  System.getProperty("catalina.home");
			String uploadsDir ="C:\\bckImageIOriente";
			items.setIdUserUpdate(1);
			itemsService.save(items);
			
			if (file!= null ) {
	            System.out.println(file.getName());
	            System.out.println(file.getSize());
	            System.out.println(file.getContentType());
                String filePath = uploadsDir +"\\pets\\"+ items.getIdItem()+"_pet.jpg";
                File dest = new File(filePath);
                if (!dest.exists()) dest.mkdirs();
                file.transferTo(dest);
	        }
			
			
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	@RequestMapping(value = "/updateForm.action", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public
    @ResponseBody
    Map<String, ? extends Object> updateForm(@ModelAttribute Items items,@RequestPart(value ="imageUpload", required=false) MultipartFile file){
		try {
//			String uploadsDir =  System.getProperty("catalina.home");
			String uploadsDir ="C:\\bckImageIOriente";
			items.setIdUserUpdate(1);
			itemsService.save(items);
			
			if (file!= null ) {
	            System.out.println(file.getName());
	            System.out.println(file.getSize());
	            System.out.println(file.getContentType());
                String filePath = uploadsDir +"\\pets\\"+ items.getIdItem()+"_pet.jpg";
                File dest = new File(filePath);
                if (!dest.exists()) dest.mkdirs();
                file.transferTo(dest);
	        }
			
			
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	@RequestMapping(value = "/updateItems.action", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, ? extends Object> updateItems(@RequestBody Items items){
		try {
			items.setIdUserUpdate(1);
			itemsService.save(items);
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	@RequestMapping(value = "/findItemById.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findItemById(@RequestBody Items req){
		LOGGER.info("Getting information from Items table");
		try{
			Items items = itemsService.findById(req.getIdItem());
			Map<String, Object> modelMap = getResponseMapFromObject(items);
			 return modelMap;
		}catch(Exception e) {
			LOGGER.error("error", e);
			return getModelMapError(e.getMessage());
		}
	} 
	
	@RequestMapping(value = "/deleteItem.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteItem(@RequestBody Items req){
		LOGGER.info("Getting information from Items table");
		try{
			itemsService.deleteItem(req.getIdItem());
			return getModelMapSuccess("Successfully Saved");
		}catch(Exception e) {
			LOGGER.error("error", e);
			return getModelMapError(e.getMessage());
		}
	} 
	
}
