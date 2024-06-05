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

    public Greeting(long id, String title,String content) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

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
