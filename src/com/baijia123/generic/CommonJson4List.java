package com.baijia123.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CommonJson4List<T> implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = -369558847578246550L;

	/**
	 * 是否成功
	 */
	private Boolean success;

	/**
	 * 数据
	 */
	private List<T> data;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public static CommonJson4List fromJson(String json, Class clazz) {
		Gson gson = new Gson();
		Type objectType = type(CommonJson4List.class, clazz);
		return gson.fromJson(json, objectType);
	}
	
//	public static CommonJson4List fromJson1(String json, Class clazz) {  
//        Gson gson = new Gson();  
//        Type objectType = new TypeToken<CommonJson4List<clazz>>() {}.getType();  
//        return gson.fromJson(json, objectType);  
//    }

	public String toJson(Class<T> clazz) {
		Gson gson = new Gson();
		Type objectType = type(CommonJson4List.class, clazz);
		return gson.toJson(this, objectType);
	}

	static ParameterizedType type(final Class raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}

}