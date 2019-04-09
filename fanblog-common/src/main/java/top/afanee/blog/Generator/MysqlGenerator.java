package top.afanee.blog.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 
 * @ClassName MysqlGenerator
 * @Description TODO(代码生成类)
 * @author Fan
 * @Date 2019年4月2日 下午7:10:02
 * @version 1.0.0
 */
public class MysqlGenerator {


    public static void main(String[] args) {
        
        //用来获取mybatis-plus.properties文件的配置信息
        final ResourceBundle rb = ResourceBundle.getBundle("admin-config");
        
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rb.getString("OutputDir"));
        gc.setFileOverride(true);   //文件覆盖
        gc.setActiveRecord(true);   //是否支持AR模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor(rb.getString("author"));
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        // gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/
        dsc.setDriverName(rb.getString("druid.driverClassName"));
        dsc.setUrl(rb.getString("druid.url"));
        dsc.setUsername(rb.getString("druid.username"));
        dsc.setPassword(rb.getString("druid.password"));
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { "fanblog_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setCapitalMode(true);              //全局大写设置
        strategy.setDbColumnUnderline(true);        //表名与字段名是否使用了下划线（配置之后生成的实体按照驼峰映射下划线，不在需要TableField注解）
       /* String[] tableArr = new String[] {"fanblog_ask","fanblog_knowledge","fanblog_blog",
                "fanblog_sing_in","fanblog_topic","fanblog_user","fanblog_user_friend","fanblog_category",
                "fanblog_shuoshuo","fanblog_shuoshuo_comment","fanblog_shuoshuo_like","fanblog_message","fanblog_blog_category"};*/
        String[] tableArr = new String[] {
                "fanblog_attachment"};
        strategy.setInclude(tableArr); // 需要生成的表
        //strategy.setExclude(new String[]{"test"}); // 排除生成的表
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parent"));
        // pc.setModuleName("tbldept");//模块名称，单独生成模块时使用！！！！！！！！！！！
        //pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.Impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };

        // 调整 xml 生成目录演示
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirXml")+ "/mappers/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        
    
        // 执行生成
        mpg.execute();
    }

}
