package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.DocumentTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de documente autorizate<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
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

                                                                                                                    
    @NamedQuery
        (
            name = DocumentTypeBase.NQ_findByCode,
            query = "select object(o) from " + DocumentType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_DocumentType_getAll",
            query = "from " + DocumentType.ENTITY_NAME
        )

    }
    )
@Entity( name = DocumentType.ENTITY_NAME )
@Table( name = "COM_DOCUMENT_TYPES" )
public class DocumentType extends DocumentTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(DocumentType.ENTITY_NAME, DocumentType.class);
    }
   
}
