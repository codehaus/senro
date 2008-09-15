package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.LovBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Liste de valori<br>
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

                                                                                                            
    @NamedQuery
        (
            name = LovBase.NQ_findByName,
            query = "select object(o) from " + Lov.ENTITY_NAME + " o where " +
                    "o.name = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_Lov_getAll",
            query = "from " + Lov.ENTITY_NAME
        )

    }
    )
@Entity( name = Lov.ENTITY_NAME )
@Table( name = "CFG_LOVS" )
public class Lov extends LovBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Lov.ENTITY_NAME, Lov.class);
    }
   
}
