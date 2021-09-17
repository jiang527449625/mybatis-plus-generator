package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
@Transactional
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public int insertModel(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.insert(${entity?uncap_first});
    }

    @Override
    public int deleteByModel(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.delete(Wrappers.<${entity}>lambdaUpdate(${entity?uncap_first}));
    }

    @Override
    public int update(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.updateById(${entity?uncap_first});
    }

    @Override
    public ${entity} findByModel(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.selectOne(Wrappers.<${entity}>lambdaQuery(${entity?uncap_first}));
    }

    @Override
    public List<${entity}> findModelList(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.selectList(Wrappers.<${entity}>lambdaQuery(${entity?uncap_first}));
    }

    @Override
    public IPage<${entity}> selectByPage(${entity} ${entity?uncap_first}, Integer page, Integer size) {
        IPage<${entity}> iPage = new Page<>(page,size);
        return ${table.mapperName?uncap_first}.selectPage(iPage,Wrappers.<${entity}>lambdaQuery(${entity?uncap_first}));
    }
}
</#if>
