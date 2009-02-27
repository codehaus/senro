package ro.siveco.senro.designer.engine.xmlgen;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.engine.ComponentXmlNames;

import java.util.List;

public class AssociationXmlGenerator
{
    public Element getXml(Document doc, AssociationInstance assoc)
    {
        Element assoc_elem = doc.createElement(ComponentXmlNames.ASSOC_ELEMENT);
        assoc_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, assoc.getName());
        assoc_elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, assoc.getId());
        assoc_elem.setAttribute(ComponentXmlNames.TYPE_ATTRIBUTE,
                                assoc.getDescription().getSenroAssoc().getClass().getName());
        List<AssociationInstance.BindingInstance> bindings = assoc.getBindings();
        for(AssociationInstance.BindingInstance binding : bindings) {
            Element binding_elem = doc.createElement(ComponentXmlNames.BINDING_ELEMENT);
            assoc_elem.appendChild(binding_elem);
            binding_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, binding.getDescription().getName());
            String b_val = binding.getValue() == null ? "" : binding.getValue().getId();
            binding_elem.setAttribute(ComponentXmlNames.VALUE_ATTRIBUTE, b_val);
            List<AssociationInstance.AspectInstance> aspects = binding.getAspects();
            for(AssociationInstance.AspectInstance aspect : aspects) {
                Element aspect_elem = doc.createElement(ComponentXmlNames.ASPECT_ELEMENT);
                binding_elem.appendChild(aspect_elem);
                aspect_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, aspect.getName());
                aspect_elem.setAttribute(ComponentXmlNames.VALUE_ATTRIBUTE, aspect.getValue() == null ? "" :
                                                                            aspect.getValue());
            }

        }
        return assoc_elem;
    }

}
