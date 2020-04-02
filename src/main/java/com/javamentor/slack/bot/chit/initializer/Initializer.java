package com.javamentor.slack.bot.chit.initializer;


import com.javamentor.slack.bot.chit.models.StudentCourseInfo;
import com.javamentor.slack.bot.chit.models.User;
import com.javamentor.slack.bot.chit.repository.PayStudentRep;
import com.javamentor.slack.bot.chit.repository.StudentCourseInfoRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Initializer {

    private final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    @Autowired
    private PayStudentRep payStudentRep;

    @Autowired
    private StudentCourseInfoRep studentCourseInfoRep;

    private void init() {
        List<User> users = payStudentRep.getAllEnableUser();
        for (User user: users) {
            List<StudentCourseInfo> studentCourseInfos = studentCourseInfoRep.getStudentCourseInfosByStudent(user.getId());
            if (!studentCourseInfos.isEmpty()) {
                if (studentCourseInfos.size() == 3) {
                    for (StudentCourseInfo studentCourseInfo: studentCourseInfos) {
                        if (studentCourseInfo.getCourse().getId() == 3) {
                            if (studentCourseInfo.getCompleted()) {
                                addStudentToSlackChannel(user.getEmail(), "java-preproject");
                            }else {
                                addStudentToSlackChannel(user.getEmail(), "java-web");
                            }
                        }
                    }
                }else {
                    addStudentToSlackChannel(user.getEmail(), "java-core");
                }

            }
        }

	}


	private void addStudentToSlackChannel (String studentEmail, String channelName) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", studentEmail);
        map.add("channelName", channelName);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, headers);
        try {
            restTemplate.postForEntity("https://bot.java-mentor.com/bot/student/channel/invite", request, String.class);
            LOGGER.debug("Студент с email: {} добавлен в канал {}", studentEmail, channelName);
        } catch (Exception e) {
            LOGGER.debug("При добавлении Студента с email: {} в канал {} возникла ошибка", studentEmail, channelName, e);
        }
    }
}
