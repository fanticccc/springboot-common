package com.example.config.swagger;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2022/9/23 10:01
 * @Package: com.example.config.swagger
 * @Description: -TODO
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket webApiConfig(){
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                //过滤掉admin路径下的所有页面
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                //过滤掉所有error或error.*页面
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .build();
//    }
//
//    @Bean
//    public Docket adminApiConfig(){
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("adminApi")
//                .apiInfo(adminApiInfo())
//                .select()
//                //只显示admin路径下的页面
//                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
//                .build();
//
//    }
//
//    private ApiInfo webApiInfo(){
//
//        return new ApiInfoBuilder()
//                .title("网站-课程中心API文档")
//                .description("本文档描述了课程中心微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("sun", "http://atguigu.com", "1416412681@qq.com"))
//                .build();
//    }
//
//    private ApiInfo adminApiInfo(){
//
//        return new ApiInfoBuilder()
//                .title("后台管理系统-课程中心API文档")
//                .description("本文档描述了后台管理系统课程中心微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("sun", "http://atguigu.com", "1789021022@qq.com"))
//                .build();
//    }
@Bean
public Docket docket() {
    // 创建一个 swagger 的 bean 实例
    return new Docket(DocumentationType.SWAGGER_2)
            // 配置基本信息
            .apiInfo(apiInfo());
}

    // 基本信息设置
    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "剑舞红颜笑", // 作者姓名
                "https://www.csdn.net/?spm=1001.2014.3001.4476", // 作者网址
                "307502005@qq.com"); // 作者邮箱
        return new ApiInfoBuilder()
                .title("测试项目-接口文档") // 标题
                .description("众里寻他千百度，慕然回首那人却在灯火阑珊处") // 描述
                .termsOfServiceUrl("https://www.baidu.com") // 跳转连接
                .version("1.0") // 版本
                .license("Swagger-的使用(详细教程)")
                .licenseUrl("https://blog.csdn.net/xhmico/article/details/125353535")
                .contact(contact)
                .build();
    }

}
