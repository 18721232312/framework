# framework

#### 介绍
此项目是个人练习项目，会把工作中遇到的比较实用的功能，做成一个个小功能，持续更新
#### 软件架构
springboot


#### 使用说明
##### logback日志脱敏
###### V1版本
1. 配置logback.xml
```aidl
    <property scope="context" resource="logback-desensitization-rule.properties"/>
    <conversionRule conversionWord="msg" converterClass="com.bk.framework.extension.logback.v1.DesensitizationMessageConvert"/>
    <property name="CONSOLE_LOG_PATTERN" value="%date{yyyy-MM-dd HH:mm:ss} | %highlight(%-5level) | %boldYellow(%thread) | %boldGreen(%logger) | %msg%n"/>
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>
```
>这里特别注意一下属性文件的scope参数，默认是local，这个参数的选项可以参考一下logback的官网
2. 增加属性文件 logback-desensitization-rule.properties 至 resources目录，和logback.xml同级，如果不同层，需要个性属性的resource路径，保证可以引用到
> 规则KEY=正则&replacement
```aidl
#JSON字段中的mobile , telphone关键字对应的内容进行脱敏
RULE_REG_1=(\"mobile\"|\"telphone\")(:\")(\\w{3})(\\w{4})(\\w{4})*(\")&$1$2$3****$5$6
#JSON字段中的"证件号码"关键字对应的内容进行脱敏
RULE_REG_2=(\"idcard\")(:\")(\\w{2})(\\w{1,})(\\w{2})(\")&$1$2$3*********$5$6
#JSON字段中的"密码"对应的内容进行全部*显示
RULE_REG_3=(\"password\")(:\")(\\w+)(\")&$1$2*****$4
#JSON字段中的"用户名"对应的内容除第一位，其它位脱敏显示
RULE_REG_4=(\"customerName\"|\"userName\"|\"name\")(:\")([\u4E00-\u9FA5]{1})[\u4E00-\u9FA5]{1,}(\")&$1$2$3**$4
#log.info("password:{}",password);类似这样的日志，关键字后的8位中，后五位脱敏显示
RULE_REG_5=(password|mobile)([:|=|,| ]+)(\\w{3})(\\w{5})&$1$2$3*****
企业法人手机号码 | 线索联系人手机
```
3. 运行 ExtensionApplication测试效果
###### V2 相比V1简化了配置，同时增加了默认的脱敏正则，用户也可以自定义脱敏规则
1. 在应用启用类上增加@EnableLogbackDesensitization注解 ，启用志脱敏功能
2. 在需要脱敏的字段上增加注解@Desensitization 类型根据枚举自行设置 ，默认类型是NAME，在启动应用时会自动收集需要脱敏的字段
3. 配置logback.xml 
```aidl
    <conversionRule conversionWord="msg" converterClass="com.bk.framework.extension.logback.DesensitizationMessageConverter"/>
    <property name="CONSOLE_LOG_PATTERN" value="%date{yyyy-MM-dd HH:mm:ss} | %highlight(%-5level) | %boldYellow(%thread) | %boldGreen(%logger) | %msg%n"/>
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>
```
4. 增加logback-desensitization-rule.properties 至 resources目录，和logback.xml同级，如果不同层，需要个性属性的resource路径，保证可以引用到
```aidl
#手机号脱敏规则
MOBILE=(KEYWORD)(:\")(\\w{3})(\\w{4})(\\w{4})*(\")&$1$2$3****$5$6
#姓名脱敏规则
NAME=(KEYWORD)(:\")([\u4E00-\u9FA5]{1})[\u4E00-\u9FA5]{1,}(\")&$1$2$3**$4
#邮箱脱敏规则
EMAIL=(KEYWORD)(:")([a-z0-9A-Z|_]{2})([a-z0-9A-Z|_]{2,})@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+([a-z]{2,})(")&$1$2$3***@$5$6$7
```
##### 优点 
* 配置灵活
 > 脱敏规则可以自行扩展，项目中只做了JSON的脱敏，用户可以正行配置，比如，XML日志脱敏等，通过正则匹配比较灵活，大家可以发挥自己的正则功力，使它释放自己的魅力
* 可以自行扩展规则KEY
 > 只要保证注解@Desensitization 的type属性和规则KEY相等即可
* 少量配置
 > 提供默认脱敏规则 ，只需要关注脱敏字段即可

##### ApiStart
 > 主要解决日常工作中，提供restful api接口 增加客户端调用鉴权相关功能
1. 通过EnableWebApi启用相关功能
2. 通过继承 AbstractRequestProcessor 实现自己的请求处理器
3. 项目启动后，post请求访问 http://localhost/api/request，如果不增加任何参数，会返回参数不正确误，根据看一下接口校验，把相关参数增加上，测试功能
> 现在有些晚，明天白天，我更新一份可用的接口参数到 request.http中，这里先占个坑
#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 