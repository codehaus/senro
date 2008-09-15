package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.LovValuesBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Valori liste de valori<br>
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
            name = LovValuesBase.NQ_findByValAndLov,
            query = "select object(o) from " + LovValues.ENTITY_NAME + " o where " +
                    "o.val = ?1 and o.lov.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_LovValues_getAll",
            query = "from " + LovValues.ENTITY_NAME
        )

    }
    )
@Entity( name = LovValues.ENTITY_NAME )
@Table( name = "CFG_LOV_VALUES" )
public class LovValues extends LovValuesBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(LovValues.ENTITY_NAME, LovValues.class);
    }
   
}
