package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CitizenshipBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Cetatenii<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de cetatenii
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
            name = CitizenshipBase.NQ_findByCode,
            query = "select object(o) from " + Citizenship.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Citizenship_getAll",
            query = "from " + Citizenship.ENTITY_NAME
        )

    }
    )
@Entity( name = Citizenship.ENTITY_NAME )
@Table( name = "COM_CITIZENSHIPS" )
public class Citizenship extends CitizenshipBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Citizenship.ENTITY_NAME, Citizenship.class);
    }
   
}
