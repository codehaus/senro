package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CalendarBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Sarbatori legale<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de zile libere
 * </p>
 *
 * tableName CALENDARS
 * constraintNamePK PK_CALENDARS
 * finders findForTimePeriod=|false||AND|
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = CalendarBase.NQ_findByDay,
            query = "select object(o) from " + Calendar.ENTITY_NAME + " o where " +
                    "o.day = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = CalendarBase.NQ_F1findForTimePeriod,
            query = "from " + Calendar.ENTITY_NAME + " o "
                      + " where  o.day >= ?1 AND o.day <= ?2  "
            ),

  

    @NamedQuery
        (
            name = "common_Calendar_getAll",
            query = "from " + Calendar.ENTITY_NAME
        )

    }
    )
@Entity( name = Calendar.ENTITY_NAME )
@Table( name = "COM_CALENDARS" )
public class Calendar extends CalendarBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Calendar.ENTITY_NAME, Calendar.class);
    }
   
}
