package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PersonalIdCardBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Acte de identititate persoane fizice si salariati
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare<br>
 * </p>
 *
 * constraintNamePK PK_PERS_CARDS
 * tableName PERS_CARDS
 * checkValability issueDate
 * validFrom issueDate
 * validTo endDate
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                           
    @NamedQuery
        (
            name = PersonalIdCardBase.NQ_findBySeriesAndIdNumberAndPersonalIDCardType,
            query = "select object(o) from " + PersonalIdCard.ENTITY_NAME + " o where " +
                    "( o.series is null or o.series = ?1 ) and o.idNumber = ?2 and o.personalIDCardType.id = ?3"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PersonalIdCard_getAll",
            query = "from " + PersonalIdCard.ENTITY_NAME
        )

    }
    )
@Entity( name = PersonalIdCard.ENTITY_NAME )
@Table( name = "COM_PERS_CARDS" )
public class PersonalIdCard extends PersonalIdCardBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PersonalIdCard.ENTITY_NAME, PersonalIdCard.class);
    }
   
}
