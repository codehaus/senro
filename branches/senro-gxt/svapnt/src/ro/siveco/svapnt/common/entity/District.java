package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.DistrictBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Districte/Judete<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de judete
 * </p>
 *
 * tableName DISTRICTS
 * constraintNamePK PK_DISTRICTS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                            
    @NamedQuery
        (
            name = DistrictBase.NQ_findByCode,
            query = "select object(o) from " + District.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_District_getAll",
            query = "from " + District.ENTITY_NAME
        )

    }
    )
@Entity( name = District.ENTITY_NAME )
@Table( name = "COM_DISTRICTS" )
public class District extends DistrictBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(District.ENTITY_NAME, District.class);
    }
   
}
