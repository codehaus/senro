package ro.siveco.senro.designer.objects;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public abstract class SenroContextDescription extends ObjectDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "SenroContextDescription";
    private List<ParamEntry> parametersList = new ArrayList<ParamEntry>();

    public String getObjectClassName()
    {
        return OBJECT_CLASS_NAME;
    }

    public int getContextParametersCount()
    {
        return parametersList.size();
    }

    public ParamEntry getParametersEntry(int idx)
    {
        return parametersList.get(idx);
    }

    public boolean isValidNewParamEntryName(String new_name)
    {
        for (ParamEntry param_entry : parametersList) {
            if (new_name.equals(param_entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    public void addContextParameter()
    {
        int temp_idx = 0;
        while (!isValidNewParamEntryName("p_" + temp_idx)) {
            temp_idx++;
        }
        parametersList.add(new ParamEntry("p_" + temp_idx, ""));
    }

    public void addContextParameter(String key, String value)
    {
        if(isValidNewParamEntryName(key)) {
            parametersList.add(new ParamEntry(key,value));                    
        }
    }

    public void removeContextParameter(int idx)
    {
        parametersList.remove(idx);
    }

    public class ParamEntry implements Serializable
    {
        private static final long serialVersionUID = 1;

        private String key = "";
        private String value = "";

        public ParamEntry()
        {
        }

        public ParamEntry(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        public String getKey()
        {
            return key == null ? "" : key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public String getValue()
        {
            return value == null ? "" : value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }
    }
}
