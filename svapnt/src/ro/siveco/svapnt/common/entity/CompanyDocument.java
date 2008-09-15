package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CompanyDocumentBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Documente companii<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 *
 * validTo validTo
 * validFrom validFrom
 * checkValability validFrom
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                      
    @NamedQuery
        (
            name = CompanyDocumentBase.NQ_findByNoAndValidFromAndPartnerAndDocumentType,
            query = "select object(o) from " + CompanyDocument.ENTITY_NAME + " o where " +
                    "o.no = ?1 and o.validFrom = ?2 and o.partner.id = ?3 and o.documentType.id = ?4"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CompanyDocument_getAll",
            query = "from " + CompanyDocument.ENTITY_NAME
        )

    }
    )
@Entity( name = CompanyDocument.ENTITY_NAME )
@Table( name = "COM_COMPANY_DOCUMENTS" )
public class CompanyDocument extends CompanyDocumentBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CompanyDocument.ENTITY_NAME, CompanyDocument.class);
    }
   
}
