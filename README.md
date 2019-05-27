# Library-Management-System
### 概述
基于Spring + Spring MVC + MyBatis的图书馆管理系统，通过Maven管理包，项目来自于Github上的zhanghuanhao的LibrarySystem，进行了二次开发。本系统主要功能有：图书管理（支持图片上传），日志管理（包含惩罚日志和借阅日志），用户管理，权限管理和图书分类管理。使用前端插件来完成分页和数据导出Excel。
### 环境配置        
**开发环境: IntelliJ IDEA 2019.1，MySQL5.7.26，jdk1.8.0_181, tomcat8.5.33**

**运行配置**  
1.配置好MySQL数据库，设置用户名为admin，密码为123456，开启服务，执行sql文件以便导入数据；  
2.IDEA导入项目：通过pom.xml导入项目，配置好Tomcat  
3.使用浏览器访问http://localhost:8080 即可进入系统
### 注意事项
BookService.java中的addBook方法和editBook方法使用了绝对路径，使用时请注意修改为相对路径。  
联系方式:
qq:474285675
微信:liyao_5675
