package com.smarthall.service;

import com.smarthall.model.Notice;
import com.smarthall.model.Student;
import com.smarthall.repository.NoticeRepository;

public class NoticeService {

    private NoticeRepository noticeRepository;

    public NoticeService() {
        noticeRepository = new NoticeRepository();
    }

    // Create and save a new notice
    public Notice createNotice(String noticeText, String date) {

        if (noticeText == null || date == null) {
            return null;
        }

        Notice notice = new Notice(
                noticeText,
                date
        );

        boolean saved =
                noticeRepository.saveNotice(notice);

        if (!saved) {
            return null;
        }

        return notice;
    }

    // Check whether a student can view notices
    public boolean canViewNotice(Student student) {

        if (student == null) {
            return false;
        }

        return student.isRoomAssigned();
    }
     public void viewAllNotices() {

        noticeRepository.displayAllNotices();
    }
}