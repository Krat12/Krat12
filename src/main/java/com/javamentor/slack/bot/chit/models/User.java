package com.javamentor.slack.bot.chit.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 50, message = "email должен быть короче 50 символов")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Size(min = 1)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = 1)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 6, max = 60, message = "Пароль должен быть больше 6 символов и меньше 30")
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "birthday", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateType")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean enabled = true;

    @Column(name = "dtype")
    private String type;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
