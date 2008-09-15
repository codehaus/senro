package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.EntityPropertyBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Proprietati entitati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * noDelete true
 * noInsert true
 * noUpdate true
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                          
    @NamedQuery
        (
            name = EntityPropertyBase.NQ_findByCodeAndModelEntity,
            query = "select object(o) from " + EntityProperty.ENTITY_NAME + " o where " +
                    "o.code = ?1 and o.modelEntity.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_EntityProperty_getAll",
            query = "from " + EntityProperty.ENTITY_NAME
        )

    }
    )
@Entity( name = EntityProperty.ENTITY_NAME )
@Table( name = "CFG_ENTITY_PROPERTIES" )
@DiscriminatorValue( "-1" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public abstract class EntityProperty extends EntityPropertyBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(EntityProperty.ENTITY_NAME, EntityProperty.class);
    }
   
}
