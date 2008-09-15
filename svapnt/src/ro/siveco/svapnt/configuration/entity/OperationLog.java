package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.OperationLogBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Log operatii<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * noUpdate true
 * noDelete true
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
            name = "configuration_OperationLog_getAll",
            query = "from " + OperationLog.ENTITY_NAME
        )

    }
    )
@Entity( name = OperationLog.ENTITY_NAME )
@Table( name = "CFG_OPERATION_LOGS" )
public class OperationLog extends OperationLogBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(OperationLog.ENTITY_NAME, OperationLog.class);
    }
   
}
