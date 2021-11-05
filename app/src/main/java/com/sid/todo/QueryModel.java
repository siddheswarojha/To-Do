package com.sid.todo;

public class QueryModel {
    String Query;
    String topic;

    public QueryModel(String query, String topic, String email) {
        Query = query;
        this.topic = topic;
        Email = email;
    }

    public QueryModel() {
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    String Email;

}
