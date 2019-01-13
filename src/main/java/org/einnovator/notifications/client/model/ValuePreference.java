package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ValuePreference extends Preference {

	public static final String CATEGORY_VALUE = "Value";

	public static final String VALUE_PREFERENCE_NAME_PREFIX = "value.";

	private String key;
	
	private Object value;
	
	private String op;
	
	private List<Object> params;
	
	public ValuePreference() {
	}

	@Override
	public String getCategory() {
		return CATEGORY_VALUE;
	}
	
	@Override
	void setName(String name) {
	}

	@Override
	void setCategory(String category) {
	}

	@Override
	public String getName() {
		return VALUE_PREFERENCE_NAME_PREFIX + key;
	}
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " ["
				+ (key != null ? "key=" + key + ", " : "")
				+ (value != null ? "value=" + value + ", " : "")
				+ (op != null ? "op=" + op + ", " : "")
				+ (params != null ? "params=" + params + ", " : "")
				+ "]";
	}


	public Object getParam(int i) {
		if (params==null || i<0 || i>=params.size()) {
			return null;			
		}
		return params.get(i);
	}
	

	public Object setParam(int i, Object value) {
		if (i<0) {
			return null;
		}
		if (params==null) {
			params = new ArrayList<>();
		}
		while (i>=params.size()) {
			params.add(null);
		}
		params.set(i, value);
		return value;
	}


}
