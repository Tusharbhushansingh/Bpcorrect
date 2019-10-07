package com.sentinel.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sentinel.constant.CustomQuery;
import com.sentinel.generic.dao.GenericDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
@Transactional
public class UsersInformationDao extends GenericDao {


	public Object getUsersBasicDetails(Integer userId) {
		try {
			
			Object values[]={userId};

			Session session = getSession();
			
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Common.get_users_info, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
