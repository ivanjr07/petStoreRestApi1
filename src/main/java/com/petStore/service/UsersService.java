package com.petStore.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petStore.dao.UsersDao;
import com.petStore.dto.UserObjDto;
import com.petStore.dto.UsersDto;
import com.petStore.model.Users;
import com.petStore.utils.PagingParameters;





@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	
	@Transactional(readOnly = true)
	public UsersDto getByName(String name){
		
		Users users = usersDao.findByLogin(name);
		if( users == null) {
			return null;
		}else{
			UsersDto userDto = new UsersDto(users);
			
			return userDto;			
		}
				
	}
	
	@Transactional(readOnly = true)
	public Users findByUserId (Integer idUser) {
		return usersDao.findById(idUser);
	}
	@Transactional
	public List<Users> findByCompanyId(PagingParameters pagingParameters){
		return usersDao.findByCompanyId(pagingParameters);
	}
	
	@Transactional
	public void save(Users itemUser,Integer idRole) {
		
		itemUser.setCountry("México");
		if (itemUser.getIdUsers() == 0){
			itemUser.setIdStatus(1);
			itemUser.setUpdatedDate(new Date());
			try {
				itemUser.setPassword(createPassword(itemUser.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usersDao.save(itemUser);
			
		} else {
			if (itemUser.getImage()== null){
				Users tempobj = usersDao.findById(itemUser.getIdUsers());
				if (tempobj.getImage() != null) itemUser.setImage(tempobj.getImage()); 
			}
			itemUser.setUpdatedDate(new Date());
			try {
				itemUser.setPassword(createPassword(itemUser.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usersDao.update(itemUser);
		}
		
	}
	
	
	@Transactional
	public void delete(Users itemUser){
		itemUser.setIdStatus(2);
		itemUser.setUpdatedDate(new Date());
		usersDao.update(itemUser);
	}
	
	@SuppressWarnings("unused")
	private String createPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}
	@Transactional(readOnly = true)
	public List<UserObjDto> findByCompanyIdRole(Integer idCompany,Integer idBranch, Integer idRole,PagingParameters pagingParameters){
		return usersDao.findByCompanyIdRole(idCompany, idBranch, idRole, pagingParameters);
	}
}
