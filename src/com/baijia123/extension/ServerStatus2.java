package com.baijia123.extension;

import java.util.Set;

public class ServerStatus2 {
	public final Set<String> users;
	public final Set<String> queries;

	public ServerStatus2(Set<String> users, Set<String> queries) {
		super();
		this.users = users;
		this.queries = queries;
	}

	public void addUser(String u) {
		synchronized (users) {
			users.add(u);
		}

	}

	public synchronized void addQuery(String q) {
		synchronized (queries) {
			queries.add(q);
		}

	}

	public synchronized void removeUser(String u) {
		synchronized (users) {
			users.remove(u);
		}
	}

	public synchronized void removeQuery(String q) {
		synchronized (queries) {
			queries.remove(q);
		}
	}
}
