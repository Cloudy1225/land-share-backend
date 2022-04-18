# land-share-backend

基于SpringBoot+Mybaits的微信小程序land-share服务端



## 写在前面

基于微信云托管官方demo和软工二大作业改造而来，官方模板的文件我没动过，依然可以按照下面的方法测试。

刚好看到计网老师发了Spring boot 和 微信小程序开发的入门，对理解项目代码帮助应该很大。当然可以不用理解，只要知道后端对前端暴露的 API 即可。



## 本地测试

- 在本地 mysql 中创建 database/schema：land-share-test
- 请找到 db.sql 文件，执行sql语句创建 TABLE：Counters 和 UserInformation
- 请找到 application.yml 文件，修改datasource为本地的数据库
- 浏览器访问 ```http://127.0.0.1:80/```可以体验官方demo的计数功能
- 利用ApiPost、Postman等工具测试我们自己的接口（详情见调用示例）

## API

### ```GET /my/loginOrRegister```

登录或注册，由于微信小程序和微信云托管的特殊性，我暂且把它们放在了一块

#### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

#### 响应

```json
{
    "code": "00000",
    "msg": "Success",
    "result": "用户已存在，直接登录"
}
```

```json
{
    "code": "00000",
    "msg": "Success",
    "result": "自动注册，直接登录"
}
```

#### 调用示例

微信小程序版：见前端代码

本地测试版：利用Postman或ApiPost

- Method: GET
- URL： http://127.0.0.1:80/my/loginOrRegister
- Header：{"X-WX-OPENID": "xxxxxx"}
- 如果看到上面的result就成功了
- 还可以去本地数据库中看看有没有添加数据



### ```GET /my/deleteUser```

用户注销

#### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

#### 响应

```json
{
    "code": "00000",
    "msg": "Success",
    "result": "用户已注销"
}
```

```json
{
    "code": "10000",
    "msg": "用户不存在",
    "result": null
}
```



### ```POST /my/realName```

用户实名 / 更改实名信息

#### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

body：注意**Content-Type为application/json**，而不是 multipart/form-data

```json
{
    "username": "刘云辉",
    "telenumber": "183****2634",
    "idnumber": "340***20021225****"
}
```

#### 响应

```json
{
	"code": "00000",
	"msg": "Success",
	"result": "用户已实名"
}
```



### ```GET /my/getUserInfo```

获取用户信息，不包括openid和收藏信息

#### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

#### 响应

```json
{
	"code": "00000",
	"msg": "Success",
	"result": {
		"uid": 4,
		"telenumber": "183****2634",
		"username": "刘云辉",
		"idnumber": "340***20021225****",
		"role": "2"
	}
}
```



## 部署踩坑

1. 微信云托管 MySql 表名与后端代码中大小些必须一致，本地测试都可以不一致
2. 微信云托管 MySql 中 varchar(20) 竟然容纳不了```ob7d15cPOmz6_y8WAViPMAslKS4g```，于是openid的属性我改成 varchar(100) 了




**这是官方模板的README**



[![GitHub license](https://img.shields.io/github/license/WeixinCloud/wxcloudrun-express)](https://github.com/WeixinCloud/wxcloudrun-express)
![GitHub package.json dependency version (prod)](https://img.shields.io/badge/maven-3.6.0-green)
![GitHub package.json dependency version (prod)](https://img.shields.io/badge/jdk-11-green)

微信云托管 Java Springboot 框架模版，实现简单的计数器读写接口，使用云托管 MySQL 读写、记录计数值。

![](https://qcloudimg.tencent-cloud.cn/raw/be22992d297d1b9a1a5365e606276781.png)


## 快速开始
前往 [微信云托管快速开始页面](https://developers.weixin.qq.com/miniprogram/dev/wxcloudrun/src/basic/guide.html)，选择相应语言的模板，根据引导完成部署。

## 本地调试
下载代码在本地调试，请参考[微信云托管本地调试指南](https://developers.weixin.qq.com/miniprogram/dev/wxcloudrun/src/guide/debug/)。

## 目录结构说明
~~~
.
├── Dockerfile                      Dockerfile 文件
├── LICENSE                         LICENSE 文件
├── README.md                       README 文件
├── container.config.json           模板部署「服务设置」初始化配置（二开请忽略）
├── mvnw                            mvnw 文件，处理mevan版本兼容问题
├── mvnw.cmd                        mvnw.cmd 文件，处理mevan版本兼容问题
├── pom.xml                         pom.xml文件
├── settings.xml                    maven 配置文件
├── springboot-cloudbaserun.iml     项目配置文件
└── src                             源码目录
    └── main                        源码主目录
        ├── java                    业务逻辑目录
        └── resources               资源文件目录
~~~


## 服务 API 文档

### `GET /api/count`

获取当前计数

#### 请求参数

无

#### 响应结果

- `code`：错误码
- `data`：当前计数值

##### 响应结果示例

```json
{
  "code": 0,
  "data": 42
}
```

#### 调用示例

```
curl https://<云托管服务域名>/api/count
```



### `POST /api/count`

更新计数，自增或者清零

#### 请求参数

- `action`：`string` 类型，枚举值
  - 等于 `"inc"` 时，表示计数加一
  - 等于 `"clear"` 时，表示计数重置（清零）

##### 请求参数示例

```
{
  "action": "inc"
}
```

#### 响应结果

- `code`：错误码
- `data`：当前计数值

##### 响应结果示例

```json
{
  "code": 0,
  "data": 42
}
```

#### 调用示例

```
curl -X POST -H 'content-type: application/json' -d '{"action": "inc"}' https://<云托管服务域名>/api/count
```

## 使用注意
如果不是通过微信云托管控制台部署模板代码，而是自行复制/下载模板代码后，手动新建一个服务并部署，需要在「服务设置」中补全以下环境变量，才可正常使用，否则会引发无法连接数据库，进而导致部署失败。
- MYSQL_ADDRESS
- MYSQL_PASSWORD
- MYSQL_USERNAME
以上三个变量的值请按实际情况填写。如果使用云托管内MySQL，可以在控制台MySQL页面获取相关信息。


## License

[MIT](./LICENSE)
