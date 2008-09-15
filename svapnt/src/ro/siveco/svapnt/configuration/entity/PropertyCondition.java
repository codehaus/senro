package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.PropertyConditionBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                
	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_PropertyCondition_getAll",
            query = "from " + PropertyCondition.ENTITY_NAME
        )

    }
    )
@Entity( name = PropertyCondition.ENTITY_NAME )
@Table( name = "CFG_PROPERTY_CONDITIONS" )
public class PropertyCondition extends PropertyConditionBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PropertyCondition.ENTITY_NAME, PropertyCondition.class);
    }
   
}
