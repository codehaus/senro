package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ZoneBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Zone (geografice, comerciale etc)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_ZONES
 * tableName ZONES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                            
    @NamedQuery
        (
            name = ZoneBase.NQ_findByCode,
            query = "select object(o) from " + Zone.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Zone_getAll",
            query = "from " + Zone.ENTITY_NAME
        )

    }
    )
@Entity( name = Zone.ENTITY_NAME )
@Table( name = "COM_ZONES" )
public class Zone extends ZoneBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Zone.ENTITY_NAME, Zone.class);
    }
   
}
