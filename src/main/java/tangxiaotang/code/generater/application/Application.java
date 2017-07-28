package tangxiaotang.code.generater.application;

import tangxiaotang.code.generater.application.config.Config;
import tangxiaotang.code.generater.application.db.DataBase;
import tangxiaotang.code.generater.application.db.Table;
import tangxiaotang.code.generater.application.model.Context;
import tangxiaotang.code.generater.application.util.DaoGenerator;
import tangxiaotang.code.generater.application.util.EntityGenerator;
import tangxiaotang.code.generater.application.util.MapperGenerator;

import java.util.List;

/**
 * Created by tangxiaotang on 2017/7/27.
 * 生成代码主入口
 */
public class Application {

    public static void main(String...args) throws Exception {
        //初始化上下文--->完成数据库链接 等操作
        Config cfg = Config.init();
        DataBase db = new DataBase(cfg);
        //获取欲导出的表
        List<Table> tables = db.getTables();
        if(tables == null || tables.isEmpty()){
            throw new Exception("请检查配置，没有要生成代码的表");
        }
        for(Table table: tables) {
            Context context = new Context();
            context.parse(table);

            //生成Entity实体文件并渲染VM
            EntityGenerator entityGenerator = new EntityGenerator();
            entityGenerator.generate(table, context);

            //dao接口生成
            DaoGenerator daoGenerator = new DaoGenerator();
            daoGenerator.generate(table, context);
            //xml文件生成
            MapperGenerator mapperGenerator = new MapperGenerator();
            mapperGenerator.generate(table, context);
            System.out.println("自动生成表【 " + table.getTableName() + "】的代码!");

        }

    }
}
