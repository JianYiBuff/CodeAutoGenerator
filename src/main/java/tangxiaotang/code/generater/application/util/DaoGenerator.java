package tangxiaotang.code.generater.application.util;

import tangxiaotang.code.generater.application.db.Table;
import tangxiaotang.code.generater.application.model.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DaoGenerator extends AbstractGenerator {

	/*
     * (non-Javadoc)
	 * 
	 * @see
	 * com.dd.codegen.generator.AbstractGenerator#generate(com.dd.codegen.db
	 * .Table, com.dd.codegen.bean.Context)
	 */
	@Override
	public void generate(Table table, Context context) throws Exception {
		Map<String, Context> data = new HashMap<String, Context>();
		data.put("data", context);
		String filePath = this.getFilePath(table, context);
		File file = new File(filePath);
		File pFile = file.getParentFile();
		if (!pFile.exists()) {
			pFile.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
			file.createNewFile();
		}
		VelocityUtil.renderFile("dao.vm", filePath, data);
	}

	private String getFilePath(Table table, Context context) {
		String filePath = super.getFilePath(table);
		String packagePath = super.getPackagePath(table);
		StringBuilder path = new StringBuilder(filePath);
		path.append("/domain/").append(packagePath)
			.append("/domain/")
			.append(context.getDao().getClazzName())
			.append(".java");
		return path.toString();
	}
}
