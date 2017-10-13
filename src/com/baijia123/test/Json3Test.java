package com.baijia123.test;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Json3Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("code", 0);
		map.put("message", "success");
		Gson gson = new Gson();
		HiveArgs hive = new HiveArgs("news", 10000, "172.31.101.246", "ad", 10000, "172.31.101.246", "sdk", 10000, "172.31.101.246", 
				"push", 10000, "172.31.101.246");
		//map.put("data", gson.toJson(hive));
		map.put("data", hive);
		String result = gson.toJson(map);
		System.out.println(result);
	}

	@Getter
	@Setter
	@AllArgsConstructor
	static private class HiveArgs {
		private String logdb;
		private Integer logport;
		private String loghost;
		private String addb;
		private Integer adport;
		private String adhost;
		private String sdkdb;
		private Integer sdkport;
		private String sdkhost;
		private String pushdb;
		private Integer pushport;
		private String pushhost;
	}
}
