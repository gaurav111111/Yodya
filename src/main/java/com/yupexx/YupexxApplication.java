package com.yupexx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.yupexx.bazaar.api.model.dto.FileStorageProperties;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = {"com.bazzar","com.services"})
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class YupexxApplication {
	public static void main(String[] args) {
		SpringApplication.run(YupexxApplication.class, args);
	}


}
