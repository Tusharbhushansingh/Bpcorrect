package com.sentinel.constant;

public class CustomQuery {

	public static class Provider {

		public static final String GET_ALL_PATIENT_BY_PROVIDER_ID = "call get_all_patient_by_practice(?,?)";
		public static final String GET_ALL_PATIENT_BY_ADMIN_ID = "call get_all_patient_by_superadmin(?,?)";
		public static final String GET_COMMON_PATIENT_BY_PROVIDER = "call get_assigned_patient(?,?)";
		public static final String ADD_LOGHOURS_PATIENT_BY_PROVIDER = "call add_loghours_patient(?,?,?,?,?,?,?,?)";
		public static final String GET_ALL_PROVIDER_BY_PRACTICE_ID = "call get_all_provider_by_practice(?,?)";
		public static final String GET_PRACTICE_ADMIN_CHART = "call get_practice_admin_chart(?,?,?)";
		public static final String GET_ALL_STAFF_BY_PRACTICE_ID = "call get_all_staff_by_practice(?,?)";
		public static final String GET_COMMON_STAFF = "call get_common_staff(?,?)";
		public static final String GET_ALL_Practice_BY_PROVIDER_ID = "call get_all_practice_org(?)";
		public static final String GET_QUICK_STATS = "call get_quick_stats(?)";

		public static final String get_vital_last_visit_by_patient = "call get_vital_last_visit_by_patient(?,?)";
		public static final String get_blood_pressure_by_patient = "call get_blood_pressure_by_patient(?)";
		public static final String get_piulseox_by_patient = "call get_piulseox_by_patient(?)";
		public static final String get_pulse_by_patient = "call get_pulse_by_patient(?)";
		public static final String get_all_providers_by_provider = "call get_all_providers_by_provider(?)";
		public static final String apply_coupon = "call applycoupon(?)";
	}

	public static class ProviderDashboard {

		public static final String dashboard_provider_get_bp_pulseox_reading = "call dashboard_provider_get_bp_pulseox_reading(?,?,?)";
		public static final String dashboard_provider_get_increade_decrease_bloodpressure = "call dashboard_provider_get_increade_decrease_bloodpressure(?,?,?)";
		public static final String dashboard_provider_get_pulseox_warning = "call dashboard_provider_get_pulseox_warning(?,?,?)";
		public static final String dashboard_provider_get_total_unrolled_patient = "call dashboard_provider_get_total_unrolled_patient(?,?)";
		public static final String dashboard_provider_hypertensive = "call dashboard_provider_hypertensive(?,?,?)";
		public static final String dashboard_provider_medication = "call dashboard_provider_medication(?,?,?)";

	}

	public static class Common {

		public static final String get_patient_information = "call get_patient_information(?)";
		public static final String get_quick_action_patients = "call quick_action_patients(?,?,?)";
		public static final String update_enable_disable = "call update_enable_disable(?,?)";
		public static final String delete_protocol = "call delete_protocol(?)";
		public static final String get_inactive_patient = "call get_inactive_patient(?)";
		public static final String update_wave_off = "call update_wave_off(?,?)";

		public static final String ADD_PATIENT = "call add_patient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String REGSITER_PATIENT = "call register_patient(?,?)";

		public static final String ADD_PROVIDER = "call add_provider(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String Share_Reading = "call share_reading(?,?,?)";
		
		public static final String ADD_NEWUSER = "call add_newuser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		public static final String get_users_info = "call get_users_info(?)";

		public static final String add_notes = "call add_notes(?,?,?,?,?,?,?)";
		public static final String get_log_hours = "call get_logged_hours(?,?,?,?,?,?,?)";
		public static final String get_log_hours_by_patient = "call get_logged_hours_by_patient(?,?,?,?,?)";
		public static final String get_log_hours_by_patient_filter_by_month = "call get_loghours_filter_for_patient_by_month(?,?,?)";
		public static final String get_ehc_data = "call get_ehc_protocol_data(?,?)";
		public static final String get_ehc_data_by_patient = "call get_current_ehc_protocol_data_by_patient(?)";
		public static final String get_previous_protocol_data_by_patient = "call get_previous_protocol_details(?)";
		public static final String getstates = "call get_states()";
		public static final String getLogs = "call get_logs()";
		public static final String add_vital = "call add_vital(?,?,?,?,?,?,?,?)";
		public static final String user_mapped_with_healthgroup = "call create_update_user_mapped_with_healthgroup(?,?,?)";
		public static final String user_mapped_with_user = "call create_update_user_mapped_with_user(?,?,?)";

		public static final String get_health_group_details_by_user = "call get_health_group_details_by_user(?)";
		public static final String get_info = "call getinfo(?)";

		// New APIs Calls

		public static final String add_admin_provider = "call add_admin_provider(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String register_practice_admin = "call register_practice_admin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String update_admin_provider = "call update_admin_provider(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String add_admin = "call add_admin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String update_admin = "call update_admin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String change_password = "call change_password(?,?)";

