package com.baijia123.test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.cache.Cache;
import com.jayway.jsonpath.spi.cache.CacheProvider;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

public class JsonPathTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration.setDefaults(new Configuration.Defaults() {

		    private final JsonProvider jsonProvider = new GsonJsonProvider();
		    private final MappingProvider mappingProvider = new GsonMappingProvider();
		      
		    @Override
		    public JsonProvider jsonProvider() {
		        return jsonProvider;
		    }

		    @Override
		    public MappingProvider mappingProvider() {
		        return mappingProvider;
		    }
		    
		    @Override
		    public Set<Option> options() {
		        return EnumSet.noneOf(Option.class);
		    }
		});
		CacheProvider.setCache(new Cache() {
		    //Not thread safe simple cache
		    private Map<String, JsonPath> map = new HashMap<String, JsonPath>();

		    @Override
		    public JsonPath get(String key) {
		        return map.get(key);
		    }

		    @Override
		    public void put(String key, JsonPath jsonPath) {
		        map.put(key, jsonPath);
		    }
		});
		String json = "{\r\n" + 
				"    \"isAttackWin\": true,\r\n" + 
				"    \"defenderKillDetail\": {},\r\n" + 
				"    \"logQueueId\": \"10790215-5990\",\r\n" + 
				"    \"playerInfo2\": {\r\n" + 
				"        \"fightSummary\": {\r\n" + 
				"            \"surviveNum\": 0,\r\n" + 
				"            \"killNum\": 0,\r\n" + 
				"            \"lostPower\": 0,\r\n" + 
				"            \"lostNum\": 0,\r\n" + 
				"            \"woundedNum\": 0,\r\n" + 
				"            \"lostTrap\": 0\r\n" + 
				"        },\r\n" + 
				"        \"armyAttrInfo\": {}\r\n" + 
				"    },\r\n" + 
				"    \"battleInfo\": {\r\n" + 
				"        \"attackArmyInfo\": [\r\n" + 
				"            {\r\n" + 
				"                \"resInfo\": [\r\n" + 
				"                    {\r\n" + 
				"                        \"id\": 1002,\r\n" + 
				"                        \"num\": 1193269\r\n" + 
				"                    },\r\n" + 
				"                    {\r\n" + 
				"                        \"id\": 1003,\r\n" + 
				"                        \"num\": 0\r\n" + 
				"                    }\r\n" + 
				"                ],\r\n" + 
				"                \"allianceInfo\": {\r\n" + 
				"                    \"abbr\": \"DMR\",\r\n" + 
				"                    \"aid\": 1116266,\r\n" + 
				"                    \"name\": \"DAMAR \",\r\n" + 
				"                    \"flag\": 4\r\n" + 
				"                },\r\n" + 
				"                \"uinfo\": {\r\n" + 
				"                    \"nickName\": \"هكونا مطاطاا\",\r\n" + 
				"                    \"iconID\": 1404,\r\n" + 
				"                    \"avatar\": \"\",\r\n" + 
				"                    \"id\": 10790215,\r\n" + 
				"                    \"lv\": 38,\r\n" + 
				"                    \"coordinate\": {\r\n" + 
				"                        \"y\": 228,\r\n" + 
				"                        \"x\": 87\r\n" + 
				"                    },\r\n" + 
				"                    \"kid\": 189\r\n" + 
				"                },\r\n" + 
				"                \"detailInfo\": [\r\n" + 
				"                    {\r\n" + 
				"                        \"kill\": 0,\r\n" + 
				"                        \"survive\": 87100,\r\n" + 
				"                        \"lost\": 0,\r\n" + 
				"                        \"battleItemId\": 708,\r\n" + 
				"                        \"battleItemType\": 1,\r\n" + 
				"                        \"wounded\": 0\r\n" + 
				"                    }\r\n" + 
				"                ]\r\n" + 
				"            }\r\n" + 
				"        ],\r\n" + 
				"        \"defenceArmyInfo\": [\r\n" + 
				"            {\r\n" + 
				"                \"allianceInfo\": {\r\n" + 
				"                    \"aid\": 0\r\n" + 
				"                },\r\n" + 
				"                \"uinfo\": {\r\n" + 
				"                    \"nickName\": \"lord7167930305\",\r\n" + 
				"                    \"iconID\": 1403,\r\n" + 
				"                    \"avatar\": \"\",\r\n" + 
				"                    \"id\": 11938878,\r\n" + 
				"                    \"lv\": 1,\r\n" + 
				"                    \"coordinate\": {\r\n" + 
				"                        \"y\": 230,\r\n" + 
				"                        \"x\": 88\r\n" + 
				"                    },\r\n" + 
				"                    \"kid\": 189\r\n" + 
				"                },\r\n" + 
				"                \"detailInfo\": {}\r\n" + 
				"            }\r\n" + 
				"        ]\r\n" + 
				"    },\r\n" + 
				"    \"isRealWar\": true,\r\n" + 
				"    \"attackerPowerPlus\": {\r\n" + 
				"        \"708\": 0.02562\r\n" + 
				"    },\r\n" + 
				"    \"allUid\": [\r\n" + 
				"        10790215,\r\n" + 
				"        11938878\r\n" + 
				"    ],\r\n" + 
				"    \"isPlayerWar\": true,\r\n" + 
				"    \"attackerUid\": 10790215,\r\n" + 
				"    \"attackerKillDetail\": {\r\n" + 
				"        \"10790215\": {}\r\n" + 
				"    },\r\n" + 
				"    \"defenderUid\": 11938878,\r\n" + 
				"    \"playerInfo1\": {\r\n" + 
				"        \"allPower\": 527055.8618,\r\n" + 
				"        \"armyAttrInfo\": [\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 1,\r\n" + 
				"                \"num\": 43,\r\n" + 
				"                \"targetId\": 1,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 2,\r\n" + 
				"                \"num\": 17,\r\n" + 
				"                \"targetId\": 1,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 3,\r\n" + 
				"                \"num\": 18,\r\n" + 
				"                \"targetId\": 1,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 1,\r\n" + 
				"                \"num\": 37,\r\n" + 
				"                \"targetId\": 2,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 2,\r\n" + 
				"                \"num\": 15,\r\n" + 
				"                \"targetId\": 2,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 3,\r\n" + 
				"                \"num\": 15,\r\n" + 
				"                \"targetId\": 2,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 1,\r\n" + 
				"                \"num\": 40,\r\n" + 
				"                \"targetId\": 3,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 2,\r\n" + 
				"                \"num\": 15,\r\n" + 
				"                \"targetId\": 3,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 3,\r\n" + 
				"                \"num\": 17,\r\n" + 
				"                \"targetId\": 3,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 1,\r\n" + 
				"                \"num\": 49,\r\n" + 
				"                \"targetId\": 4,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 2,\r\n" + 
				"                \"num\": 21,\r\n" + 
				"                \"targetId\": 4,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 3,\r\n" + 
				"                \"num\": 15,\r\n" + 
				"                \"targetId\": 4,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 1,\r\n" + 
				"                \"num\": 27,\r\n" + 
				"                \"targetId\": 8,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 2,\r\n" + 
				"                \"num\": 12,\r\n" + 
				"                \"targetId\": 8,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 3,\r\n" + 
				"                \"num\": 12,\r\n" + 
				"                \"targetId\": 8,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 5,\r\n" + 
				"                \"num\": 5,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 5,\r\n" + 
				"                \"num\": 5,\r\n" + 
				"                \"targetId\": 5,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 5,\r\n" + 
				"                \"num\": 5,\r\n" + 
				"                \"targetId\": 6,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                \"attrType\": 5,\r\n" + 
				"                \"num\": 5,\r\n" + 
				"                \"targetId\": 7,\r\n" + 
				"                \"attrBuff\": 1\r\n" + 
				"            }\r\n" + 
				"        ],\r\n" + 
				"        \"fightSummary\": {\r\n" + 
				"            \"surviveNum\": 87100,\r\n" + 
				"            \"killNum\": 0,\r\n" + 
				"            \"lostPower\": 0,\r\n" + 
				"            \"lostNum\": 0,\r\n" + 
				"            \"woundedNum\": 0,\r\n" + 
				"            \"lostTrap\": 0\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    \"resInfo\": [\r\n" + 
				"        {\r\n" + 
				"            \"id\": 1002,\r\n" + 
				"            \"num\": 1193269\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"id\": 1003,\r\n" + 
				"            \"num\": 0\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}";
		
		String simpleJson = "{\r\n" + 
				"\"isAttackWin\":true,\r\n" + 
				"\"defenderKillDetail\":{},\r\n" + 
				"\"logQueueId\":\"10790215-5990\",\r\n" + 
				"\"playerInfo2\":{\r\n" + 
				"\"fightSummary\":{\r\n" + 
				"\"surviveNum\":0,\r\n" + 
				"\"killNum\":0,\r\n" + 
				"\"lostPower\":0,\r\n" + 
				"\"lostNum\":0,\r\n" + 
				"\"woundedNum\":0,\r\n" + 
				"\"lostTrap\":0\r\n" + 
				"},\r\n" + 
				"\"armyAttrInfo\":{\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"";
		List<String> list = new ArrayList<>();
		//for (int i = 0; i < 1024 * 1000; i++) {
		for (int i = 0; i < 1; i++) {
			list.add(json);
		}
