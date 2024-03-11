package com.yeyiyi.plane.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author YeYiYi
 * @date 2024/3/11 10:54
 * @description
 */
@Component
public class MyRunner implements CommandLineRunner {
    private final DataSource dataSource;

    public MyRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        // 仅尝试获取连接以触发连接池初始化
        dataSource.getConnection().close();
        System.out.println("数据库连接已初始化。");
    }
}
