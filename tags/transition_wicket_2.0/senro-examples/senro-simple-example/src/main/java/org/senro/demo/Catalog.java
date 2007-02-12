/*
 * Copyright 2004 Chris Nelson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.senro.demo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;


/**
 * @hibernate.class
 * @javabean.class shortDescription="This is some other descriptive text."
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
@Entity
public class Catalog implements Serializable {
    private Integer id;
    private String name;
    private List<Category> categories = new ArrayList<Category>();

    /**
     * @hibernate.id generator-class="native"
     * @javabean.property
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @hibernate.property
     * @javabean.property
     */
    @NotNull
    @Length(min=1,max=20)
    @Pattern(regex="[a-z]*")
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return getName();
    }

    /**
     * @hibernate.list cascade="all-delete-orphan"
     * @hibernate.collection-key column="CATALOG_ID"
     * @hibernate.collection-index column = "CATEGORY_INDEX"
     * @hibernate.collection-one-to-many class="org.senro.demo.Category"
     * @javabean.property
     *
     */
    @OneToMany(cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name="CATALOG_ID")
    @IndexColumn(name="CATEGORY_INDEX")
    public List<Category> getCategories()
    {
        return categories;
    }

    /**
     * @param categories The categories to set.
     */
    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
        
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        try
        {
            final Catalog catalog = (Catalog) obj;
            if (!getId().equals(catalog.getId()))
                return false;
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
}
