package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.EmployeeCardBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Defineste legitimatiile sau alte acte care atesta calitatea de
 * angajat<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_EMP_CARDS
 * checkValability issueDate
 * validTo endDate
 * validFrom issueDate
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                
    @NamedQuery
        (
            name = EmployeeCardBase.NQ_findByCardNumberAndPersonalIDCardType,
            query = "select object(o) from " + EmployeeCard.ENTITY_NAME + " o where " +
                    "o.cardNumber = ?1 and o.personalIDCardType.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_EmployeeCard_getAll",
            query = "from " + EmployeeCard.ENTITY_NAME
        )

    }
    )
@Entity( name = EmployeeCard.ENTITY_NAME )
@Table( name = "COM_EMPLOYEE_CARDS" )
public class EmployeeCard extends EmployeeCardBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(EmployeeCard.ENTITY_NAME, EmployeeCard.class);
    }
   
}