		public static final String add_officestaff = "call add_officestaff(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String add_physiologicaldata = "call add_graph_data(?,?,?,?)";
		public static final String update_officestaff = "call update_officestaff(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		public static final String update_provider = "call update_provider(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String update_patient = "call update_patient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		public static final String forgot_password = "call forgot_password(?)";
		public static final String verify_email = "call activate_account(?)";
		public static final String email_exists = "call verify_email(?)";

		public static final String add_allergy_comorbidity = "call add_allergy_comorbidity(?,?,?,?,?,?)";
		public static final String update_allergy_comorbidity = "call update_allergy_comorbidity(?,?,?,?,?)";
		public static final String patient_mapping_allergy_comorbidity = "call create_update_patient_allergy_comorbidity_mapping(?,?,?)";

		public static final String add_medication = "call add_medication(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String update_medication = "call update_medication(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String patient_mapping_medication = "call create_update_patient_medication_mapping(?,?,?)";

		public static final String view_notes = "call get_notes(?,?)";

		public static final String logout = "";

		public static final String get_patient_medication_by_patient = "call get_patient_medication_by_patient_id(?)";
		public static final String get_patient_allergy_comorbidity_by_patient = "call get_patient_allergy_comorbidity_by_patient_id(?,?)";

		public static final String get_all_medication = "call get_all_medication(?)";

		public static final String set_Graduated_patient = "call set_Graduated_patient(?,?)";

		public static final String get_health_group = "call get_health_group()";

		public static final String add_appointment_time = "call add_appointment_time(?,?,?)";
		public static final String create_ehc_protocol = "call create_ehc_protocol(?,?,?,?,?,?)";
		public static final String get_appointment_time_by_patient_id = "call get_appointment_time_by_patient_id(?,?)";

		public static final String get_all_patient_by_provider_id_filter = "call get_all_patient_by_provider_id_filter(?,?,?,?,?,?,?,?)";

		public static final String get_all_wearable_device = "call get_all_wearable_device()";

		public static final String get_chart_data_according_to_user = "call get_chart_data_according_to_user(?,?,?,?)";
		public static final String get_protocol_data_according_to_user = "call get_all_protocol_by_patient(?)";
		public static final String update_total_readings = "call count_total_readings(?,?)";
		public static final String add_patient_reading = "call add_reading_data(?,?,?,?,?,?,?,?)";
		public static final String get_device_list = "call get_device_list()";

		public static final String get_master_comorbidity = "call get_master_comorbidity()";
		public static final String remove_allergy_comorbidity = "call remove_allergy_comorbidity(?)";

		public static final String remove_user_mapped_with_user_by_userId = "call remove_user_mapped_with_user_by_userId(?)";
		
		public static final String get_master_medication = "call get_master_medication()";

		public static final String reset_password="call reset_password(?,?)";
		
		
		public static final String add_notes_comments="call add_notes_comments(?,?,?)";
		public static final String add_payment_details="call add_payment_details(?,?,?,?,?,?,?,?,?,?,?)";
		public static final String add_payment_table_details="call add_payment_table_data(?,?,?,?,?,?,?,?,?,?,?)";
		public static final String upload_profile_image="call get_profile_image(?,?)";
		public static final String update_payment_final_status="call update_payment_final_status(?,?,?,?,?,?,?,?,?,?)";
		
		public static final String get_notes_comments="call get_notes_comments(?,?)";

		public static final String get_patient_setting="call get_patient_setting(?)";
		public static final String set_timezone="call set_timezone(?,?)";
		public static final String get_timezone="call get_timezone_detail_by_practice(?)";
		public static final String get_country="call get_country_zone()";

		public static final String update_patient_setting="call update_patient_setting(?,?,?)";
		
		public static final String create_pdf_with_patient_details="call create_pdf_with_patient_details(?)";
		
		public static final String get_all_users_by_user="call get_all_users_by_user(?,?)";
		public static final String contact_us="call contact_us(?,?,?,?,?)";
		public static final String update_protocol_status="call update_protocol_status()";
		

	}

	public static class Admin {
		public static final String add_wearable_device = "call add_wearable_device(?,?,?,?,?,?,?,?)";
		public static final String update_wearable_device = "call update_wearable_device(?,?,?,?,?,?,?)";
		public static final String practice_org_mapping = "call practice_org_mapping(?,?)";
		public static final String patient_mapping_wearable_device = "call create_update_patient_device_mapping(?,?,?)";
		public static final String get_patient_wearable_device_by_patient = "call get_patient_wearable_device_by_patient_id(?)";
		
		public static final String add_update_coupon_code = "call add_update_coupon(?,?,?,?,?,?)";
		public static final String get_coupon_list = "call get_coupon_list(?,?)";
		public static final String activate_deactivate_coupon = "call activate_deactivate(?,?)";
		public static final String add_charge_amount = "call add_bill_amount(?)";
		public static final String get_charge_amount = "call get_bill_amount()";
		
		public static final String get_all_payment_record = "call get_all_payment_record(?,?,?)";
		public static final String add_health_group_by_sentinel_admin = "call add_health_group_by_sentinel_admin(?,?,?,?,?,?,?)";
		public static final String update_health_group_by_sentinel_admin = "call update_health_group_by_sentinel_admin(?,?,?,?,?,?,?)";
		public static final String get_health_group_by_sentinel_admin = "call get_health_group_by_sentinel_admin(?)";
		public static final String get_chart_data = "call get_superadmin_chart(?,?)";
		
		public static final String add_master_medication = "call add_master_medication(?,?,?,?,?,?)";
		public static final String update_master_medication = "call update_master_medication(?,?,?,?,?,?,?)";
	}

}
