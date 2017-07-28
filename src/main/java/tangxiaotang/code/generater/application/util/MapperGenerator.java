package tangxiaotang.code.generater.application.util;

import tangxiaotang.code.generater.application.db.Table;
import tangxiaotang.code.generater.application.model.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MapperGenerator extends AbstractGenerator {

	/* (non-Javadoc)
     * @see com.dd.codegen.ICodeGenerator#generate(com.dd.codegen.db.Table, com.dd.codegen.bean.Context)
	 */
	@Override
	public void generate(Table table, Context context)throws Exception{
		Map<String, Context> data = new HashMap<String, Context>();
		data.put("data", context);
		String filePath = this.getFilePath(table, context);
		File file = new File(filePath);
		File pFile = file.getParentFile();
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		if(!file.exists()){
			file.createNewFile();
		}else{
			file.delete();
			file.createNewFile();
		}
		VelocityUtil.renderFile("mapper.vm", filePath,  data);
	}

	
	private String getFilePath(Table table, Context context) {
		String filePath = super.getFilePath(table);
		//String packagePath = super.getPackagePath(table);
		StringBuilder path = new StringBuilder(filePath);
		path.append("/mybatis_mappers/")
			.append(context.getEntity().getClazzName())
			.append("Mapper.xml");
		
		StringBuilder mapperPath = new StringBuilder("");

		mapperPath.append(context.getEntity().getClazzName()).append("Mapper.xml");
		context.getMapper().setFilePath(mapperPath.toString());
		return path.toString();
	}
	
	
}
