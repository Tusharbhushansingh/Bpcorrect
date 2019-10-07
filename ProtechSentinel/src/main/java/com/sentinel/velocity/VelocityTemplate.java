package com.sentinel.velocity;

import java.io.StringWriter;
import java.util.Calendar;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.tools.generic.ComparisonDateTool;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Component;

/**
 * This Class created for Velocity Engine. Velocity Engine we used for Email
 * process. We required some fixed format of email templates.
 * 
 * @author dushyant
 *
 */
@Component
public class VelocityTemplate {

	/**
	 * This Class created for Velocity Engine. Velocity Engine we used for Email
	 * process. We required some fixed format of email templates.
	 */
	public VelocityTemplate() {
	}

	/**
	 * This Method we used for Velocity Template. First initialize Velocity
	 * Engine then read template data.
	 * 
	 * @param path
	 *            , Path of template file.
	 * @param templateName
	 *            , Template File Name.
	 * @param object
	 *            , Template Parameter Objects.
	 * @return , Return String Template Data.
	 */
	public String readTemplate(String path, String templateName, Object object, String invoice_id, String month, String amount) {
		try {

			// /* first, get and initialize an engine */
			// VelocityEngine ve = new VelocityEngine();
			// ve.init();
			// /* next, get the Template */
			// Template t = ve.getTemplate(templateName);
			// /* create a context and add data */
			// VelocityContext context = new VelocityContext();
			// context.put("object", model);
			// /* now render the template into a StringWriter */
			// StringWriter writer = new StringWriter();
			// t.merge(context, writer);
			// /* show the World */
			// return writer.toString();

			/* first, get and initialize an engine */
			VelocityEngine ve = new VelocityEngine();
			// ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			// ve.setProperty("classpath.resource.loader.class",
			// ClasspathResourceLoader.class.getName());
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, path);
			ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
			ve.init();

			/* add that list to a VelocityContext */
			VelocityContext context = new VelocityContext();
			context.put("object", object);
			context.put("invoice", invoice_id);
			context.put("date", Calendar.getInstance().getTime());
			context.put("month", month);
			context.put("amount", amount);
			/* get the Template */
			Template t = ve.getTemplate(templateName);
			/* now render the template into a Writer */
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			/* use the output in your email body */

			return writer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
