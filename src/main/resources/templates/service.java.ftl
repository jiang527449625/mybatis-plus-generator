package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.ytsoft.medical.common.mybatis.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 添加${table.comment!}
     *
     * @param ${entity?uncap_first} ${table.comment!}
     * @return int
     */
    int insertModel(${entity} ${entity?uncap_first});

    /**
     * 删除${table.comment!}
     *
     * @param ${entity?uncap_first} ${table.comment!}
     * @return int
     */
    int deleteByModel(${entity} ${entity?uncap_first});

    /**
     * 修改${table.comment!}
     *
     * @param ${entity?uncap_first} ${table.comment!}
     * @return int
     */
    int update(${entity} ${entity?uncap_first});

    /**
     * 查询数据
     *
     * @param ${entity?uncap_first} ${table.comment!}
     * @return ${entity}
     */
    ${entity} findByModel(${entity} ${entity?uncap_first});

    /**
     * 查询数据集合
     *
     * @param ${entity?uncap_first} ${table.comment!}
     * @return ${entity}
     */
    List<${entity}> findModelList(${entity} ${entity?uncap_first});

    /**
    * 查询${table.comment!}分页数据
    *
    * @param page      页码
    * @param size 每页条数
    * @return IPage<${entity}>
    */
    IPage<${entity}> selectByPage(${entity} ${entity?uncap_first}, Integer page, Integer size);
}
</#if>
