package com.louis.mango.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 启动器
 * @Author: created by wangkaishuang on 2019-06-26
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class MangoMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoMonitorApplication.class, args);
	}
}