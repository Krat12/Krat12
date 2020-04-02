package com.javamentor.slack.bot.chit.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "slack_channel_name")
    private String slackChannelName;

    public Course(String name, String slackChannelName) {
        this.name = name;
        this.slackChannelName = slackChannelName;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlackChannelName() {
        return slackChannelName;
    }

    public void setSlackChannelName(String slackChannelName) {
        this.slackChannelName = slackChannelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(slackChannelName, course.slackChannelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slackChannelName);
    }
}
