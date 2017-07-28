package tangxiaotang.code.generater.application.db;

/**
 * 表中字段定义
 */
public class Column {
	/**
	 * 字段名
	 */
	private String colName;
	/**
	 * 字段数据类型
	 */
	private String colType;
	/**
	 * 字段备注
	 */
	private String comment;

	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
