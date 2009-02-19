package ro.siveco.senro.designer.util;

import org.apache.commons.codec.binary.Hex;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class HexUtil
{
     public static String getObjectsHexFromList(List<?> obj_list)
    {
        try {
            ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(ba_os);
            os.writeObject(obj_list);
            os.close();
            byte[] b = ba_os.toByteArray();
            return new String(Hex.encodeHex(b));
        }
        catch (Exception e) {
            return null;
        }
    }
}
