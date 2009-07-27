package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;

public class ContextFragmentAnalyzer extends SCAnalyzer
{
    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.CONTEXT_FRAGMENT;
    }
}
