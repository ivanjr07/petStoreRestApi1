package com.petStore.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petStore.dao.ItemsDao;
import com.petStore.dto.ItemsDto;
import com.petStore.model.Items;
import com.petStore.utils.PagingParameters;

@Service
public class ItemsService {
	@Autowired
	private ItemsDao itemsDao;
	
	@Transactional(readOnly=true)
	public Items findById(Integer idItems){
		return itemsDao.findById(idItems);
	}
	
	@Transactional(readOnly=true)
	public List<Items> findBySearchDescr(String descr, PagingParameters pagingParameters){
		return itemsDao.findBySearchDescr(descr, pagingParameters);
	}
	
	@Transactional(readOnly=true)
	public List<ItemsDto> findListBySearchDescr(String descr,Integer idBranch, PagingParameters pagingParameters){
		return itemsDao.findListBySearchDescr(descr,idBranch, pagingParameters);
	}
	@Transactional(readOnly=true)
	public List<Items> findByCategory(String descr,Integer idCategory, Integer idSubCategory, PagingParameters pagingParameters){
		return itemsDao.findByCategory(descr,idCategory,idSubCategory, pagingParameters);
	}
	
	@Transactional
	public void save(Items items){
		if (items.getIdItem()  <=0){
			items.setUpdatedDate(new Date());
			items.setDateUpdate(new Date());
			items.setIdStatus(1);
			itemsDao.save(items);
		}else {
			items.setUpdatedDate(new Date());
			items.setDateUpdate(new Date());
			items.setIdStatus(1);
			itemsDao.update(items);
		}
	}
	
	@Transactional
	public void delete(Items items){
		items.setUpdatedDate(new Date());
		items.setDateUpdate(new Date());
		items.setIdStatus(2);
		itemsDao.update(items);
	}
	
	@Transactional(readOnly=true)
	public ItemsDto  findItem(Integer idItem, PagingParameters pagingParameters){
		return itemsDao.findItem(idItem, pagingParameters);
	}
	
	@Transactional
	public void deleteItem (Integer idItem) {
		itemsDao.deleteItem(idItem);
	}
}
