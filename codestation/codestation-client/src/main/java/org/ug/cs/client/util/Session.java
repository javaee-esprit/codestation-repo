package org.ug.cs.client.util;

import java.util.HashMap;
import java.util.Map;

public class Session {

	private Map<String, Object> storage;

	private static Session instance;

	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		return instance;
	}

	private Session() {
		storage = new HashMap<>();
	}

	public void store(String key, Object value) {
		storage.put(key, value);
	}

	public Object retrieve(String key) {
		return storage.get(key);
	}

}
