package com.zhc.sys.service;

import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class CustomMapper extends ObjectMapper {

	public CustomMapper() {
		super();
		    FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", new EntityBeanPropertyFilter());
	        //SimpleBeanPropertyFilter.filterOutAllExcept("name"));
	
		    setFilters(filters);
		}



	private class EntityBeanPropertyFilter implements BeanPropertyFilter
	  {
	
		@Override
		public void serializeAsField(Object pojo, JsonGenerator jgen,
				SerializerProvider prov, BeanPropertyWriter writer)
				throws Exception {
			if(writer.getAnnotation(Transient.class)!=null)
				return;
		    
			writer.serializeAsField(pojo, jgen, prov);
		}

		@Override
		@Deprecated
		public void depositSchemaProperty(BeanPropertyWriter arg0,
				ObjectNode arg1, SerializerProvider arg2)
				throws JsonMappingException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void depositSchemaProperty(BeanPropertyWriter arg0,
				JsonObjectFormatVisitor arg1, SerializerProvider arg2)
				throws JsonMappingException {
			// TODO Auto-generated method stub
			
		}
	  }
}
