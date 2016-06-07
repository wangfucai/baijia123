package com.baijia123.generic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestGson<T> {
	public static void main(String[] args) throws IOException {
		// ��װ����
		Page<People> peoplePage = new Page<People>();
		List<People> pList = new ArrayList<People>();
		People p = new People();
		p.setName("Tom");
		p.setAge(18);
		pList.add(p);
		peoplePage.setEntities(pList);
		peoplePage.setEntityCount(pList.size());

		Page<People> newPage = getPage(peoplePage,
				new HashMap<String, Object>(), "", People.class);
		System.out.println(newPage == peoplePage);// false��ʾ�������ɵĶ���
		System.out.println(newPage.getEntities());
	}

	public static <T> Page<T> getPage(Page<T> page,
			Map<String, Object> parameter, String path, Class<T> clazz)
			throws IOException {
		if (parameter == null) {
			parameter = new HashMap<String, Object>();
		}
		parameter.put("page", page.getEntities());
		parameter.put("limit", page.getEntityCount());

		Gson gson = new Gson();
		// ����תjson
		String pageStr = gson.toJson(page);

		// ֱ��������ַ���ת����ע�⣬���������ɵ��µĶ���
		Page<T> newPage = gson.fromJson(pageStr, new TypeToken<Page<T>>() {}.getType());

		return newPage;
	}
}
