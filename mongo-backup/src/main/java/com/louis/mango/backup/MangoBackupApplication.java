package com.louis.mango.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动器
 * @author Louis
 * @date Jan 15, 2019
 */
@SpringBootApplication(scanBasePackages={"com.louis.mango"})
//@EnableDiscoveryClient
public class MangoBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoBackupApplication.class, args);
	}
}