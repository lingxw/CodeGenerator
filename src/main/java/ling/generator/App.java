package ling.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FreeMarkerUtil marker = FreeMarkerUtil.instance();
    	Map<String, Object> dataModel = new HashMap<>();
    	List<String> fields = new ArrayList<String>();
    	fields.add("name");
    	fields.add("path");
    	dataModel.put("packageName", "ling.entity");
    	dataModel.put("className", "Sample");
    	dataModel.put("fields", fields);
    	marker.generate("package_name.java", dataModel, "src/main/resource/output/ling/entity", "Sample");
    }
}
