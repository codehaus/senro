package org.senro.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

@Entity
public class Model implements Serializable {

    public Model()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    private Integer id;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
    
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        // TODO Auto-generated method stub
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return getName();
    }

}
