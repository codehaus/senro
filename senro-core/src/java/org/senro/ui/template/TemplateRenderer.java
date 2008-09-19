package org.senro.ui.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mvel.UnresolveablePropertyException;
import org.mvel.templates.CompiledTemplate;
import org.mvel.templates.TemplateCompiler;
import org.mvel.templates.TemplateRuntime;
import org.senro.exception.SenroTemplateException;
import org.senro.gwt.client.model.ui.CellLayout;
import org.senro.gwt.client.model.ui.HorizontalAlignment;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.SenroTableLayout;
import org.senro.gwt.client.model.ui.VerticalAlignment;
import org.senro.gwt.client.model.ui.binding.BooleanModel;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.DataModel;
import org.senro.gwt.client.model.ui.binding.MapModel;
import org.senro.gwt.client.model.ui.binding.StringModel;
import org.senro.ui.template.model.BasicComponentType;
import org.senro.ui.template.model.ButtonType;
import org.senro.ui.template.model.CheckBoxType;
import org.senro.ui.template.model.ComboBoxType;
import org.senro.ui.template.model.ConditionalType;
import org.senro.ui.template.model.DateFieldType;
import org.senro.ui.template.model.GridSpecificationElement;
import org.senro.ui.template.model.GridType;
import org.senro.ui.template.model.IteratorType;
import org.senro.ui.template.model.LabelType;
import org.senro.ui.template.model.RootPanelType;
import org.senro.ui.template.model.TemplateType;
import org.senro.ui.template.model.TextAreaType;
import org.senro.ui.template.model.TextFieldType;
import org.senro.ui.template.model.TreeNodeType;
import org.senro.ui.template.model.TreeType;
import org.senro.ui.template.model.UIComponentType;

/**
 * 
 * @author FlaviusB
 *
 */
public class TemplateRenderer {
	private Map<String, Object> internalContext = new HashMap<String, Object>();
	private int iteratorCount = 0;
	
	public TemplateRenderer(Map<String, Object> context) {	
		this.internalContext = context;
	}
	
	public List<SenroComponent> renderGeneric( UIComponentType uiComponent) throws SenroTemplateException {
		return renderGeneric(uiComponent, false);
	}
	
