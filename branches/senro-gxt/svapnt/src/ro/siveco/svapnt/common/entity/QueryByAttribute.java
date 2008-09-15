package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.QueryByAttributeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Query-urile definte de utilizatori. Aceste query-uri sunt
 * utilizate pentru a extrage articole cu anumite atribute<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName QUERY_BY_ATTRS
 * constraintNamePK PK_QUERY_BY_ATTRS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = QueryByAttributeBase.NQ_findByCode,
            query = "select object(o) from " + QueryByAttribute.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_QueryByAttribute_getAll",
            query = "from " + QueryByAttribute.ENTITY_NAME
        )

    }
    )
@Entity( name = QueryByAttribute.ENTITY_NAME )
@Table( name = "COM_QUERY_BY_ATTRS" )
public class QueryByAttribute extends QueryByAttributeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(QueryByAttribute.ENTITY_NAME, QueryByAttribute.class);
    }
   
}
