package com.baijia123.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Json1Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new LinkedHashMap<>();
		// ClientDto cd = new ClientDto();
		// cd.setBacklog(100);
		// cd.setInterval(300);
		result.put("code", 0);
		result.put("message", "success");
//		Map<String, Object> data = new HashMap<>();
//		data.put("slblist", "10.0.20.5, 10.0.20.6, 10.0.20.7");
//		result.put("data", data);
	//	result.put("data", "10.0.20.5, 10.0.20.6, 10.0.20.7");
		List<IpInfo> list = new ArrayList<>();
		list.add(new IpInfo("10.0.20.5"));
		list.add(new IpInfo("10.0.20.6"));
		list.add(new IpInfo("10.0.20.7"));
		result.put("data", list);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println(ret);
	}
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	static private class IpInfo {
		private String ip;
	}
}
