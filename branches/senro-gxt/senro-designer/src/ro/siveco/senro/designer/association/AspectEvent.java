package ro.siveco.senro.designer.association;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Arrays;

public enum AspectEvent
{
    ON_CLICK
            {
                public int getEventId()
                {
                    return 1;
                }

                public String toString()
                {
                    return "OnClick";
                }
            },
    ON_DOUBLE_CLICK
            {
                public int getEventId()
                {
                    return 2;
                }

                public String toString()
                {
                    return "OnDoubleClick";
                }
            },
    ON_MOUSE_DOWN
            {
                public int getEventId()
                {
                    return 4;
                }

                public String toString()
                {
                    return "OnMouseDown";
                }
            },
    ON_MOUSE_UP
            {
                public int getEventId()
                {
                    return 8;
                }

                public String toString()
                {
                    return "OnMouseUp";
                }
            },
    ON_MOUSE_OVER
            {
                public int getEventId()
                {
                    return 16;
                }

                public String toString()
                {
                    return "OnMouseOver";
                }
            },
    ON_MOUSE_OUT
            {
                public int getEventId()
                {
                    return 32;
                }

                public String toString()
                {
                    return "OnMouseOut";
                }
            },
    ON_MOUSE_MOVE
            {
                public int getEventId()
                {
                    return 64;
                }

                public String toString()
                {
                    return "OnMouseMove";
                }
            },
    ON_KEY_DOWN
            {
                public int getEventId()
                {
                    return 128;
                }

                public String toString()
                {
                    return "OnKeyDown";
                }
            },
    ON_KEY_UP
            {
                public int getEventId()
                {
                    return 512;
                }

                public String toString()
                {
                    return "OnKeyUp";
                }
            },
    ON_BLUR
            {
                public int getEventId()
                {
                    return 140;
                }

                public String toString()
                {
                    return "OnBlur";
                }
            },
    ON_CHANGE
            {
                public int getEventId()
                {
                    return 170;
                }

                public String toString()
                {
                    return "OnChange";
                }
            },
    ON_FOCUS
            {
                public int getEventId()
                {
                    return 380;
                }

                public String toString()
                {
                    return "OnFocus";
                }
            },
    ON_VALID
            {
                public int getEventId()
                {
                    return 710;
                }

                public String toString()
                {
                    return "OnValid";
                }
            };

    public abstract int getEventId();

    public abstract String toString();

    public static final Set<AspectEvent> ALL_EVENTS =
            Collections.unmodifiableSet(new HashSet<AspectEvent>(Arrays.asList(ON_CLICK, ON_DOUBLE_CLICK, ON_MOUSE_DOWN,
                    ON_MOUSE_UP, ON_MOUSE_OVER, ON_MOUSE_OUT, ON_MOUSE_MOVE, ON_KEY_DOWN, ON_KEY_UP, ON_BLUR, ON_CHANGE,
                    ON_FOCUS, ON_VALID)));
}