package com.sentinel.util;


public class Period {

	public Integer start;
    public Integer end;

    public Period(Integer s,Integer e){
        start = s;
        end = e;
    }
    public Boolean contains(Period period){
        if(period.start >= start && period.end < end)
            return true;
        return false;
    }
	
}
