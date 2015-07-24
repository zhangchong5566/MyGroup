package com.zhc.schedule.quartz.entity;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhc.sys.entity.AbstractEntityGenerate;

@Entity
@Table(name = "qz_job_detail")
@JsonFilter("myFilter")
@Access(PROPERTY)
public class QzJobDetail extends AbstractEntityGenerate implements Serializable{
	
	  private String name;
	  private String description;
	  private String className;
	  private String parameters;
	  private Long extendf1;
	  private Long extendf2;
	  private Integer extendf3;
	  private Integer extendf4;
	  private String extendf5;
	  private String extendf6;
	  private String extendf7;
	  private String extendf8;
	  
	  public Long getExtendf1()
	  {
	    return this.extendf1;
	  }
	  
	  public void setExtendf1(Long extendf1)
	  {
	    this.extendf1 = extendf1;
	  }
	  
	  public Long getExtendf2()
	  {
	    return this.extendf2;
	  }
	  
	  public void setExtendf2(Long extendf2)
	  {
	    this.extendf2 = extendf2;
	  }
	  
	  public Integer getExtendf3()
	  {
	    return this.extendf3;
	  }
	  
	  public void setExtendf3(Integer extendf3)
	  {
	    this.extendf3 = extendf3;
	  }
	  
	  public Integer getExtendf4()
	  {
	    return this.extendf4;
	  }
	  
	  public void setExtendf4(Integer extendf4)
	  {
	    this.extendf4 = extendf4;
	  }
	  
	  public String getExtendf5()
	  {
	    return this.extendf5;
	  }
	  
	  public void setExtendf5(String extendf5)
	  {
	    this.extendf5 = extendf5;
	  }
	  
	  public String getExtendf6()
	  {
	    return this.extendf6;
	  }
	  
	  public void setExtendf6(String extendf6)
	  {
	    this.extendf6 = extendf6;
	  }
	  
	  public String getExtendf7()
	  {
	    return this.extendf7;
	  }
	  
	  public void setExtendf7(String extendf7)
	  {
	    this.extendf7 = extendf7;
	  }
	  
	  public String getExtendf8()
	  {
	    return this.extendf8;
	  }
	  
	  public void setExtendf8(String extendf8)
	  {
	    this.extendf8 = extendf8;
	  }
	  
	  public String getName()
	  {
	    return this.name;
	  }
	  
	  public void setName(String name)
	  {
	    this.name = name;
	  }
	  
	  public String getDescription()
	  {
	    return this.description;
	  }
	  
	  public void setDescription(String description)
	  {
	    this.description = description;
	  }
	  
	  public String getClassName()
	  {
	    return this.className;
	  }
	  
	  public void setClassName(String className)
	  {
	    this.className = className;
	  }
	  
	  public String getParameters()
	  {
	    return this.parameters;
	  }
	  
	  public void setParameters(String parameters)
	  {
	    this.parameters = parameters;
	  }

}