//		Long start = System.currentTimeMillis();
//		list.parallelStream().forEach(s -> {
//			List<Integer> authors = JsonPath.read(s, "$.battleInfo.attackArmyInfo[0].resInfo[*].id");
//			//authors.stream().forEach(System.out::println);
//		});
//		System.out.println("time = " + (System.currentTimeMillis() - start) + "ms");
		Long start = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			
//			Object authors = JsonPath.read(json, "$.battleInfo.attackArmyInfo[0].resInfo[*].id");
//			System.out.println(authors.toString());
			Object authors = JsonPath.read(simpleJson, "$.playerInfo2.fightSummary.lostPower");
			System.out.println(authors.toString());
			//authors.stream().forEach(System.out::println);
//			String authors = JsonPath.read(json, "$.logQueueId");
//			System.out.println(authors);
//			Map<String, Object> authors = JsonPath.read(json, "$.battleInfo.attackArmyInfo[0].resInfo[*].id");
//			for (Map.Entry<String, Object> entry : authors.entrySet()) {
//				System.out.println(entry.getKey() + " = " + entry.getValue());
//			}
		}
		
		System.out.println("time = " + (System.currentTimeMillis() - start) + "ms");
		
		//ExecutorService es = Executors.newWorkStealingPool();
		
		
	}

}
