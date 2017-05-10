package com.github.danielfernandez.matchday.business;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
@TypeAlias("team")
public class Team {

    @Id private String code = null;
    private String name = null;



    public Team() {
        super();
    }

    public Team(final String code, final String name) {
        super();
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Team{" +
                "code='" + this.code + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }

}
