package org.senro.ui.template;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mvel.templates.CompiledTemplate;
import org.mvel.templates.TemplateCompiler;
import org.mvel.templates.TemplateRuntime;
import org.senro.Senro;
import org.senro.exception.SenroTemplateException;
import org.senro.gwt.client.assoc.SenroAspect;
import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.model.ui.HorizontalAlignment;
import org.senro.gwt.client.model.ui.SenroCellLayout;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.SenroTableLayout;
import org.senro.gwt.client.model.ui.VerticalAlignment;
import org.senro.gwt.client.model.ui.binding.BooleanModelObject;
import org.senro.gwt.client.model.ui.binding.ButtonModelObject;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.ListModelObject;
import org.senro.gwt.client.model.ui.binding.MapModelObject;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.binding.StringModelObject;
import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataProperty;
import org.senro.ui.ComponentFactory;
import org.senro.ui.GridAllocator;
import org.senro.ui.control.AssociationRegistry;
import org.senro.ui.template.model.Assoc;
import org.senro.ui.template.model.Associations;
import org.senro.ui.template.model.BasicComponentType;
import org.senro.ui.template.model.ButtonType;
import org.senro.ui.template.model.CheckBoxType;
import org.senro.ui.template.model.ComboBoxType;
import org.senro.ui.template.model.ComponentTemplate;
import org.senro.ui.template.model.ConditionalType;
import org.senro.ui.template.model.DateFieldType;
import org.senro.ui.template.model.ElseType;
import org.senro.ui.template.model.GridAllocatorRendererType;
import org.senro.ui.template.model.GridAllocatorType;
import org.senro.ui.template.model.GridSpecificationElement;
import org.senro.ui.template.model.GridType;
import org.senro.ui.template.model.IfType;
import org.senro.ui.template.model.IteratorType;
import org.senro.ui.template.model.LabelType;
import org.senro.ui.template.model.ListType;
import org.senro.ui.template.model.PopupType;
import org.senro.ui.template.model.RootPanelType;
import org.senro.ui.template.model.Server;
import org.senro.ui.template.model.SwitchComponentType;
import org.senro.ui.template.model.TabPageType;
import org.senro.ui.template.model.TabPanelType;
import org.senro.ui.template.model.TemplateType;
import org.senro.ui.template.model.TextAreaType;
import org.senro.ui.template.model.TextFieldType;
import org.senro.ui.template.model.TreeNodeType;
import org.senro.ui.template.model.TreeType;
import org.senro.ui.template.model.UIComponentType;
import org.senro.ui.template.model.Assoc.Binding;
import org.senro.ui.template.model.TemplateType.Param;
import org.senro.ui.template.sid.SIDComponent;
import org.springframework.util.Assert;

/**
 * This is a component factory that renders {@link SenroComponent} components from JAXB 2.0 model
 * beans that were generated from the {@link ComponentTemplate} XSD document.
 * This acts as a factory for {@link SenroComponent} object having as input XML nodes 
 * of type {@link UIComponentType}.
 * It uses the runtime context provided by the {@link TemplateParser} which it alters to add XML node objects
 * as it runs by them. The object are identified by a unique <i>id</i> within the template file.
 * @author CristiS
 * @author FlaviusB
 */
public class TemplateRenderer<T extends SenroComponent> {
	private final Logger logger = Logger.getLogger(TemplateRenderer.class);
	
	protected Map<String, Object> internalContext = new HashMap<String, Object>();
	private int iteratorCount = 0;
	
	/**
	 * Constructor
	 */
	public TemplateRenderer() {	
	}
	
	public void setInternalContext(Map<String, Object> internalContext) {
		this.internalContext = internalContext;
	}
	
