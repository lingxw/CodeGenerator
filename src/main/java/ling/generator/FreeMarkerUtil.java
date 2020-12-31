package ling.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import ling.common.FileUtils;

public class FreeMarkerUtil {
	private static FreeMarkerUtil instance;
	private Configuration config;
	
	private FreeMarkerUtil() {
		config = new Configuration(Configuration.getVersion());
		try {
			config.setDirectoryForTemplateLoading(new File("src/main/resource/templates"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	public static FreeMarkerUtil instance() {
		if (instance == null) {
			instance = new FreeMarkerUtil();
		}
		return instance;
	}
	
	public boolean generate(String template, Object dataModel, String dir, String name) {
		try {
			FileUtils.createDirectory(dir);
			Template temp = config.getTemplate(template);
	        File file = new File(dir + File.separator + name);
	        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	        temp.process(dataModel, out);
	        return true;
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
