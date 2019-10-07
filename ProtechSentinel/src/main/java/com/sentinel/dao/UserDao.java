package com.sentinel.dao;

import java.util.Random;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sentinel.generic.dao.GenericDao;

@SuppressWarnings("rawtypes")
@Repository
@Transactional
public class UserDao extends GenericDao {

	public String getAuthorityByUserName(String username) {

		Session session = getSession();

		String authority = (String) session
				.createSQLQuery(
						"select a.access_role from loginuser u join authority a on a.id=u.authorityId where u.username = :username")
				.setParameter("username", username).uniqueResult();

		return authority;
	}

	public Integer getIDAuthorityByUserName(String username) {

		Session session = getSession();

		Integer id = (Integer) session
				.createSQLQuery(
						"select u.id from loginuser u join authority a on a.id=u.authorityId where u.username = :username")
				.setParameter("username", username).uniqueResult();

		return id;
	}

	public Object getEnableAuthorityByUserName(String username) {

		Session session = getSession();

		Object id = session
				.createSQLQuery(
						"select u.enable from loginuser u join authority a on a.id=u.authorityId where u.username = :username")
				.setParameter("username", username).uniqueResult();

		return id;
	}
	
	public Object getOtpByEmail(int lenght) {
		
		
		String numbers="0123456789";
		Random r=new Random();
		char[] otp1=new char[lenght];
		for(int i=0;i<lenght;i++)
		{
			otp1[i]=numbers.charAt(r.nextInt(numbers.length()));
		}
		
		Session session=getSession();
		Object otp=session.createQuery("select u.otp from loginuser u join authority a on a.id=u.authorityId where u.otp = :otp").setParameter("otp",otp1).uniqueResult();
		
		return otp;
		
	}

}
