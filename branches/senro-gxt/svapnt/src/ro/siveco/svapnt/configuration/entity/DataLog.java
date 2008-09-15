package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.DataLogBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Log date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * noDelete true
 * noUpdate true
 * noInsert true
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
            name = "configuration_DataLog_getAll",
            query = "from " + DataLog.ENTITY_NAME
        )

    }
    )
@Entity( name = DataLog.ENTITY_NAME )
@Table( name = "CFG_DATA_LOGS" )
public class DataLog extends DataLogBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(DataLog.ENTITY_NAME, DataLog.class);
    }
   
}
