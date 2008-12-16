package org.senro.ui.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.senro.Senro;
import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.service.UIServiceRemote;
import org.senro.ui.template.TemplateParser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Requests the new Senro component defined by the given context.
 * 
 * @see SenroContainerComponent
 * @see SenroContext
 * 
 * @author FlaviusB
 * @author CristiS
 */
public class UIServiceRemoteImpl extends RemoteServiceServlet implements UIServiceRemote {
	public SenroContainerComponent getComponent(SenroContext ctx) throws SenroUIException {
		try {
			Senro.init();
			Senro.setSenroContext(ctx);
			
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("senroContext", ctx);
			varMap.put("metadata", Senro.getMetadataManager());

			String mainEntity = (String) ctx.get(SenroContext.MAIN_ENTITY);
			String task = (String) ctx.get(SenroContext.TASK);
			File template = Senro.getTemplate( TaskDefaults.get(task) );
			if( !StringUtils.isEmpty(TaskOverrides.get(mainEntity, task )) ) {
				template = Senro.getTemplate( TaskOverrides.get(mainEntity, task ) );
			}
			
			TemplateParser<SenroContainerComponent> parser = Senro.getTemplateParser();
			parser.setInputStream( new FileInputStream(template) );			
			return parser.render(varMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SenroUIException(e);
		}
	}
}
