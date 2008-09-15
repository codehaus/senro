package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.AddressTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de adresa<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ADDRESS_TYPES
 * tableName ADDRESS_TYPES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = AddressTypeBase.NQ_findByCode,
            query = "select object(o) from " + AddressType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_AddressType_getAll",
            query = "from " + AddressType.ENTITY_NAME
        )

    }
    )
@Entity( name = AddressType.ENTITY_NAME )
@Table( name = "COM_ADDRESS_TYPES" )
public class AddressType extends AddressTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(AddressType.ENTITY_NAME, AddressType.class);
    }
   
}
