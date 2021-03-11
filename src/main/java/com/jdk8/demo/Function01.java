package com.jdk8.demo;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Function01 {

    public static void main(String[] args) {

        Function timestampToDate = timestamp -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(new Date(timestamp));
        };


        System.out.println("s: " + timestampToDate.apply(new Date().getTime()));
    }

    interface Function {
        String apply(long timestamp);
    }
}
