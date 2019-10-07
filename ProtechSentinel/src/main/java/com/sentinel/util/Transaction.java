package com.sentinel.util;

import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import java.util.Random;

public class Transaction {
	static String newOne;
public static void main(String args[]) {

      /*      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date  = new Date();
            try {
              Thread.sleep(1);                 
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }


            Calendar requestCal = Calendar.getInstance();
            requestCal.setTime(date);

            newOne = requestCal.getWeekYear() + "" + String.format("%15s", requestCal.getTimeInMillis()+"").replace(' ', '0');

            System.out.println(newOne); */               

    }

public String getTransaction() {
	 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date  = new Date();
     try {
       Thread.sleep(1);                 
     } catch(InterruptedException ex) {
         Thread.currentThread().interrupt();
     }


     Calendar requestCal = Calendar.getInstance();
     requestCal.setTime(date);

     newOne = requestCal.getWeekYear() + "" + String.format("%15s", requestCal.getTimeInMillis()+"").replace(' ', '0');

	return newOne;
}
}