package com.smarthall.service;

import com.smarthall.model.Notice;
import com.smarthall.model.Student;

public class NoticeService {

    // Create a new notice
    public Notice createNotice(String noticeText, String date) {

        if (noticeText == null || date == null) {
            return null;
        }

        Notice notice = new Notice(noticeText, date);

        return notice;
    }


    // Check whether a student can view notices
    public boolean canViewNotice(Student student) {

        if (student == null) {
            return false;
        }

        return student.isRoomAssigned();
    }
}