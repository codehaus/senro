package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PersonalIDCardTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de acte de identitate<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ID_CARD_TYPES
 * tableName PERS_ID_CARD_TYPES
 * versionable true
 * validTo validTo
 * validFrom validFrom
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                    
    @NamedQuery
        (
            name = PersonalIDCardTypeBase.NQ_findByCode,
            query = "select object(o) from " + PersonalIDCardType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PersonalIDCardType_getAll",
            query = "from " + PersonalIDCardType.ENTITY_NAME
        )

    }
    )
@Entity( name = PersonalIDCardType.ENTITY_NAME )
@Table( name = "COM_PERS_ID_CARD_TYPES" )
public class PersonalIDCardType extends PersonalIDCardTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PersonalIDCardType.ENTITY_NAME, PersonalIDCardType.class);
    }
   
}
