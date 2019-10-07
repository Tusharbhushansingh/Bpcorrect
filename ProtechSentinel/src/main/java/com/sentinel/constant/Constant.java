package com.sentinel.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {

	public static final int nurseType = 0;
	public static final int doctorType = 1;
	public static final byte isRemoveTrue = 0;
	public static final byte isRemoveFalse = 1;

	public static final String MeasurementTable = "measurement";
	public static final String MeasurementStatusTable = "measurement_status";

	public static List<String> createSqlQuery(List<Map<String, String>> list) {
		try {		
			
			if (list.size() <= 0) {
				return null;
			}
			
			List<String> queryList=new ArrayList<String>();

			StringBuilder builder = new StringBuilder();
			
			StringBuilder buildStatus = new StringBuilder();
			
			builder.append("insert into " + MeasurementTable + " (");
			builder.append(list.get(0).keySet().toString().replace("[", "")
					.replace("]", "")
					+ " ) values ");
			
			
			buildStatus.append("replace into " + MeasurementStatusTable + " (");
			buildStatus.append(list.get(0).keySet().toString().replace("[", "")
					.replace("]", "")
					+ " ) values ");

			for (Map<String, String> map : list) {
				
				String value="('"
						+ map.values().toString().replace("[", "")
						.replace("]", "").replace(", ", "','") + "'),";
				
				builder.append(value.replace("'null'", "null"));
				
				String valueStatus="('"
						+ map.values().toString().replace("[", "")
						.replace("]", "").replace(", ", "','") + "'),";
				
				buildStatus.append(valueStatus.replace("'null'", "0"));
				
			}
			
			builder.deleteCharAt(builder.lastIndexOf(","));
			buildStatus.deleteCharAt(buildStatus.lastIndexOf(","));
			
			queryList.add(builder.toString());
			queryList.add(buildStatus.toString());

			System.out.println(builder);
			System.out.println(buildStatus);
			return queryList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object setupChartData(Integer dayno)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		
		try {
			
			if(dayno==1)
			{
				
			}
			else{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
