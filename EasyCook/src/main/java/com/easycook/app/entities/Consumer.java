package com.easycook.app.entities;

import org.bson.types.ObjectId;

public class Consumer extends Guest {
    public static String COLLECTION_NAME = "consumers";

    private ObjectId _id;
    private String email;
    private String password;

    public Consumer(int id, String name) {
        super(id, name);
    }

    public Consumer(String name) {
        super(name);
    }

    public Consumer(int id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

    public ObjectId get_id() {
		return _id;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
