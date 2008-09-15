package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.TranslationBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Traduceri pentru date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_TRANSLATIONS
 * tableName TRANSLATIONS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                               
	//Unique keys methods with Relations as parameters

    @NamedQuery
        (
            name = TranslationBase.NQ_findByLanguage,
            query = "select object(o) from " + Translation.ENTITY_NAME + " o where " +
                    "o.language.id = ?1"
        ),


//model defined finders


    @NamedQuery
        (
            name = "configuration_Translation_getAll",
            query = "from " + Translation.ENTITY_NAME
        )

    }
    )
@Entity( name = Translation.ENTITY_NAME )
@Table( name = "CFG_TRANSLATIONS" )
public class Translation extends TranslationBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Translation.ENTITY_NAME, Translation.class);
    }
   
}
