package com.fengwenyi.codegenerator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Erwin Feng
 * @since 2019-04-17 12:04
 */


@Component
@ConfigurationProperties(prefix = "custom")
@Data
public class Custom {


    /**
     * 代码生成地址
     */
    public String outdir;
    /**
     * 文件下载地址
     */
    public String downloaddir;




}
