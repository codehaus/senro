package ro.siveco.senro.designer.util;

import java.util.Map;
import java.util.HashMap;

public final class MapUtil
{
    @SuppressWarnings({"unchecked"})
    public static <K, V> Map<K, V> createMap(Class<K> key_class, Class<V> val_class, Object ... pairs)
    {
        Map<K, V> map = new HashMap<K,V>();
        if(pairs == null || pairs.length == 0) {
            return map;
        }
        if((pairs.length % 2) != 0) {
            throw new RuntimeException("Length of pairs array must be even.");
        }
        for(int key_idx = 0; key_idx < pairs.length; key_idx += 2) {
            Object key_obj = pairs[key_idx];
            Object val_obj = pairs[key_idx+1];
            if(key_obj != null && !key_class.isAssignableFrom(key_obj.getClass())) {
                throw new RuntimeException("Keys must be of class " + key_class.getName() + ".");
            }
            if(val_obj != null && !val_class.isAssignableFrom(val_obj.getClass())) {
                throw new RuntimeException("Values must be of class " + val_class.getName() + ".");
            }
            if(map.put((K)key_obj, (V)val_obj) != null) {
                throw new RuntimeException("Multiple key specification: '" + (key_obj == null ? "<null>" : key_obj.toString()) + "'.");
            }
        }
        return map;
    }

}
