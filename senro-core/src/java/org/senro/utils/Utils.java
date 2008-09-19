package org.senro.utils;

public class Utils {
    /**
     * Thank you, AndroMDA project...
     * Linguistically pluralizes a singular noun. <p/>
     * <ul>
     * <li><code>noun</code> becomes <code>nouns</code></li>
     * <li><code>key</code> becomes <code>keys</code></li>
     * <li><code>word</code> becomes <code>words</code></li>
     * <li><code>property</code> becomes <code>properties</code></li>
     * <li><code>bus</code> becomes <code>busses</code></li>
     * <li><code>boss</code> becomes <code>bosses</code></li>
     * </ul>
     * <p/>
     * Whitespace as well as <code>null></code> arguments will return an empty
     * String.
     * </p>
     *
     * @param singularNoun A singularNoun to pluralize
     * @return The plural of the argument singularNoun
     */
    public static String pluralize(String singularNoun) {
        String pluralNoun = singularNoun;

        int nounLength = pluralNoun.length();

        if (nounLength == 1) {
            pluralNoun = pluralNoun + 's';
        } else if (nounLength > 1) {
            char secondToLastChar = pluralNoun.charAt(nounLength - 2);

            if (pluralNoun.endsWith("y")) {
                switch (secondToLastChar) {
                    case'a': // fall-through
                    case'e': // fall-through
                    case'i': // fall-through
                    case'o': // fall-through
                    case'u':
                        pluralNoun = pluralNoun + 's';
                        break;
                    default:
                        pluralNoun = pluralNoun.substring(0, nounLength - 1)
                                + "ies";
                }
            } else if (pluralNoun.endsWith("s")) {
                switch (secondToLastChar) {
                    case's':
                        pluralNoun = pluralNoun + "es";
                        break;
                    default:
                        pluralNoun = pluralNoun + "ses";
                }
            } else {
                pluralNoun = pluralNoun + 's';
            }
        }
        return pluralNoun;
    }

    /**
     * @param type the (usable) super type if passed a CGLIB enhanced class
     * @return
     */
    public static Class checkForCGLIB(Class type) {
        if (type.getName().contains("CGLIB")) {
            return type.getSuperclass();
        } else return type;
    }

}
