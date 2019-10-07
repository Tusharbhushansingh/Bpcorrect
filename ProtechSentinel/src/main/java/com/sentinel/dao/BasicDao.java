package com.sentinel.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sentinel.constant.CustomQuery;
import com.sentinel.generic.dao.GenericDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
@Transactional
public class BasicDao extends GenericDao {

	public Object forgotPassword(String email) {
		try {

			Object values[] = { email };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Common.forgot_password, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object verifyEmail(String email) {
		try {

			Object values[] = { email };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Common.verify_email, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object emailExists(String email) {
		try {

			Object values[] = { email };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Common.email_exists, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object resetPassword(String code, String password) {
		try {

			Object values[] = { code, password };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Common.reset_password, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
