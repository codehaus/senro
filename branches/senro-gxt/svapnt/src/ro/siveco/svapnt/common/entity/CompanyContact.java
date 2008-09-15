package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CompanyContactBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Contacte firme, persoane fizice
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 * <p>
 * Persoane de contact pentru persoane juridice<br>
 * </p>
 *
 * constraintNamePK PK_COMPANY_CONTACT
 * tableName COMPANY_CONTACTS
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
            name = CompanyContactBase.NQ_findByNameContactAndPidAndCompany,
            query = "select object(o) from " + CompanyContact.ENTITY_NAME + " o where " +
                    "o.nameContact = ?1 and ( o.pid is null or o.pid = ?2 ) and o.company.id = ?3"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CompanyContact_getAll",
            query = "from " + CompanyContact.ENTITY_NAME
        )

    }
    )
@Entity( name = CompanyContact.ENTITY_NAME )
@Table( name = "COM_COMPANY_CONTACTS" )
public class CompanyContact extends CompanyContactBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CompanyContact.ENTITY_NAME, CompanyContact.class);
    }
   
}
