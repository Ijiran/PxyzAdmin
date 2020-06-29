# PxyzAdmin
后台管理系统（layui、springboot），提供基础功能集成，比如用户、菜单、角色、监控、日志、字典。。。

## 演示地址

[PxyzAdmin演示地址](http://116.196.124.0:9090/pxyzadmin/index) 账号：admin 密码：123456

请大家演示时不要把系统管理菜单的状态关掉，关掉后就看不到菜单了。
<br>尽量新增一些菜单尝试，这样效果会更好哦。

github代码地址：https://github.com/Ijiran/PxyzAdmin/
gitee代码地址：https://gitee.com/Ijiran/PxyzAdmin

## 框架技术

- Springboot 2.0 
- Spring Security 实现登录认证；角色&权限交于系统本身程序控制（菜单持有制）
- Mybatis：JPA与Mybatis抉择了一下，还是使用Mybatis，自己写SQL感觉比较舒服
- MySQL
- Layui

## 目前功能

- 用户管理
- 菜单管理
- 角色管理

## 最新完成
- 用户管理-密码重置
- 基本信息修改 & 修改密码

## 正在开发

- 角色管理-用户授权
- ip白名单(暂时搁置)
- 监控管理：读取服务器当前各项性能参数（oshi-core）
- 字典管理
- 登录日志
- 清理缓存按钮：这个计划使用redis存储用户菜单时使用。

## 前端模板

- 前端模板使用了

[https://github.com/zhongshaofa/layuimini]: 

的模板，十分感谢，提供了一个很好的思路去管理这么多的前端组件；因为我本身在其基础上更新了一些组件的版本和代码，如果大家有需要最新的前端模板，可以点击链接自行获取layuimini项目。

- 更新了treeTable版本。
- 基于前后端开发，新增了common.js，其中更新在项目使用到的基础方法。

## 部署

1. 修改数据库配置（application.yml-->[-test,-dev,-pro]）
2. 在数据库中运行pxyz-admin.sql文件，目录：PxyzAdmin/pxyzadmin.sql

## 演示地址

[PxyzAdmin演示地址](http://116.196.124.0:9090/pxyzadmin/index)

## 系统截图

![登录页](https://images.gitee.com/uploads/images/2020/0611/223354_664f8834_1139309.png "登录页")
![用户管理](https://images.gitee.com/uploads/images/2020/0611/223424_96ab67d1_1139309.png "用户管理")
![角色管理](https://images.gitee.com/uploads/images/2020/0611/223447_56d1bedb_1139309.png "角色管理")
![菜单管理](https://images.gitee.com/uploads/images/2020/0611/223500_3ecdccc1_1139309.png "菜单管理")
