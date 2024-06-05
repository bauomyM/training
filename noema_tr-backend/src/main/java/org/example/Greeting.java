package org.example;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Document
public class Greeting {

    @Id
    private final long id;

    private final String content;
    private final String title;
    private final String name;
    private final String lastName;

    public Greeting(long id, String title,String name,String lastName,String content) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.name=name;
        this.lastName =lastName;

    }

    public String getName() {return name;}
    public String getLastName() {return lastName;}
    public String getTitle() {
        return title;
    }
    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
}
