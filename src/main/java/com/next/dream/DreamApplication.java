package com.next.dream;

import com.next.dream.init.CustomerApplicationContextInitlater;
import com.next.dream.listener.CustomerApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DreamApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DreamApplication.class);
	}

	public static void main(String[] args) {
		//创建 SpringApplication 容器
		SpringApplication application = new SpringApplication(DreamApplication.class);
		/*//定制自己的初始化容器
		application.addInitializers(new CustomerApplicationContextInitlater());
		//添加自己的监听
		application.addListeners(new CustomerApplicationListener());
*/
		//启动项目
		application.run(args);
	}
}
