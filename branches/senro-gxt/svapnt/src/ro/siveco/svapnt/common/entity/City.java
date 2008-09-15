package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CityBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Localitati<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de orase
 * </p>
 *
 * constraintNamePK PK_CITIES
 * tableName CITIES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                    
    @NamedQuery
        (
            name = CityBase.NQ_findByCode,
            query = "select object(o) from " + City.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_City_getAll",
            query = "from " + City.ENTITY_NAME
        )

    }
    )
@Entity( name = City.ENTITY_NAME )
@Table( name = "COM_CITIES" )
public class City extends CityBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(City.ENTITY_NAME, City.class);
    }
   
}
