package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.StreetBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Strazi/artere<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_STREETS
 * tableName STREETS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                            
    @NamedQuery
        (
            name = StreetBase.NQ_findByCodeAndCity,
            query = "select object(o) from " + Street.ENTITY_NAME + " o where " +
                    "o.code = ?1 and o.city.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Street_getAll",
            query = "from " + Street.ENTITY_NAME
        )

    }
    )
@Entity( name = Street.ENTITY_NAME )
@Table( name = "COM_STREETS" )
public class Street extends StreetBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Street.ENTITY_NAME, Street.class);
    }
   
}
