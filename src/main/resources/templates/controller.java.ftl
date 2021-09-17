package ${package.Controller};

import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytsoft.medical.common.mybatis.Result;
import com.ytsoft.medical.common.mybatis.ResultGenerator;

import javax.annotation.Resource;
import java.util.List;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{
<#else>public class ${table.controllerName} {
</#if>

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};


    @ApiOperation(value = "新增${table.comment!}", notes = "<#if package.ModuleName??>/${package.ModuleName}</#if>/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/insert")
    @PostMapping("/insert")
    @ResponseBody
    public Result add(@RequestBody ${entity} ${entity?uncap_first}Param){
        ${(table.serviceName?substring(1))?uncap_first}.insertModel(${entity?uncap_first}Param);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除${table.comment!}", notes = "<#if package.ModuleName??>/${package.ModuleName}</#if>/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/delete")
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ${entity} ${entity?uncap_first}Param){
        ${(table.serviceName?substring(1))?uncap_first}.deleteByModel(${entity?uncap_first}Param);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "更新${table.comment!}", notes = "<#if package.ModuleName??>/${package.ModuleName}</#if>/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/update")
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ${entity} ${entity?uncap_first}Param){
        ${(table.serviceName?substring(1))?uncap_first}.update(${entity?uncap_first}Param);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "查看${table.comment!}集合", notes = "<#if package.ModuleName??>/${package.ModuleName}</#if>/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/getList")
    @PostMapping("/getList")
    @ResponseBody
    public Result getList(@RequestBody ${entity} ${entity?uncap_first}Param){
        List<${entity}> list = ${(table.serviceName?substring(1))?uncap_first}.findModelList(${entity?uncap_first}Param);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation(value = "查询${table.comment!}分页数据", notes = "<#if package.ModuleName??>/${package.ModuleName}</#if>/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/pageList")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "${entity?uncap_first}Param", value = "查询条件"),
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @PostMapping("/pageList")
    @ResponseBody
    public Result pageList(@RequestParam ${entity} ${entity?uncap_first}Param,@RequestParam Integer page,
        @RequestParam Integer pageSize){
        IPage<${entity}> pageInfo = ${(table.serviceName?substring(1))?uncap_first}.selectByPage(${entity?uncap_first}Param, page,  pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value = "查询${table.comment!}")
    @PostMapping("/getModel")
    @ResponseBody
    public Result findById(@RequestBody ${entity} ${entity?uncap_first}Param){
        ${entity} ${entity?uncap_first} =  ${(table.serviceName?substring(1))?uncap_first}.findByModel(${entity?uncap_first}Param);
        return ResultGenerator.genSuccessResult(${entity?uncap_first});
    }

}
</#if>