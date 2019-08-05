package com.petStore.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.petStore.dto.UserObjDto;
import com.petStore.dto.UsersDto;
import com.petStore.model.Users;
import com.petStore.utils.PagingParameters;



@Repository
public class UsersDao extends BaseDao<Users,Integer>{
	
	public Users findByLogin(String name) {
		
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery("Select {u.*} FROM users {u} WHERE Login = ?")
				.addEntity("u",Users.class)
				.setString(0, name);
		
		return (Users) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> findByCompanyId(PagingParameters pagingParameters){
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("idStatus", 1));
		
		if (pagingParameters !=null){
			if (pagingParameters.getLimit() != 0) {
			    criteria.setMaxResults(pagingParameters.getLimit());
		    }
			criteria.setFirstResult(pagingParameters.getStart());
		}
		List<Users> result = (List<Users>) criteria.list();
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(0);
		Integer total = (Integer) criteria.uniqueResult();
		if(pagingParameters !=null) pagingParameters.setTotal(total != null ?total.intValue():0);
		return result; 
	
	}
	
	public void updatePassword (Users users) {
		 Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("UPDATE users SET password = MD5(?)  WHERE idUsers = ?")
				 .setString(0, users.getPassword())
	             .setInteger(1, users.getIdUsers());
	        query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserObjDto> findByCompanyIdRole(Integer idCompany,Integer idBranch, Integer idRole,PagingParameters pagingParameters){
		String sqlRole = "";
		if(idRole != null && idRole>0 ){
			sqlRole ="AND ur.idRole = "+idRole;
		}
		String sql = "SELECT us.idUsers,us.firstName, us.middleName, us.secondName, kr.idRole";
		sql += " FROM users us JOIN usersrole ur ON us.idUsers = ur.idUsers JOIN role kr ON kr.idRole = ur.idRole ";
		sql += " WHERE us.idCompany = ?";
		sql += " AND us.idBranch = ?";
		sql +=sqlRole;
		SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<UserObjDto> results= query
				 .addScalar("idUsers", Hibernate.INTEGER)
				 .addScalar("firstName", Hibernate.STRING)
				 .addScalar("middleName", Hibernate.STRING)
				 .addScalar("secondName", Hibernate.STRING)
				 .addScalar("idRole", Hibernate.INTEGER)
				 .setInteger(0, idCompany)
				 .setInteger(1, idBranch)
		        .setResultTransformer(Transformers.aliasToBean(UserObjDto.class)).list();
		return results; 
	
	}
}
