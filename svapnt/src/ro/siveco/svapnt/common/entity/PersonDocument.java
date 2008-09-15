package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PersonDocumentBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Documente persoane<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Date despre asigurat<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare<br>
 * </p>
 *
 * validFrom validFrom
 * validTo validTo
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                      
    @NamedQuery
        (
            name = PersonDocumentBase.NQ_findByNoAndValidFromAndPersonAndDocumentType,
            query = "select object(o) from " + PersonDocument.ENTITY_NAME + " o where " +
                    "o.no = ?1 and o.validFrom = ?2 and o.person.id = ?3 and o.documentType.id = ?4"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PersonDocument_getAll",
            query = "from " + PersonDocument.ENTITY_NAME
        )

    }
    )
@Entity( name = PersonDocument.ENTITY_NAME )
@Table( name = "COM_PERSON_DOCUMENTS" )
public class PersonDocument extends PersonDocumentBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PersonDocument.ENTITY_NAME, PersonDocument.class);
    }
   
}
