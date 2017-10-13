package com.baijia123.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Json2Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("code", 0);
		map.put("message", "success");
		
		Map<String, Object> data = new LinkedHashMap<>();
		ClientDto cd = new ClientDto();
		cd.setBacklog(100);
		cd.setInterval(300);
		data.put("clientDto", cd);
		List<RunConfigDto> list = new ArrayList<>();
		list.add(new RunConfigDto("slbIpList", 7200));
		list.add(new RunConfigDto("eventPolicy", 7200));
		List<String> testList = new ArrayList<>();
		testList.add("123");
		testList.add("345");
		testList.add("666");
		data.put("runConfigDto", testList);
		data.put("list", list);
		map.put("data", data);
		
		//map.put("data", "{\"clientDto\":{\"backlog\":100,\"interval\":300},\"runConfigDto\":[{\"name\":\"slbIpList\",\"value\":7200},{\"name\":\"eventPolicy\",\"value\":7200}]}");
		Gson gson = new Gson();
		String result = gson.toJson(map);
		System.out.println(result);
		//HashMap<String, Object> revertMap = gson.fromJson(result, new TypeToken<HashMap<String, Object>>() {}.getType());
		//System.out.println(revertMap.get("data"));
		//Map<String, Object> dataMap = gson.fromJson((String)revertMap.get("data"), new TypeToken<HashMap<String, Object>>() {}.getType());;
		//System.out.println(dataMap.get("clientDto"));
		
	}

	@Getter
	@Setter
	@NoArgsConstructor
	static private class ClientDto {
		private Integer backlog;
		private Integer interval;

	}

	@Getter
	@Setter
	@AllArgsConstructor
	static private class RunConfigDto {
		private String name;
		private Integer value;
	}
}
