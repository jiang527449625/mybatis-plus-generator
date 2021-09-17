package com.fengwenyi.codegenerator;

import com.fengwenyi.codegenerator.enums.DbType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Erwin Feng
 * @since 2020/5/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisPlusCodeGeneratorApplication.class)
public class CodeGeneratorTests {
    @Autowired
    private Custom custom;
    @Test
    public void testMySQLCodeGenerator() {
        System.out.println(custom.getOutdir());
    }



}
