package ro.siveco.senro.designer.objects;

import ro.siveco.senro.designer.util.event.AddCollectionItemEvent;
import ro.siveco.senro.designer.util.event.RemoveCollectionItemEvent;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import org.apache.commons.lang.ObjectUtils;

public abstract class SCDescription extends ObjectDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "SCDescription";
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
        ParamEntry new_param = new ParamEntry("p_" + temp_idx, "");
        new AddCollectionItemEvent(this, "parametersList", new_param).post();
        parametersList.add(new_param);
    }

    public void addContextParameter(String key, String value)
    {
        if (isValidNewParamEntryName(key)) {
            ParamEntry new_param = new ParamEntry(key, value);
            new AddCollectionItemEvent(this, "parametersList", new_param).post();
            parametersList.add(new_param);
        }
    }

    public void removeContextParameter(int idx)
    {
        new RemoveCollectionItemEvent(this, "parametersList", idx).post();
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

        public void setKey(String new_key)
        {
            if (ObjectUtils.equals(key, new_key)) {
                return;
            }
            new AttributeChangeEvent(this, "key", key, new_key).post();
            key = new_key;
        }

        public String getValue()
        {
            return value == null ? "" : value;
        }

        public void setValue(String new_value)
        {
            if (ObjectUtils.equals(value, new_value)) {
                return;
            }
            new AttributeChangeEvent(this, "value", value, new_value).post();
            value = new_value;
        }
    }
}
