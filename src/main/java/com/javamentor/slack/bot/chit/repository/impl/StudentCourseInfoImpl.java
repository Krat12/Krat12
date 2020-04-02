package com.javamentor.slack.bot.chit.repository.impl;

import com.javamentor.slack.bot.chit.models.StudentCourseInfo;
import com.javamentor.slack.bot.chit.repository.StudentCourseInfoRep;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentCourseInfoImpl implements StudentCourseInfoRep {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<StudentCourseInfo> getStudentCourseInfosByStudent(long studentId) {
        return entityManager.createQuery(
                "SELECT sci FROM StudentCourseInfo sci WHERE sci.student.id = :studentId", StudentCourseInfo.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }
}
