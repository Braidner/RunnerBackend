package org.braidner.runner.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 17/08/2016
 * Time: 14:11
 */
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
