package org.senro.ui.template.sid;


public interface SIDComponent {
	public static final String Iterator_List = "Iterator_List";
	public static final String Iterator_FilterCondition = "Iterator_FilterCondition";
	public static final String List_Entity = "List_Entity";
	public static final String SwitchComponent_Property = "SwitchComponent_Property";
	
	public static class KeyValuePair {
        public Object key;
        public Object value;
        
        public KeyValuePair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
        boolean equals(KeyValuePair pair) {
            return pair.key == this.key;
        }
    }
}
