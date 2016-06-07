package com.baijia123.generic;

import java.util.Collection;

public class Page<T> {
	private Collection<T> entities; // ��ǰҳ��¼
	private int entityCount; // �ܼ�¼��

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
