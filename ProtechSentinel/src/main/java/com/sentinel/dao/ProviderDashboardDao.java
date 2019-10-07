package com.sentinel.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sentinel.constant.CustomQuery;
import com.sentinel.generic.dao.GenericDao;
import com.sentinel.model.WearableDevice.AddWearableDevice;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
@Transactional
public class ProviderDashboardDao extends GenericDao {

	public Object dashboardProviderGetbpPulseoxReading(Integer userId,
			Integer patientType, Integer dayno) {
		try {

			Object values[] = { userId, patientType, dayno };

			Session session = getSession();
			Object obj = executeStoreProcedure(
					session,
					null,
					CustomQuery.ProviderDashboard.dashboard_provider_get_bp_pulseox_reading,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object dashboardProviderGetIncreadeDecreaseBloodpressure(
			Integer userId, Integer patientType, Integer dayno) {
		try {

			Object values[] = { userId, patientType, dayno };

			Session session = getSession();
			Object obj = executeStoreProcedure(
					session,
					null,
					CustomQuery.ProviderDashboard.dashboard_provider_get_increade_decrease_bloodpressure,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object dashboardProviderGetPulseoxWarning(Integer userId,
			Integer patientType, Integer dayno) {
		try {

			Object values[] = { userId, patientType, dayno };

			Session session = getSession();
			Object obj = executeStoreProcedure(
					session,
					null,
					CustomQuery.ProviderDashboard.dashboard_provider_get_pulseox_warning,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object dashboardProviderGetTotalUnrolledPatient(Integer userId,
			Integer patientType) {
		try {

			Object values[] = { userId, patientType };

			Session session = getSession();
			Object obj = executeStoreProcedure(
					session,
					null,
					CustomQuery.ProviderDashboard.dashboard_provider_get_total_unrolled_patient,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object dashboardProviderHypertensive(Integer userId,
			Integer patientType, Integer dayno) {
		try {

			Object values[] = { userId, patientType, dayno };

			Session session = getSession();
			Object obj = executeStoreProcedure(
					session,
					null,
					CustomQuery.ProviderDashboard.dashboard_provider_hypertensive,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object dashboardProviderMedication(Integer userId,
			Integer patientType, Integer dayno) {
		try {

			Object values[] = { userId, patientType, dayno };

			Session session = getSession();
			Object obj = executeStoreProcedure(
					session,
					null,
					CustomQuery.ProviderDashboard.dashboard_provider_medication,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
