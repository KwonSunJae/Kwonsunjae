package com.kyeongbuk.mongie;

/**
 * Created by DGCOM on 2018-02-04.
 */

public class Notice {

    String notice;
    String name;
    String date;
    String seeview;

    public Notice(String notice, String name, String date, String seeview) {
        this.notice = notice;
        this.name = name;
        this.date = date;
        this.seeview = seeview;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeeview() {
        return seeview;
    }

    public void setSeeview(String seeview) {
        this.seeview = seeview;
    }
}

