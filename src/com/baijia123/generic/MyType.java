package com.baijia123.generic;

class MyType<T> {
	// �Զ��巺����

	public <X> MyType<X> getMyTypeInstance(Class<X> clazz) {
		return new MyType<X>();
	}

	public void test() {
		MyType<Integer> type = getMyTypeInstance(Integer.class);
	}
}
