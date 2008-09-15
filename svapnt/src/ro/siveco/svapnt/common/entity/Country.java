package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CountryBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tari<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de tari
 * </p>
 *
 * tableName COUNTRIES
 * constraintNamePK PK_COUNTRIES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                     
    @NamedQuery
        (
            name = CountryBase.NQ_findByCode,
            query = "select object(o) from " + Country.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Country_getAll",
            query = "from " + Country.ENTITY_NAME
        )

    }
    )
@Entity( name = Country.ENTITY_NAME )
@Table( name = "COM_COUNTRIES" )
public class Country extends CountryBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Country.ENTITY_NAME, Country.class);
    }
   
}
