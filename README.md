## spring-boot代码演示

备注：
- spring-boot的版本为2.0.1.RELEASE
- 依赖redis服务；请先安装redis，接着修改application-${env}.properties中的redis配置
- 依赖mysql；运行之前，请先执行 init_script.sql，接着修改数据库配置项

## 1.Hello World
### 使用Spring Initializr初始化项目
- http://start.spring.io/
- IntelliJ IDEA  -> New -> Project -> Spring Initializr
- Spring Boot CLI

### 添加Controller
```java
package com.example.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
 
    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }
}
```

### 如何运行
- IntelliJ IDEA右键Run
- mvn spring-boot:run

### 2.starter依赖
	web
	mybatis
	redis
	freemarker
	...

### 3.配置管理
#### 优先级
1. 命令行参数
2. java:comp/env里的JNDI属性
3. JVM系统属性
4. 操作系统环境变量
5. 随机生成的带random.*前缀的属性（在设置其他属性时，可以引用它们，比如${random.long}）
6. 应用程序以外的application.properties或者appliaction.yml文件
7. 打包在应用程序内的application.properties或者appliaction.yml文件
8. 通过@PropertySource标注的属性源
9. 默认属性

#### 区分环境
##### 环境信息 application-{env}.properties
##### 公共配置 application.properties
##### 设置环境  
> application.properties中配置spring.profiles.active=${env}

1. dev
2. test
3. pre
4. prod

### 4.Actuator
1. 开启Actuator
```
#打开actuator
management.endpoints.enabled-by-default=true
management.endpoints.web.exposure.include=*
management.endpoints.web.base-path=/actuator
management.endpoint.beans.enabled=true
management.endpoint.env.enabled=true
management.endpoint.health.enabled=true
management.endpoint.httptrace.enabled=true
management.endpoint.info.enabled=true
management.endpoint.metrics.enabled=true
```

2. 请求
	* http://localhost:8080/actuator/env
	* http://localhost:8080/actuator/beans
	* http://localhost:8080/actuator/metrics
	* ...

### 5.完整的项目
- spring-security使用演示
	+ 禁用csrf
		* HttpSecurity.csrf().disable()
	+ 默认打开csrf
		* login页面需要传递`${_csrf.parameterName}=${_csrf.token}`，要不然页面403报错
		* logout默认不支持GET请求方式，会发生404报错
		* logout可以添加`logoutRequestMatcher(new AntPathRequestMatcher("/logout"))`而使GET请求方式得以支持
- mybatis注解
- redis限流处理
	+ 分钟限流
- freemarker

### 6.统一错误处理
* error页面
* Json

可以使用的方式
- 继承BasicErrorController；实现ErrorController接口
- RestControllerAdvice + ExceptionHandler
- ControllerAdvice + ExceptionHandler
- 实现ErrorViewResolver接口
- HandlerExceptionResolver

注意事项
- 区分请求头信息

### 7.相关资料
- https://spring.io/guides
- https://docs.spring.io/spring-boot/docs/current/reference/html/
- https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
- https://segmentfault.com/a/1190000008893479
- https://blog.csdn.net/t894690230/article/details/52404105
- https://stackoverflow.com/questions/23187109/spring-security-404-on-logout
- https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf-logout
- https://juejin.im/post/5a6c2333f265da3e3d496125
- http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
- https://www.cnblogs.com/waterystone/p/6214212.html
- http://codippa.com/ambiguous-exceptionhandler-method-mapped-error-in-spring/
