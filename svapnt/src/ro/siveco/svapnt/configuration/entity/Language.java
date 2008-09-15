package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.LanguageBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_LANGUAGES
 * tableName LANGUAGES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = LanguageBase.NQ_findByCode,
            query = "select object(o) from " + Language.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_Language_getAll",
            query = "from " + Language.ENTITY_NAME
        )

    }
    )
@Entity( name = Language.ENTITY_NAME )
@Table( name = "CFG_LANGUAGES" )
public class Language extends LanguageBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Language.ENTITY_NAME, Language.class);
    }
   
}
