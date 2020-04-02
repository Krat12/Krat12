package com.javamentor.slack.bot.chit.repository;

import com.javamentor.slack.bot.chit.models.StudentCourseInfo;

import java.util.List;

public interface StudentCourseInfoRep {

    List<StudentCourseInfo> getStudentCourseInfosByStudent(long studentId);
}
