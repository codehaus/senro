package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CityTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tip Localitate<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de tipuri orase
 * </p>
 *
 * constraintNamePK PK_CITY_TYPES
 * tableName CITY_TYPES
 * defaultValueFlag implicitValueFlag
 * formColumns 4
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                      
    @NamedQuery
        (
            name = CityTypeBase.NQ_findByCode,
            query = "select object(o) from " + CityType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CityType_getAll",
            query = "from " + CityType.ENTITY_NAME
        )

    }
    )
@Entity( name = CityType.ENTITY_NAME )
@Table( name = "COM_CITY_TYPES" )
public class CityType extends CityTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CityType.ENTITY_NAME, CityType.class);
    }
   
}