	/**
	 * Renders a {@link UIComponentType} into a list of {@link SenroComponent} components
	 * It uses the runtime context provided by the {@link TemplateParser} which it alters to add XML node objects
	 * as it runs by them. The object are identified by a unique <i>id</i> within the template file.
	 * @param uiComponent the provided {@link UIComponentType} component
	 * @param generateId whether to generate a default unique id
	 * @return a list of {@link SenroComponent} components
	 * @throws SenroTemplateException if something went wrong
	 */
	public List<T> renderGeneric( UIComponentType uiComponent, boolean generateId ) throws SenroTemplateException {
		List<T> result = new ArrayList<T>();
		if( uiComponent instanceof LabelType ) {
			T component = (T) new SenroComponent<StringModelObject>();
			component.setRenderComponent( ComponentAssociation.LABEL );
			String expression = ((LabelType)uiComponent).getText();
			String text = this.<String> evaluate(expression);
			component.setModel(new Model<StringModelObject>(new StringModelObject( text )));

			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TextFieldType ) {
			T component = (T) new SenroComponent<StringModelObject>();
			component.setRenderComponent( ComponentAssociation.TEXTFIELD );
			component.setModel(new Model<StringModelObject>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TextAreaType ) {
			T component = (T) new SenroComponent<StringModelObject>();
			component.setRenderComponent( ComponentAssociation.TEXTAREA );
			component.setModel(new Model<StringModelObject>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof CheckBoxType ) {
			T component = (T) new SenroComponent<BooleanModelObject>();
			component.setRenderComponent( ComponentAssociation.CHECKBOX );
			component.setModel(new Model<BooleanModelObject>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof ComboBoxType ) {
			T component = (T) new SenroComponent<MapModelObject>();
			component.setRenderComponent( ComponentAssociation.COMBOBOX );
			component.setModel(new Model<MapModelObject>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof ButtonType ) {
			T component = (T) new SenroComponent<ButtonModelObject>();
			component.setRenderComponent( ComponentAssociation.BUTTON );
			ButtonType button = (ButtonType)uiComponent;
			
			
			String entity = evaluate(button.getEntity());
			ButtonModelObject model = new ButtonModelObject(button.getLabel(), entity, button.getTask());
			component.setModel(new Model<ButtonModelObject>(model));
			
			if( "icon".equals(button.getType()) ) {
				component.setRenderComponent( ComponentAssociation.ICON_BUTTON );
				((ButtonModelObject)component.getModel().getDataObject()).setIcon(button.getIcon());
				((ButtonModelObject)component.getModel().getDataObject()).setHoverIcon(button.getHoverIcon());
			}
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add(component);
			addVariable(uiComponent);
		}
		else if( uiComponent instanceof DateFieldType ) {
			T component = (T) new SenroComponent();
			component.setRenderComponent( ComponentAssociation.DATEFIELD );
			component.setModel(new Model());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TreeType ) {
			T component = renderTree( (TreeType)uiComponent, generateId );
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof RootPanelType ) {
			T component = (T) new SenroComponent();
			component.setRenderComponent( ComponentAssociation.ROOTPANEL );
			component.setModel(new Model());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof GridType && !(uiComponent instanceof PopupType)) {
			T component = (T) renderGrid((GridType)uiComponent, generateId) ;
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add(component);
		}
		else if( uiComponent instanceof ListType ) {
			T component = (T) renderList((ListType)uiComponent, generateId) ;
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add(component);
		}
		else if( uiComponent instanceof GridAllocatorRendererType ) {
			T component = renderGridAllocatorRenderer( (GridAllocatorRendererType)uiComponent, generateId );
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			}
			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add((T) component);
		}
		else if( uiComponent instanceof TabPanelType ) {
			T component = renderTabPanel( (TabPanelType)uiComponent, generateId );
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TabPageType ) {
			T component = renderTabPage( (TabPageType)uiComponent, generateId );
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof SwitchComponentType ) {
			T component = renderSwitchComponent( (SwitchComponentType)uiComponent, generateId );
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof IteratorType ) {
			List<T> components = renderIterator((IteratorType)uiComponent, generateId) ;
			result.addAll(components);
		}
		else if( uiComponent instanceof ConditionalType ) {
			List<T> components = renderConditional((ConditionalType)uiComponent, generateId) ;
			result.addAll(components);
		}
		else if( uiComponent instanceof TemplateType ) {
			T component = renderTemplate( (TemplateType)uiComponent, generateId ); 
			result.add(component);
		}
		else {
			T component = (T) new SenroComponent();
			component.setRenderComponent( ComponentAssociation.CUSTOM );
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
				iteratorCount++;
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		
		return result;
	}
	
	protected T renderSwitchComponent( SwitchComponentType uiComponent, boolean generateId ) throws SenroTemplateException {
		Assert.notNull(uiComponent);
		String expr = uiComponent.getProperty();
		Object evalResult = evaluate(expr);
		SenroContainerComponent<T> result = new SenroContainerComponent<T>(uiComponent.getId());
		
		if(evalResult instanceof MetadataProperty) {
			MetadataProperty property = this.<MetadataProperty> evaluate(expr);
			
			List<T> components = (List<T>) ComponentFactory.createComponent(property, new Boolean(uiComponent.isCreateLabel()));
			result.setLayout(new SenroTableLayout(components.size()));
			result.setRenderComponent(ComponentAssociation.GRID);
			result.add(components);
			
			try {
				result = (SenroContainerComponent<T>) GridAllocator.allocateWidgets((SenroContainerComponent<SenroComponent>) result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			result.put(SIDComponent.SwitchComponent_Property, uiComponent.getProperty());
			result.setRenderComponent(ComponentAssociation.SWITCHCOMPONENT);
		}
		
		return (T) result;
	}

	protected T renderTemplate(TemplateType uiComponent, boolean generateId) throws SenroTemplateException {
		if( StringUtils.isEmpty(uiComponent.getFile()) )
			throw new SenroTemplateException("Template file not specified");
		
		List<Param> params = uiComponent.getParam();
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("senroContext", evaluate("@{senroContext}"));
		
		for(Param param : params) {
			String paramName = param.getName();
			Object paramValue = evaluate(param.getValue());
			
			if( StringUtils.isEmpty(paramName) )
				throw new SenroTemplateException("Found null template parameter name.");
			
			if( paramValue == null )
				System.out.println("WARNING: Template component parameter value is null for: "+paramName);
			
			varMap.put(paramName, paramValue);
		}
		
		InputStream stream;
		SenroContainerComponent<SenroComponent<?>> result = null;
		try {
			stream = new FileInputStream( Senro.getTemplate(uiComponent.getFile()) );
			TemplateParser parser = Senro.getTemplateParser();
			parser.setInputStream( stream );
			result = (SenroContainerComponent<SenroComponent<?>>) parser.render(varMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return (T) result;
	}

	protected T renderTabPanel(TabPanelType uiComponent, boolean generateId) throws SenroTemplateException {
		SenroContainerComponent<T> component = new SenroContainerComponent<T>();
		component.setRenderComponent( ComponentAssociation.TABPANEL );
		List<?> children = uiComponent.getTabPageAndIterator();
		if( children != null) {
			// 2 options: treenode or iterator
			for(Object child : children) {
				if( child instanceof TabPageType ) {
					TabPageType tabPage = (TabPageType) child;
					T result = renderTabPage(tabPage, generateId); 
					component.add(result);
				}
				else if( child instanceof IteratorType ) {
					IteratorType iterator = (IteratorType) child;
					List<T> result = renderIterator(iterator, generateId);
					component.add(result);
				}
			}
		}
		return (T) component;
	}

	protected T renderTabPage(TabPageType tabPage, boolean generateId) throws SenroTemplateException {
		SenroContainerComponent<T> component = 
			new SenroContainerComponent<T>();
		
		component.setRenderComponent( ComponentAssociation.TABPAGE );
		String title = this.<String> evaluate(tabPage.getTitle());
		
		component.setModel(new Model<StringModelObject>(new StringModelObject(title)));
		
		if( tabPage.getLabelOrTextFieldOrTextArea() != null ) {
			List<UIComponentType> children = tabPage.getLabelOrTextFieldOrTextArea();
			for( UIComponentType child : children ) {
				List<T> components = renderGeneric(child, generateId);
				component.add(components);
			}
		}
		return (T) component;
	}

	protected T renderList(ListType uiComponent, boolean generateId) throws SenroTemplateException {
		SenroComponent<ListModelObject> component = new SenroComponent<ListModelObject>();
		component.setRenderComponent(ComponentAssociation.LIST);
		component.put(SIDComponent.List_Entity, uiComponent.getEntity());
		Object evalResult = evaluate(uiComponent.getEntity());
		if(evalResult instanceof MetadataClass) {
			MetadataClass entity = this.<MetadataClass> evaluate(uiComponent.getEntity());
			List<String> propertyList = new ArrayList<String>();
			ListModelObject model = new ListModelObject(propertyList);
			for( MetadataProperty property : entity.getProperties() ) {
				String name = (String) property.get("name");
				propertyList.add(name);
			}
			
			component.setModel(new Model<ListModelObject>(model));
		}
		
		return (T) component;
	}

	/**
	 * Renders a {@link GridType} component 
	 * @param grid the provided {@link GridType} component
	 * @param generateId whether to generate a default unique id
	 * @return the rendered {@link SenroContainerComponent}
	 * @throws SenroTemplateException
	 */
	public T renderGrid( GridType grid, boolean generateId ) throws SenroTemplateException {
		T result = (T) new SenroContainerComponent<T>();
		((SenroContainerComponent)result).setLayout( buildTableLayout(grid) );
		result.setLayoutData(new SenroCellLayout());
		result.setRenderComponent(ComponentAssociation.GRID);
		result.setId(this.<String> evaluate(grid.getId()));
		result.setName(this.<String> evaluate(grid.getName()));
		addVariable(grid);
		
		if( grid.getComponents() != null && grid.getComponents().getLabelOrTextFieldOrTextArea() != null ) {
			List<UIComponentType> children = grid.getComponents().getLabelOrTextFieldOrTextArea();
			for( UIComponentType child : children ) {
				List<T> components = renderGeneric(child, generateId);
				((SenroContainerComponent)result).add(components);
			}
		}
		return result;
	}
	
	/**
	 * Renders a list of {@link PopupType} components 
	 * @param grid the provided list of {@link PopupType} components
	 * @param generateId whether to generate a default unique id
	 * @return the rendered list of {@link SenroContainerComponent}
	 * @throws SenroTemplateException
	 */
	public <K extends SenroContainerComponent> List<K> renderPopups( Set<PopupType> popups ) throws SenroTemplateException {
		List<K> result = new ArrayList<K>();
		for( PopupType popup : popups ) {
			K popupComp = (K) renderGrid(popup, false);
			popupComp.setRenderComponent(ComponentAssociation.POPUP);
			result.add(popupComp);
		}
		return result;
	}
	
	/**
	 * Renders a {@link TreeType} component
	 * @param tree the provided {@link TreeType} component
	 * @param generateId whether to generate a default unique id
	 * @return the rendered {@link SenroComponent}
	 * @throws SenroTemplateException
	 */
	public T renderTree( TreeType tree, boolean generateId ) throws SenroTemplateException {
		SenroContainerComponent<T> component = new SenroContainerComponent<T>();
		component.setRenderComponent( ComponentAssociation.TREE );
		List<?> children = tree.getTreeNodeAndIterator();
		if( children != null) {
			// 2 options: treenode or iterator
			for(Object child : children) {
				if( child instanceof TreeNodeType ) {
					TreeNodeType treeNode = (TreeNodeType) child;
					T result = renderTreeNode(treeNode, generateId); 
					component.add(result);
				}
				else if( child instanceof IteratorType ) {
					IteratorType iterator = (IteratorType) child;
					List<T> result = renderIterator(iterator, generateId);
					component.add(result);
				}
			}
		}
		return (T) component;
	}
	
	/**
	 * Renders a {@link TreeNodeType} component
	 * @param treeNode the provided {@link TreeNodeType} component
	 * @param generateId whether to generate a default unique id
	 * @return the rendered {@link SenroComponent}
	 * @throws SenroTemplateException
	 */
	public T renderTreeNode( TreeNodeType treeNode, boolean generateId ) throws SenroTemplateException {
		T component = (T) new SenroComponent<StringModelObject>();
		component.setRenderComponent( ComponentAssociation.TREENODE );
		String expression = treeNode.getText();
		String text = this.<String> evaluate(expression);
		component.setModel(new Model<StringModelObject>(new StringModelObject( text )));
		return component;
	}
	
	/**
	 * Renders a {@link IteratorType} component
	 * @param iterator the provided {@link IteratorType} component
	 * @param generateId whether to generate a default unique id
	 * @return the rendered list of {@link SenroComponent} components
	 * @throws SenroTemplateException
	 */
	public List<T> renderIterator( IteratorType iterator, boolean generateId ) throws SenroTemplateException {
		List<T> components = new ArrayList<T>();
		String list = iterator.getList();
		
		Object evalResult = evaluate(list);
		if( evalResult instanceof Iterable ) {
			Iterable<?> result = this.<Iterable<?>> evaluate(list);
			addVariable(iterator);
		
			if( iterator.getLabelOrTextFieldOrTextArea().size() == 0 ) {
				throw new SenroTemplateException("Iterator content is empty for: "+iterator.getName());
			}
			else if( iterator.getLabelOrTextFieldOrTextArea().size() > 1 ) {
				throw new SenroTemplateException("Iterator is restricted to only one component: "+iterator.getName());
			}
			String filterCondition = iterator.getFilterCondition();
			Object iteratorResult = iterator.getLabelOrTextFieldOrTextArea().get(0);
			if( result != null ) {
				Iterator<?> iter = result.iterator();
				int i=0;
				while( iter.hasNext() ) {
					Object currentObj = iter.next();
					iterator.setCurrentItem(currentObj);
					if( !StringUtils.isEmpty(filterCondition) && this.<Boolean> evaluate(filterCondition) ) {
						iterator.setCurrentIndex(i++);
						if( iteratorResult instanceof UIComponentType )
							components.addAll( renderGeneric((UIComponentType) iteratorResult, true) );
						else if( iteratorResult instanceof TreeNodeType ) {
							components.add( renderTreeNode((TreeNodeType) iteratorResult, true) );
						}
					}
					else if( StringUtils.isEmpty(filterCondition) ) {
						iterator.setCurrentIndex(i++);
						if( iteratorResult instanceof UIComponentType )
							components.addAll( renderGeneric((UIComponentType) iteratorResult, true) );
						else if( iteratorResult instanceof TreeNodeType ) {
							components.add( renderTreeNode((TreeNodeType) iteratorResult, true) );
						}
					}
				}
				iterator.setCurrentIndex(i);
			}
		}
		else {
			SenroContainerComponent comp = new SenroContainerComponent(iterator.getId());
			comp.setRenderComponent(ComponentAssociation.ITERATOR);
			comp.put(SIDComponent.Iterator_List, iterator.getList());
			comp.put(SIDComponent.Iterator_FilterCondition, iterator.getFilterCondition());
			Object iteratorResult = iterator.getLabelOrTextFieldOrTextArea().get(0);
			if( iteratorResult instanceof UIComponentType ) {
				comp.add(renderGeneric((UIComponentType) iteratorResult, true));
			}
			else if( iteratorResult instanceof TreeNodeType ) {
				comp.add( renderTreeNode((TreeNodeType) iteratorResult, true) );
			}
			
			components.add((T) comp);
		}
		
		return components;
	}
	
	/**
	 * Renders a {@link ConditionalType} component
	 * @param conditional the provided {@link ConditionalType} component
	 * @param generateId whether to generate a default unique id
	 * @return the rendered list of {@link SenroComponent} components
	 * @throws SenroTemplateException
	 */
	public List<T> renderConditional( ConditionalType conditional, boolean generateId ) throws SenroTemplateException {
		if( conditional.getIf() == null )
			throw new SenroTemplateException("Found Conditional tag without if");
		
		IfType ifType = conditional.getIf();
		String condition = ifType.getCondition();
		if( StringUtils.isEmpty(condition) ) 
			throw new SenroTemplateException("Found If tag with empty condition");
		
		Boolean result = this.<Boolean>evaluate(condition);
		if( result ) {
			if( ifType.getLabelOrTextFieldOrTextArea().iterator().hasNext() ) {
				UIComponentType uiComponentType = ifType.getLabelOrTextFieldOrTextArea().iterator().next();
				List<T> comp = renderGeneric(uiComponentType, generateId);
				return comp;
			}	
		}
		else {
			ElseType elseType = conditional.getElse();
			if( elseType != null && elseType.getLabelOrTextFieldOrTextArea().iterator().hasNext() ) {
				UIComponentType uiComponentType = elseType.getLabelOrTextFieldOrTextArea().iterator().next();
				List<T> comp = renderGeneric(uiComponentType, generateId);
				return comp;
			}
		}
		return new ArrayList<T>();
	}
	
	/**
	 * Renders a {@link GridAllocatorRendererType} component
	 * @param gridAllocatorRenderer conditional the provided {@link GridAllocatorRendererType} component
	 * @param generateId generateId whether to generate a default unique id
	 * @return the rendered {@link SenroContainerComponent} component
	 * @throws SenroTemplateException
	 */
	public T renderGridAllocatorRenderer( GridAllocatorRendererType gridAllocatorRenderer, boolean generateId ) throws SenroTemplateException {
		if( StringUtils.isEmpty(gridAllocatorRenderer.getGridAllocator()) )
			throw new SenroTemplateException("Attribute gridAllocator is null: "+gridAllocatorRenderer.getName());
		
		GridAllocator gridAllocator = this.<GridAllocator> evaluate(gridAllocatorRenderer.getGridAllocator());
		SenroContainerComponent<SenroComponent> result = gridAllocator.render();
		
		result.setRenderComponent( ComponentAssociation.GRID );
		result.setLayoutData( buildCellLayout(gridAllocatorRenderer) );
		
		return (T) result;
	}
	
	/**
	 * Renders the server components like {@link GridAllocatorType}
	 * into the runtime context
	 * @param server the provided {@link Server} template object
	 * @throws SenroTemplateException
	 */
	public void renderServer( Server server ) throws SenroTemplateException {
		if( server.getGridAllocator().size() > 0 ) {
			GridAllocatorType gridAllocatorType = server.getGridAllocator().iterator().next();
			
			if( StringUtils.isEmpty(gridAllocatorType.getName()) )
				throw new SenroTemplateException("Found component with empty name (id: "+gridAllocatorType.getId()+")");
			if( internalContext.get(gridAllocatorType.getName()) != null )
				throw new SenroTemplateException("Duplicate variable name: "+gridAllocatorType.getName());
			
			if( gridAllocatorType.getColumns() == null )
				System.out.println("WARNING: GridAllocator columns not specified. Defaulting to "+GridAllocator.columns);
			if( gridAllocatorType.getColumns().intValue() == 0 )
				throw new SenroTemplateException("Number of columns is 0 for: "+gridAllocatorType.getName());
			
			MetadataClass entity = this.<MetadataClass> evaluate(gridAllocatorType.getEntity());
			if( entity == null )
				throw new SenroTemplateException("GridAllocator entity is null");

			GridAllocator gridAllocator = new GridAllocator( entity, gridAllocatorType.getColumns().intValue() );
			internalContext.put(gridAllocatorType.getName(), gridAllocator);
		}
	}
	
	public List<SenroAssoc> renderAssociations(Associations associations, T rootComponent) throws SenroTemplateException {
		List<SenroAssoc> result = new ArrayList<SenroAssoc>();
		
		for(Assoc assoc : associations.getAssoc()) {
			String assocType = assoc.getType();			
			Map<String, String> bindings = new HashMap<String, String>();
			Map<String, SenroAspect> aspects = new HashMap<String, SenroAspect>();
			
			for( Binding binding : assoc.getBinding() ) {
				String name = binding.getName();
				String strVal = binding.getValue();
				String aspect = binding.getAspect();
				int event = new Integer(binding.getEvent());

				bindings.put(name, strVal);
				aspects.put(name, new SenroAspect(aspect, event));
			}
			
			SenroAssoc baseAssoc = AssociationRegistry.get(assocType);
			baseAssoc.setType(assocType);
			
			if( baseAssoc != null ) {
				baseAssoc.setBindings( (Map) resolveBindings(bindings, rootComponent, baseAssoc) );
				baseAssoc.setAspects(aspects);
				result.add(baseAssoc);
			}
			else 
				throw new SenroTemplateException("Unknown association type: "+assocType);
		}
		return result;
	}
	
	protected Map<String, T> resolveBindings( Map<String, String> bindings, SenroComponent rootComponent, SenroAssoc assoc) throws SenroTemplateException 
	{
		Map<String, T> result = new HashMap<String, T>();
		for( String key : bindings.keySet() ) {
			String id = bindings.get(key);
			T bindObj = findComponent( (T) rootComponent, id );
			ComponentAssociation signature = assoc.getBindingSignatures().get(key);
			if( signature != null && !signature.equals(bindObj.getRenderComponent())) 
				throw new SenroTemplateException(
					"Component "+bindObj.getId()+
					" cannot be bound to association "+assoc.getType()+
					" on binding '"+key+"': Incompatible types ( found: "+bindObj.getRenderComponent()+" but needed: "+signature);
			
			result.put(key, (T) bindObj);
		}
		return result;
	}
	
	protected T findComponent( T rootComponent, String id ) {
		if( id.equals(rootComponent.getId()) ) { 
			return rootComponent;		
		}
		else if( rootComponent instanceof SenroContainerComponent ) {
			SenroContainerComponent<T> rootContainer = (SenroContainerComponent<T>) rootComponent;
			for( T child : rootContainer.getComponents() ) {
				T found = findComponent(child, id);
				if( found != null )
					return found;
			}
		}
		return null;
	}
	
	/**
	 * Evaluates a generic MVEL 2.0 template expression and casts the output to the given output type
	 * @param <T> the expected output type.
	 * @param expression the template expression to evaluate
	 * @return the evaluation result cast to the type T
	 * @throws SenroTemplateException
	 */
	public <K> K evaluate( String expression ) throws SenroTemplateException {
		if( expression == null )
			return null;
		
		if( expression.contains("@{") && expression.length() > 2) {
			try {
				CompiledTemplate compiledExpr = TemplateCompiler.compileTemplate(expression);
				Object result = TemplateRuntime.execute(compiledExpr, internalContext);
				return (K) result;
			} catch (Exception e) {
				//throw new SenroTemplateException(e.getMessage());
				logger.warn("Expression "+expression+" could not be evaluated within this context");
				return (K) expression;
			}
		}
		else {
			return (K) expression;
		}
	}
		
	protected void addVariable( BasicComponentType component ) throws SenroTemplateException {
		if( StringUtils.isEmpty(component.getName()) )
			throw new SenroTemplateException("Found component with empty name (id: "+component.getId()+")");
		if( internalContext.get(component.getName()) != null )
			throw new SenroTemplateException("Duplicate variable name: "+component.getName());
		else
			internalContext.put(component.getName(), component);
	}
	
	protected SenroTableLayout buildTableLayout( GridType gridType ) throws SenroTemplateException {
		SenroTableLayout layout = new SenroTableLayout();
		if( gridType.getColumns() != null && gridType.getColumns().getColumn() != null )
			layout.setColumns( gridType.getColumns().getColumn().size() );
		else
			throw new SenroTemplateException("Columns element non-existent or empty.");
		
		if( gridType.getRows() != null && gridType.getRows().getRow() != null )
			layout.setRows( gridType.getRows().getRow().size() );
		else
			throw new SenroTemplateException("Rows element non-existent or empty.");
		
		for( GridSpecificationElement column : gridType.getColumns().getColumn() ) {
			SenroTableLayout.ColumnDefault columnDefault = new SenroTableLayout.ColumnDefault();
			if( "Left".equals(column.getAlignment()) ) {
				columnDefault.setHorizontalAlignment(HorizontalAlignment.LEFT);
			}
			else if( "Center".equals(column.getAlignment()) ) {
				columnDefault.setHorizontalAlignment(HorizontalAlignment.CENTER);
			}
			else if( "Right".equals(column.getAlignment()) ) {
				columnDefault.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			}
			else if( "Fill".equals(column.getAlignment()) ) {
				columnDefault.setHorizontalAlignment(HorizontalAlignment.FILL);
			}
			layout.addColumnDefault(columnDefault);
		}
		for( GridSpecificationElement row : gridType.getRows().getRow() ) {
			SenroTableLayout.RowDefault rowDefault = new SenroTableLayout.RowDefault();
			if( "Top".equals(row.getAlignment()) ) {
				rowDefault.setVerticalAlignment(VerticalAlignment.TOP);
			}
			else if( "Middle".equals(row.getAlignment()) ) {
				rowDefault.setVerticalAlignment(VerticalAlignment.MIDDLE);
			}
			else if( "Bottom".equals(row.getAlignment()) ) {
				rowDefault.setVerticalAlignment(VerticalAlignment.BOTTOM);
			}
			else if( "Fill".equals(row.getAlignment()) ) {
				rowDefault.setVerticalAlignment(VerticalAlignment.FILL);
			}
			layout.addRowDefault(rowDefault);
		}

		return layout;
	}
	
	protected SenroCellLayout buildCellLayout( UIComponentType componentType ) throws SenroTemplateException {
		SenroCellLayout cellLayout = new SenroCellLayout();
		if( componentType.getRow() != null ) {
			cellLayout.setRowExpr( evaluate(componentType.getRow()).toString() );
			try {
				int row = Integer.parseInt( cellLayout.getRowExpr() );
				cellLayout.setRow( row );
			} catch (NumberFormatException e) {}
		}
		else
			throw new SenroTemplateException("'row' attribute is required for element: "+componentType.getClass().getSimpleName());
		
		if( !StringUtils.isEmpty(componentType.getCol()) ) {
			cellLayout.setColumnExpr(evaluate(componentType.getCol()).toString());
			try {
				int col = Integer.parseInt( cellLayout.getColumnExpr() );
				cellLayout.setColumn( col );
			} catch (NumberFormatException e) {}
		}
		else
			throw new SenroTemplateException("'col' attribute is required for element: "+componentType.getClass().getSimpleName());
		
		if( componentType.getColSpan() != null ) {
			cellLayout.setColSpanExpr(evaluate(componentType.getColSpan()).toString());
			try {
				int colSpan = Integer.parseInt(cellLayout.getColSpanExpr());
				cellLayout.setColSpan( colSpan );
			} catch (NumberFormatException e) {}
		}
		
		if( componentType.getRowSpan() != null ) {
			cellLayout.setRowSpanExpr(evaluate(componentType.getRowSpan()).toString());
			try {
				int rowSpan = Integer.parseInt(cellLayout.getRowSpanExpr());
				cellLayout.setRowSpan( rowSpan );
			} catch (NumberFormatException e) {}
		}
		
		if( !StringUtils.isEmpty(componentType.getHalign()) ) {
			String align = componentType.getHalign();
			if( "Left".equals(align) )
				cellLayout.setHorizontalAlignment(HorizontalAlignment.LEFT);
			if( "Right".equals(align) )
				cellLayout.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			if( "Center".equals(align) )
				cellLayout.setHorizontalAlignment(HorizontalAlignment.CENTER);
		}
		if( !StringUtils.isEmpty(componentType.getValign()) ) {
			String align = componentType.getValign();
			if( "Top".equals(align) )
				cellLayout.setVerticalAlignment(VerticalAlignment.TOP);
			if( "Center".equals(align) )
				cellLayout.setVerticalAlignment(VerticalAlignment.MIDDLE);
			if( "Bottom".equals(align) )
				cellLayout.setVerticalAlignment(VerticalAlignment.BOTTOM);
		}
		
		return cellLayout;
	}
	
	protected HorizontalAlignment getColumnDefaultAlignment( UIComponentType componentType, GridType grid ) throws SenroTemplateException {
		int column = Integer.parseInt( evaluate(componentType.getCol()).toString() );

		if( column > grid.getColumns().getColumn().size() )
			throw new SenroTemplateException("Component: "+componentType.getId()+", column "+column+" is greater than the specified number of columns for Grid: "+grid.getId());
		
		GridSpecificationElement elem = grid.getColumns().getColumn().get(column-1);
		if("Left".equals(elem.getAlignment()))
			return HorizontalAlignment.LEFT;
		else if( "Center".equals(elem.getAlignment()) )
			return HorizontalAlignment.CENTER;
		else if( "Right".equals(elem.getAlignment()) )
			return HorizontalAlignment.RIGHT;
		else if( "Fill".equals(elem.getAlignment()) )
			return HorizontalAlignment.CENTER;
		else
			return HorizontalAlignment.CENTER; 
	}
	
	protected VerticalAlignment getRowDefaultAlignment( UIComponentType componentType, GridType grid ) throws SenroTemplateException {
		int row = this.<Integer> evaluate(componentType.getRow());
		if( row > grid.getRows().getRow().size() )
			throw new SenroTemplateException("Component: "+componentType.getId()+", row "+row+" is greater than the specified number of rows for Grid: "+grid.getId());
		
		GridSpecificationElement elem = grid.getRows().getRow().get(row-1);
		if("Top".equals(elem.getAlignment()))
			return VerticalAlignment.TOP;
		else if( "Middle".equals(elem.getAlignment()) )
			return VerticalAlignment.MIDDLE;
		else if( "Bottom".equals(elem.getAlignment()) )
			return VerticalAlignment.BOTTOM;
		else if( "Fill".equals(elem.getAlignment()) )
			return VerticalAlignment.MIDDLE;
		else
			return VerticalAlignment.MIDDLE; 
	}
}