	public List<SenroComponent> renderGeneric( UIComponentType uiComponent, boolean generateId ) throws SenroTemplateException {
		List<SenroComponent> result = new ArrayList<SenroComponent>();
		if( uiComponent instanceof LabelType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.LABEL );
			String expression = ((LabelType)uiComponent).getText();
			String text = this.<String> evaluate(expression);
			component.setModel(new DataModel<StringModel>(new StringModel( text )));

			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TextFieldType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.TEXTFIELD );
			component.setModel(new DataModel<StringModel>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TextAreaType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.TEXTAREA );
			component.setModel(new DataModel<StringModel>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof CheckBoxType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.CHECKBOX );
			component.setModel(new DataModel<BooleanModel>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof ComboBoxType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.COMBOBOX );
			component.setModel(new DataModel<MapModel>());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof ButtonType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.BUTTON );
			component.setModel(new DataModel<StringModel>(new StringModel( ((ButtonType)uiComponent).getLabel() )));
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add(component);
			addVariable(uiComponent);
		}
		else if( uiComponent instanceof DateFieldType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.DATEFIELD );
			component.setModel(new DataModel());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof TreeType ) {
			SenroComponent component = renderTree( (TreeType)uiComponent );
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof RootPanelType ) {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.ROOTPANEL );
			component.setModel(new DataModel());
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		else if( uiComponent instanceof GridType ) {
			SenroComponent component = renderGrid((GridType)uiComponent) ;
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add(component);
		}
		else if( uiComponent instanceof IteratorType ) {
			List<SenroComponent> components = renderIterator((IteratorType)uiComponent) ;
			for( SenroComponent component : components ) {
				if( generateId ) {
					uiComponent.setId( "iterator"+iteratorCount );
					uiComponent.setName( "iterator"+iteratorCount );
				} 

				component.setId( uiComponent.getId() );
				component.setName( uiComponent.getName() );
				component.setLayoutData( buildCellLayout(uiComponent) );
			}
			result.addAll(components);
		}
		else if( uiComponent instanceof ConditionalType ) {
			SenroComponent component = renderConditional((ConditionalType)uiComponent) ;
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			result.add(component);
		}
		else if( uiComponent instanceof TemplateType ) {
			throw new UnsupportedOperationException("Templates not supported yet!");
		}
		else {
			SenroComponent component = new SenroComponent();
			component.setRenderComponent( ComponentAssociation.CUSTOM );
			
			if( generateId ) {
				uiComponent.setId( "iterator"+iteratorCount );
				uiComponent.setName( "iterator"+iteratorCount );
			} 

			component.setId( uiComponent.getId() );
			component.setName( uiComponent.getName() );
			component.setLayoutData( buildCellLayout(uiComponent) );
			addVariable(uiComponent);
			result.add(component);
		}
		
		return result;
	}
	
	public SenroContainerComponent renderGrid( GridType grid ) throws SenroTemplateException {
		SenroContainerComponent result = new SenroContainerComponent();
		result.setLayout( buildTableLayout(grid) );
		result.setRenderComponent(ComponentAssociation.GRID);
		result.setId(grid.getId());
		result.setName(grid.getName());
		addVariable(grid);
		
		if( grid.getComponents() != null && grid.getComponents().getLabelOrTextFieldOrTextArea() != null ) {
			List<UIComponentType> children = grid.getComponents().getLabelOrTextFieldOrTextArea();
			for( UIComponentType child : children ) {
				List<SenroComponent> components = renderGeneric(child);
				result.add(components);
			}
		}
		return result;
	}
	
	public SenroComponent renderTree( TreeType tree ) throws SenroTemplateException {
		SenroContainerComponent component = new SenroContainerComponent();
		component.setRenderComponent( ComponentAssociation.TREE );
		List children = tree.getTreeNodeAndIterator();
		if( children != null) {
			// 2 options: treenode or iterator
			for(Object child : children) {
				if( child instanceof TreeNodeType ) {
					TreeNodeType treeNode = (TreeNodeType) child;
					List<SenroComponent> result = renderTreeNode(treeNode); 
					component.add(result);
				}
				else if( child instanceof IteratorType ) {
					IteratorType iterator = (IteratorType) child;
					List<SenroComponent> result = renderIterator(iterator);
					component.add(result);
				}
			}
		}
		return component;
	}
	
	public List<SenroComponent> renderTreeNode( TreeNodeType treeNode ) throws SenroTemplateException {
		return renderTreeNode(treeNode, false);
	}
	
	public List<SenroComponent> renderTreeNode( TreeNodeType treeNode, boolean generateId ) throws SenroTemplateException {
		List<SenroComponent> components = new ArrayList<SenroComponent>();
		for( UIComponentType uiComponent : treeNode.getLabelOrTextFieldOrTextArea() )
			components.addAll( renderGeneric(uiComponent,generateId) );
		
		return components;
	}
	
	public List<SenroComponent> renderIterator( IteratorType iterator ) throws SenroTemplateException {
		List<SenroComponent> components = new ArrayList<SenroComponent>();
		String list = iterator.getList();
		Iterable<?> result = this.<Iterable<?>> evaluate(list);
		addVariable(iterator);
		
		if( iterator.getLabelOrTextFieldOrTextArea().size() == 0 ) {
			throw new SenroTemplateException("Iterator content is empty for: "+iterator.getName());
		}
		else if( iterator.getLabelOrTextFieldOrTextArea().size() > 1 ) {
			throw new SenroTemplateException("Iterator is restricted to only one component: "+iterator.getName());
		}
		Object iteratorResult = iterator.getLabelOrTextFieldOrTextArea().get(0);
		if( result != null ) {
			Iterator<?> iter = result.iterator();
			while( iter.hasNext() ) {
				Object currentObj = iter.next();
				iterator.setCurrentItem(currentObj);
				
				if( iteratorResult instanceof UIComponentType )
					components.addAll( renderGeneric((UIComponentType) iteratorResult, true) );
				else if( iteratorResult instanceof TreeNodeType ) {
					components.addAll( renderTreeNode((TreeNodeType) iteratorResult, true) );
				}
				
				iteratorCount++;
			}
		}
		return components;
	}
	
	public SenroComponent renderConditional( ConditionalType conditional ) {
		SenroComponent component = new SenroComponent();
		return component;
	}
	
	private <T> T evaluate( String expression ) throws SenroTemplateException {
		if( expression.contains("@{") && expression.length() > 2) {
			try {
				CompiledTemplate compiledExpr = TemplateCompiler.compileTemplate(expression);
				Object result = TemplateRuntime.execute(compiledExpr, internalContext);
				return (T) result;
			} catch (UnresolveablePropertyException e) {
				throw new SenroTemplateException(e.getMessage());
			}
		}
		else {
			return (T) expression;
		}
	}
		
	private void addVariable( BasicComponentType component ) throws SenroTemplateException {
		if( StringUtils.isEmpty(component.getName()) )
			throw new SenroTemplateException("Found component with empty name (id: "+component.getId()+")");
		if( internalContext.get(component.getName()) != null )
			throw new SenroTemplateException("Duplicate variable name: "+component.getName());
		else
			internalContext.put(component.getName(), component);
	}
	
	private SenroTableLayout buildTableLayout( GridType gridType ) throws SenroTemplateException {
		SenroTableLayout layout = new SenroTableLayout();
		if( gridType.getColumns() != null && gridType.getColumns().getColumn() != null )
			layout.setColumns( gridType.getColumns().getColumn().size() );
		else
			throw new SenroTemplateException("Columns element non-existent or empty.");
		
		if( gridType.getRows() != null && gridType.getRows().getRow() != null )
			layout.setRows( gridType.getRows().getRow().size() );
		else
			throw new SenroTemplateException("Rows element non-existent or empty.");
		
		return layout;
	}
	
	private CellLayout buildCellLayout( UIComponentType componentType ) throws SenroTemplateException {
		CellLayout cellLayout = new CellLayout();
		if( componentType.getRow() != null )
			cellLayout.setRow( componentType.getRow().intValue() );
		else
			throw new SenroTemplateException("'row' attribute is required for element: "+componentType.getClass().getSimpleName());
		if( componentType.getCol() != null )
			cellLayout.setColumn( componentType.getCol().intValue() );
		else
			throw new SenroTemplateException("'col' attribute is required for element: "+componentType.getClass().getSimpleName());
		if( componentType.getColSpan() != null )
			cellLayout.setColSpan( componentType.getColSpan().intValue() );
		if( componentType.getRowSpan() != null )
			cellLayout.setRowSpan( componentType.getRowSpan().intValue() );
		
		return cellLayout;
	}
	
	public HorizontalAlignment getColumnDefaultAlignment( UIComponentType componentType, GridType grid ) throws SenroTemplateException {
		int column = componentType.getCol().intValue();

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
			return HorizontalAlignment.FILL;
		else
			return HorizontalAlignment.CENTER; 
	}
	
	public VerticalAlignment getRowDefaultAlignment( UIComponentType componentType, GridType grid ) throws SenroTemplateException {
		int row = componentType.getRow().intValue();
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
			return VerticalAlignment.FILL;
		else
			return VerticalAlignment.MIDDLE; 
	}
}
