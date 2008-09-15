package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.ModuleRightBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Drepturi pe module<br>
 * </p>
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
            name = "configuration_ModuleRight_getAll",
            query = "from " + ModuleRight.ENTITY_NAME
        )

    }
    )
@Entity( name = ModuleRight.ENTITY_NAME )
@Table( name = "CFG_MODULE_RIGHTS" )
@DiscriminatorValue( "-1" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public abstract class ModuleRight extends ModuleRightBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ModuleRight.ENTITY_NAME, ModuleRight.class);
    }
   
}
