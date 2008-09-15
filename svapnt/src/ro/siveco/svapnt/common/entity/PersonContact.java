package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PersonContactBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Persoane de contact<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare<br>
 * </p>
 * <p>
 * Persoane de contact pentru persoane juridice<br>
 * </p>
 *
 * validTo validTo
 * validFrom validFrom
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
            name = "common_PersonContact_getAll",
            query = "from " + PersonContact.ENTITY_NAME
        )

    }
    )
@Entity( name = PersonContact.ENTITY_NAME )
@Table( name = "COM_PERSON_CONTACTS" )
public class PersonContact extends PersonContactBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PersonContact.ENTITY_NAME, PersonContact.class);
    }
   
}
