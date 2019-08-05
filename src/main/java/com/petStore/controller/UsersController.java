package com.petStore.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.petStore.dto.UserObjDto;
import com.petStore.dto.UsersDto;
import com.petStore.model.Users;
import com.petStore.service.UsersService;
import com.petStore.utils.PagingParameters;

@Controller
@RequestMapping("/users") 
public class UsersController  extends BaseController{
	@Autowired
	private UsersService usersService;
	
	private static final Logger LOGGER = Logger.getLogger(UsersController.class);
	
	@RequestMapping(value = "/findUsersByComapy.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUsersByComapy(String query,Integer start, Integer limit){
		LOGGER.info("Getting information from Users table");
		UsersDto user = getCurrentUserDetails();
		try{
			PagingParameters pagingParameters= null;
	    	if (limit !=null) pagingParameters = new PagingParameters(limit, start);
	    	List<Users> elements = usersService.findByCompanyId( pagingParameters);
	    	Map<String, Object> result = getResponseMap(elements,  pagingParameters !=null ?pagingParameters.getTotal():0);
	    	return result;
		}
		catch(Exception e) {
	    	LOGGER.error("error", e);
	        return getModelMapError(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/findUserById.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findUserById(Integer idUsers){
		LOGGER.info("Getting information from User table");
		try{
			Users user= usersService.findByUserId(idUsers);
			 Map<String, Object> modelMap = getResponseMapFromObject(user);
		    return modelMap;
		}catch(Exception e) {
	    	LOGGER.error("error", e);
	        return getModelMapError(e.getMessage());
		}
	}
	@RequestMapping(value = "/imageDisplay.action", method = RequestMethod.GET)
	public void showImage(Integer idUsers, HttpServletResponse response,HttpServletRequest request) throws IOException{
		Users user = usersService.findByUserId(idUsers);
		 response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		 if(user.getImage()!=null){
			 response.getOutputStream().write(user.getImage());
			 response.getOutputStream().close();
		 }
		 
	}
	
	@RequestMapping(value = "/saveUsers.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, ? extends Object> saveUsers(@ModelAttribute Users itemUser,@RequestParam("file") MultipartFile file,Integer idRole){
		try {
			UsersDto user = getCurrentUserDetails();
			usersService.save(itemUser,idRole);
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	
//	@RequestMapping(value = "/saveForm.action", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	@RequestMapping(value = "/saveForm.action", method = RequestMethod.POST)
	public
    @ResponseBody
    Map<String, ? extends Object> saveForm(@ModelAttribute Users itemUser,Integer idRole){
		try {
			UsersDto user = getCurrentUserDetails();
//	        if (file!= null) {
//	            // prints out some information for debugging
//	            System.out.println(file.getName());
//	            System.out.println(file.getSize());
//	            System.out.println(file.getContentType());
//	            byte[] byteArr = file.getBytes();
//	            itemUser.setImage(byteArr);
//	        }
//	         
			usersService.save(itemUser,idRole);
			return getModelMapSuccess("Successfully created");
		}catch  (Exception e) {
            String msg = "Error to save into database: " + e.getMessage();
            LOGGER.error(msg, e);
            return getModelMapError(msg);
        }
	}
	
	@RequestMapping(value = "/findUsersByStatus.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUsersByStatus(Integer idCompany,Integer idBranch, Integer idRole, Integer start, Integer limit){
		LOGGER.info("Getting information from Users table");
		UsersDto user = getCurrentUserDetails();
		try{
			PagingParameters pagingParameters= null;
	    	if (limit !=null) pagingParameters = new PagingParameters(limit, start);
	    	List<UserObjDto> elements = usersService.findByCompanyIdRole(idCompany, idBranch, idRole, pagingParameters);
	    	Map<String, Object> result = getResponseMap(elements,  pagingParameters !=null ?pagingParameters.getTotal():0);
	    	return result;
		}
		catch(Exception e) {
	    	LOGGER.error("error", e);
	        return getModelMapError(e.getMessage());
		}
	}
	
}
