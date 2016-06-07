package com.baijia123.extension;

import java.util.Set;

public class ServerStatus1 {
	public final Set<String> users;
	public final Set<String> queries;

	public ServerStatus1(Set<String> users, Set<String> queries) {
		super();
		this.users = users;
		this.queries = queries;
	}

	public synchronized void addUser(String u) {
		users.add(u);
	}

	public synchronized void addQuery(String q) {
		queries.add(q);
	}

	public synchronized void removeUser(String u) {
		users.remove(u);
	}

	public synchronized void removeQuery(String q) {
		queries.remove(q);
	}
}
