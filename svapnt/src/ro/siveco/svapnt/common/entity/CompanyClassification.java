package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CompanyClassificationBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Clasificare de firme<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_CMP_CLASSIFS
 * tableName CMP_CLASSIFICATIONS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = CompanyClassificationBase.NQ_findByCode,
            query = "select object(o) from " + CompanyClassification.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CompanyClassification_getAll",
            query = "from " + CompanyClassification.ENTITY_NAME
        )

    }
    )
@Entity( name = CompanyClassification.ENTITY_NAME )
@Table( name = "COM_CMP_CLASSIFICATIONS" )
public class CompanyClassification extends CompanyClassificationBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CompanyClassification.ENTITY_NAME, CompanyClassification.class);
    }
   
}
