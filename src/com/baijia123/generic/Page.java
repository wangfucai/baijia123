package com.baijia123.generic;

import java.util.Collection;

public class Page<T> {
	private Collection<T> entities; // 当前页记录
	private int entityCount; // 总记录数

	public Collection<T> getEntities() {
		return entities;
	}

	public void setEntities(Collection<T> entities) {
		this.entities = entities;
	}

	public int getEntityCount() {
		return entityCount;
	}

	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
}
