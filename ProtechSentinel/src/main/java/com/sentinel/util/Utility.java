package com.sentinel.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sentinel.annotations.GetIt;
import com.sentinel.annotations.SkipIt;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.OneToMany;

/**
 * Created by Jlavyan on 3/18/17.
 */
public class Utility {

    public static Integer LOSANGELES_TIMEZONE = 7;

    /*public static Gson defaultGson(){
        Gson gson = new GsonBuilder()
                .serializeNulls().setDateFormat(Constant.DATE_FORMAT).create();

        return gson;
    }*/

    public static Gson getGsonObject() {

    	Gson gson = new GsonBuilder().serializeNulls().setDateFormat(Constants.DATE_FORMAT).addSerializationExclusionStrategy(new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				//

				if (f.getAnnotation(GetIt.class) != null) {
					return false;
				} else if (f.getAnnotation(OneToMany.class) != null) {
					return true;
				}else if (f.getAnnotation(SkipIt.class) != null) {
					return true;
				}else {
					return false;
				}
				
				
			}

			@Override
			public boolean shouldSkipClass(Class<?> arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		}).create();

		return gson;
	}
    
    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    public static Date dateFromInterval(Long interval){
        if(interval == 0){
            return null;
        }else {
            return new Date(interval);
        }
    }

    public static Date getLastWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 1);
        return c.getTime();
    }

    public static Date getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getFirstYear() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    public static Date dateFromHour(Integer hour) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar c = Calendar.getInstance(timeZone);

        c.set(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    public static Date endOfDay(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar c = Calendar.getInstance(timeZone);
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 60);
        return c.getTime();
    }


    public static Integer hourOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }


    public static Date dateFromString(String dateString,String format){
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date startDate = (Date)formatter.parse(dateString);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date beforeDays(Integer interval){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -interval);
        cal.add(Calendar.HOUR_OF_DAY, 0);

        return cal.getTime();
    }

    public static Date beforeHours(Date date,Integer hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, - hours);

        return cal.getTime();
    }

    public static Integer getUTCHour(){
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar c = Calendar.getInstance(timeZone);
        Date date = c.getTime();

        return Integer.valueOf(stringFromDate(date,"HH",timeZone));
    }


    public static String stringFromDate(Date date, String format,TimeZone timeZone) {
        String dateStr = null;

        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(timeZone);

        try {
            dateStr = df.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateStr;
    }

    public static int calculateAge(Date birthdate) {
        if(birthdate == null)
            return Integer.MIN_VALUE;
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);
        Calendar today = Calendar.getInstance();

        int yearDifference = today.get(Calendar.YEAR)
                - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            yearDifference--;
        } else {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birth
                    .get(Calendar.DAY_OF_MONTH)) {
                yearDifference--;
            }

        }

        return yearDifference;
    }

    public static String UUID(){
        return UUID.randomUUID().toString();
    }

    public static Long getRandom(Long min, Long max) {
        return (long) ( (Math.random() * (max + 1 - min)) + min);
    }

    public static String getExtension(String path){
        String extension = "";

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i+1);
        }

        return extension;
    }




}
