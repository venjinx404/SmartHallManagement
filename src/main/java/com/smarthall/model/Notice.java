package com.smarthall.model;

public class Notice {
    private String noticeText;
    private String date;

    public Notice(String noticeText, String date)
    {
        this.noticeText=noticeText;
        this.date=date;
    }
       public String getDate() {
        return date;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }
}
