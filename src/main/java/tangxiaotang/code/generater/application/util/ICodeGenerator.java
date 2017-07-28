package tangxiaotang.code.generater.application.util;

import tangxiaotang.code.generater.application.db.Table;
import tangxiaotang.code.generater.application.model.Context;

public interface ICodeGenerator {
    void generate(Table table, Context context)throws Exception;
}