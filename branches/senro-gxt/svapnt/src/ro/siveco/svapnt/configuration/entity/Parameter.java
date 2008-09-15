package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.ParameterBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Parametri aplicatiei<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName PARAMS
 * constraintNamePK PK_PARAMS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                 
    @NamedQuery
        (
            name = ParameterBase.NQ_findByParamName,
            query = "select object(o) from " + Parameter.ENTITY_NAME + " o where " +
                    "o.paramName = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_Parameter_getAll",
            query = "from " + Parameter.ENTITY_NAME
        )

    }
    )
@Entity( name = Parameter.ENTITY_NAME )
@Table( name = "CFG_PARAMS" )
public class Parameter extends ParameterBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Parameter.ENTITY_NAME, Parameter.class);
    }
   
}
