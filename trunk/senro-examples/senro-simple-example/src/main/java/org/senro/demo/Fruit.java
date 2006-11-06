package org.senro.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Fruit implements Serializable {
    private Integer id;
    
    public Fruit()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
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
}
