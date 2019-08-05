package com.petStore.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.petStore.dto.ItemsDto;
import com.petStore.model.Items;
import com.petStore.utils.PagingParameters;


@Repository
public class ItemsDao  extends BaseDao<Items,Integer>{

	@SuppressWarnings("unchecked")
	public List<Items>  findBySearchDescr(String descr, PagingParameters pagingParameters){
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.eq("idStatus", 1));
		if (descr != null && descr.length()>0) criteria.add(Restrictions.like("Description",descr,MatchMode.START));
		if (pagingParameters !=null){
			if (pagingParameters.getLimit() != 0) {
			    criteria.setMaxResults(pagingParameters.getLimit());
		    }
			criteria.setFirstResult(pagingParameters.getStart());
		}
		List<Items>result = (List<Items>)criteria.list();
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(0);
		Integer total = (Integer) criteria.uniqueResult();
		if(pagingParameters !=null) pagingParameters.setTotal(total != null ?total.intValue():0);
		return result; 
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemsDto>  findListBySearchDescr(String descr, Integer idBranch,PagingParameters pagingParameters){
		String sqlDescr="";
		String sql = " SELECT i.idItem,c.description family,ifnull(s.description,'') subFamily, um.unitOfMeasurement,i.name, i.description,i.unitCost,i.brand, i.startPrice,i.endPrice,i.QuantityPeerUnit, i.unitWeight";
		sql += " FROM items i ";
		sql += " JOIN unitmeasurement um ON i.idUnitMeasurement = um.idUnitMeasurement ";
		sql += " JOIN category c ON i.idCategory = c.idCategory";
		sql += " LEFT OUTER JOIN subcategory s ON i.idSubCategory = s.idSubCategory ";
		sql += " WHERE i.idStatus = 1";
		
		sql += " AND i.idBranch = "+idBranch;
		
		if (descr != null && descr.length()>0) sqlDescr = " AND i.Description like '"+descr+"%'";
		sql += sqlDescr;
		if (pagingParameters != null) {
            sql = pagingParameters.addToSql(sql);
		}  
		SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<ItemsDto> results = query
				.addScalar("family", Hibernate.STRING)
				.addScalar("subFamily", Hibernate.STRING)
				.addScalar("name", Hibernate.STRING)
		        .addScalar("unitOfMeasurement", Hibernate.STRING)
		        .addScalar("description", Hibernate.STRING)
		        .addScalar("unitCost", Hibernate.DOUBLE)
		        .addScalar("brand", Hibernate.STRING)
		        .addScalar("startPrice", Hibernate.DOUBLE)
		        .addScalar("endPrice", Hibernate.DOUBLE)
		        .addScalar("QuantityPeerUnit", Hibernate.INTEGER)
		        .addScalar("unitWeight", Hibernate.INTEGER)
		        .addScalar("idItem", Hibernate.INTEGER)
		        .setResultTransformer(Transformers.aliasToBean(ItemsDto.class)).list();
		if (pagingParameters != null) {
            BigInteger total = (BigInteger) hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery("select FOUND_ROWS()").uniqueResult();
            pagingParameters.setTotal(total.intValue()); 
        }
		return results;
	}   
	
	
	@SuppressWarnings("unchecked")
	public List<Items>  findByCategory(String descr, Integer idCategory, Integer idSubCategory, PagingParameters pagingParameters){
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Items.class);
		criteria.add(Restrictions.eq("idStatus", 1));
		criteria.add(Restrictions.eq("idCategory", idCategory));
		criteria.add(Restrictions.eq("idSubCategory", idSubCategory));
		if (descr != null && descr.length()>0) criteria.add(Restrictions.like("Description",descr,MatchMode.START));
		if (pagingParameters !=null){
			if (pagingParameters.getLimit() != 0) {
			    criteria.setMaxResults(pagingParameters.getLimit());
		    }
			criteria.setFirstResult(pagingParameters.getStart());
		}
		List<Items>result = (List<Items>)criteria.list();
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(0);
		Integer total = (Integer) criteria.uniqueResult();
		if(pagingParameters !=null) pagingParameters.setTotal(total != null ?total.intValue():0);
		return result; 
	}
	
	@SuppressWarnings("unchecked")
	public ItemsDto  findItem(Integer idItem, PagingParameters pagingParameters){
		String sql = " SELECT i.idItem,c.description family,ifnull(s.description,'') subFamily, um.unitOfMeasurement,i.name, i.description,i.unitCost,i.brand, i.startPrice,i.endPrice,i.QuantityPeerUnit, i.unitWeight";
		sql += " FROM items i ";
		sql += " JOIN unitmeasurement um ON i.idUnitMeasurement = um.idUnitMeasurement ";
		sql += " JOIN category c ON i.idCategory = c.idCategory";
		sql += " LEFT OUTER JOIN subcategory s ON i.idSubCategory = s.idSubCategory ";
		sql += " WHERE i.idStatus = 1";
		sql += " AND i.idItem = "+idItem;
		if (pagingParameters != null) {
            sql = pagingParameters.addToSql(sql);
		}  
		SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		ItemsDto result = (ItemsDto) query
				.addScalar("family", Hibernate.STRING)
				.addScalar("subFamily", Hibernate.STRING)
				.addScalar("name", Hibernate.STRING)
		        .addScalar("unitOfMeasurement", Hibernate.STRING)
		        .addScalar("description", Hibernate.STRING)
		        .addScalar("unitCost", Hibernate.DOUBLE)
		        .addScalar("brand", Hibernate.STRING)
		        .addScalar("startPrice", Hibernate.DOUBLE)
		        .addScalar("endPrice", Hibernate.DOUBLE)
		        .addScalar("QuantityPeerUnit", Hibernate.INTEGER)
		        .addScalar("unitWeight", Hibernate.INTEGER)
		        .addScalar("idItem", Hibernate.INTEGER)
		        .setResultTransformer(Transformers.aliasToBean(ItemsDto.class)).uniqueResult();
		if (pagingParameters != null) {
            BigInteger total = (BigInteger) hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery("select FOUND_ROWS()").uniqueResult();
            pagingParameters.setTotal(total.intValue()); 
        }
		return result;
	} 
	
	public void deleteItem(Integer idItem){
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(" UPDATE " + Items.class.getName() +
				" SET idStatus = 2" +
				" WHERE idItem = " + idItem);
		query.executeUpdate();
	}
}
