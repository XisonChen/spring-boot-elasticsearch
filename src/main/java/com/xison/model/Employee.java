package com.xison.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * created by Xison Chen
 * on 2017/11/12-16:55
 */
@Document(indexName = "megacorp", type = "employee")
public class Employee {

    private Long id;

    private String first_name;

    private String last_name;

    private Integer age;

    private String about;

    private List<String> interests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
