package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CompanyProfileBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Profile clienti<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 *
 * constraintNamePK PK_COMPANY_PROFILE
 * tableName COMPANY_PROFILES
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
            name = CompanyProfileBase.NQ_findByCode,
            query = "select object(o) from " + CompanyProfile.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CompanyProfile_getAll",
            query = "from " + CompanyProfile.ENTITY_NAME
        )

    }
    )
@Entity( name = CompanyProfile.ENTITY_NAME )
@Table( name = "COM_COMPANY_PROFILES" )
public class CompanyProfile extends CompanyProfileBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CompanyProfile.ENTITY_NAME, CompanyProfile.class);
    }
   
}
