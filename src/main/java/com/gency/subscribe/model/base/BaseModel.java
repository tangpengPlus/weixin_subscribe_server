package com.gency.subscribe.model.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseModel implements Serializable,Cloneable {
private static final long serialVersionUID = -7840927907448967010L;
    
	public BaseModel(){
		
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public final <T> T simpleClone() {
		try {
			@SuppressWarnings("unchecked")
			T ret = (T)clone();
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
