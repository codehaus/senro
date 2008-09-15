package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CompanyOrgBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri organizatorice de firme<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 *
 * constraintNamePK PK_CMP_ORGS
 * tableName CMP_ORGS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = CompanyOrgBase.NQ_findByCode,
            query = "select object(o) from " + CompanyOrg.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CompanyOrg_getAll",
            query = "from " + CompanyOrg.ENTITY_NAME
        )

    }
    )
@Entity( name = CompanyOrg.ENTITY_NAME )
@Table( name = "COM_CMP_ORGS" )
public class CompanyOrg extends CompanyOrgBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CompanyOrg.ENTITY_NAME, CompanyOrg.class);
    }
   
}
