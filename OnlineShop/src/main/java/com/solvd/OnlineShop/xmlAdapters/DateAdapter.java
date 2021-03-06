package com.solvd.OnlineShop.xmlAdapters;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	 
    private static final ThreadLocal<DateFormat> dateFormat 
      = new ThreadLocal<DateFormat>() {
 
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
 
    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.get().parse(v);
    }
 
    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.get().format(v);
    }
}