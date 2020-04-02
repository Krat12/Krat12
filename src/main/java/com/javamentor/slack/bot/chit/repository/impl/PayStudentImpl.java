package com.javamentor.slack.bot.chit.repository.impl;

import com.javamentor.slack.bot.chit.models.User;
import com.javamentor.slack.bot.chit.repository.PayStudentRep;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PayStudentImpl implements PayStudentRep {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<User> getAllEnableUser() {
        return entityManager.createQuery("SELECT ps FROM User ps WHERE ps.enabled = true AND ps.type = :PayStudent", User.class)
                .setParameter("PayStudent","PayStudent")
                .getResultList();
    }
}
