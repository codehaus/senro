package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ContactTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de contacte firme, persoane fizice<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_CONTACT_TYPES
 * tableName CONTACT_TYPES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = ContactTypeBase.NQ_findByCode,
            query = "select object(o) from " + ContactType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_ContactType_getAll",
            query = "from " + ContactType.ENTITY_NAME
        )

    }
    )
@Entity( name = ContactType.ENTITY_NAME )
@Table( name = "COM_CONTACT_TYPES" )
public class ContactType extends ContactTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ContactType.ENTITY_NAME, ContactType.class);
    }
   
}
