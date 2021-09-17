package com.fengwenyi.codegenerator.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fengwenyi.codegenerator.Custom;
import com.fengwenyi.codegenerator.bo.CodeGeneratorBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MyGenerator {

    private final CodeGeneratorBo bo;

    private  static String path=null;
    public MyGenerator(CodeGeneratorBo bo) {
        this.bo = bo;
    }
    public void myAutoGenerator(String outdir){
        path=outdir;
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outdir);
        gc.setAuthor(bo.getAuthor());
        gc.setOpen(false);
        gc.setSwagger2(bo.getSwaggerSupport() !=null ? bo.getSwaggerSupport() : false); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(bo.getDbUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName(bo.getDriver());
        dsc.setUsername(bo.getUsername());
        dsc.setPassword(bo.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        String[] packageNames = bo.getPackageName().split("\\.");
        pc.setModuleName(packageNames[packageNames.length - 1]);
        // 此处设置包名，需要自定义
        int lastIndex = bo.getPackageName().lastIndexOf(".");
        pc.setParent((lastIndex > 0 ? bo.getPackageName().substring(0, lastIndex) : bo.getPackageName()));
        pc.setController(bo.getPackageController());
        pc.setEntity(bo.getPackageEntity());
        pc.setMapper(bo.getPackageMapper());
        pc.setXml(bo.getPackageMapperXml());
        pc.setService(bo.getPackageService());
        pc.setServiceImpl(bo.getPackageServiceImpl());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
         String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
//        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setMapper("templates/mapper.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("vip.mate.core.database.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("vip.mate.core.web.controller.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(bo.getTableNames());
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix("表前缀");
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
