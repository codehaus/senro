package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.EntityAttributeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Atribute entitati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 0
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                          
    @NamedQuery
        (
            name = EntityAttributeBase.NQ_findByCodeAndModelEntity,
            query = "select object(o) from " + EntityAttribute.ENTITY_NAME + " o where " +
                    "o.code = ?1 and o.modelEntity.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_EntityAttribute_getAll",
            query = "from " + EntityAttribute.ENTITY_NAME
        )

    }
    )
@Entity( name = EntityAttribute.ENTITY_NAME )
@Table( name = "CFG_ENTITY_PROPERTIES" )
@DiscriminatorValue( "0" )
public class EntityAttribute extends EntityAttributeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(EntityAttribute.ENTITY_NAME, EntityAttribute.class);
    }
   
}
