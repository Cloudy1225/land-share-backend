<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [ApiDocs](#apidocs)
  - [写在前面](#%E5%86%99%E5%9C%A8%E5%89%8D%E9%9D%A2)
  - [具体说明](#%E5%85%B7%E4%BD%93%E8%AF%B4%E6%98%8E)
    - [User](#user)
      - [```GET /my/loginOrRegister```](#get-myloginorregister)
        - [请求](#%E8%AF%B7%E6%B1%82)
        - [响应](#%E5%93%8D%E5%BA%94)
        - [调用示例](#%E8%B0%83%E7%94%A8%E7%A4%BA%E4%BE%8B)
      - [```GET /my/deleteUser```](#get-mydeleteuser)
        - [请求](#%E8%AF%B7%E6%B1%82-1)
        - [响应](#%E5%93%8D%E5%BA%94-1)
      - [```POST /my/realName```](#post-myrealname)
        - [请求](#%E8%AF%B7%E6%B1%82-2)
        - [响应](#%E5%93%8D%E5%BA%94-2)
      - [```GET /my/getUserInfo```](#get-mygetuserinfo)
        - [请求](#%E8%AF%B7%E6%B1%82-3)
        - [响应](#%E5%93%8D%E5%BA%94-3)
    - [LandPost](#landpost)
      - [```POST /landPost/createLandPost```](#post-landpostcreatelandpost)
        - [请求](#%E8%AF%B7%E6%B1%82-4)
        - [响应](#%E5%93%8D%E5%BA%94-4)
      - [```GET /landPost/getMyLandPosts```](#get-landpostgetmylandposts)
        - [请求](#%E8%AF%B7%E6%B1%82-5)
        - [响应](#%E5%93%8D%E5%BA%94-5)
      - [```POST /landPost/updateLandPost```](#post-landpostupdatelandpost)
        - [请求](#%E8%AF%B7%E6%B1%82-6)
        - [响应](#%E5%93%8D%E5%BA%94-6)
      - [```GET /landPost/deleteLandPost?```](#get-landpostdeletelandpost)
        - [请求](#%E8%AF%B7%E6%B1%82-7)
        - [响应](#%E5%93%8D%E5%BA%94-7)
      - [```POST /landPost/getLandPosts```](#post-landpostgetlandposts)
        - [请求](#%E8%AF%B7%E6%B1%82-8)
        - [响应](#%E5%93%8D%E5%BA%94-8)
      - [```Post /landPost/searchLandPosts```](#post-landpostsearchlandposts)
        - [请求](#%E8%AF%B7%E6%B1%82-9)
        - [响应](#%E5%93%8D%E5%BA%94-9)
      - [```Post /landPost/recommendLandPosts```](#post-landpostrecommendlandposts)
        - [请求](#%E8%AF%B7%E6%B1%82-10)
        - [响应](#%E5%93%8D%E5%BA%94-10)
    - [Article](#article)
      - [```POST /article/insertArticle```](#post-articleinsertarticle)
        - [请求](#%E8%AF%B7%E6%B1%82-11)
        - [响应](#%E5%93%8D%E5%BA%94-11)
      - [```POST /article/updateArticle```](#post-articleupdatearticle)
        - [请求](#%E8%AF%B7%E6%B1%82-12)
        - [响应](#%E5%93%8D%E5%BA%94-12)
      - [```GET /article/getArticles?```](#get-articlegetarticles)
        - [请求](#%E8%AF%B7%E6%B1%82-13)
        - [响应](#%E5%93%8D%E5%BA%94-13)
    - [LandRequire](#landrequire)
      - [```POST /landRequire/createLandRequire```](#post-landrequirecreatelandrequire)
        - [请求](#%E8%AF%B7%E6%B1%82-14)
        - [响应](#%E5%93%8D%E5%BA%94-14)
      - [```GET /landRequire/deleteLandRequire?```](#get-landrequiredeletelandrequire)
        - [请求](#%E8%AF%B7%E6%B1%82-15)
        - [响应](#%E5%93%8D%E5%BA%94-15)
      - [```Post /landRequires/searchLandRequires```](#post-landrequiressearchlandrequires)
        - [请求](#%E8%AF%B7%E6%B1%82-16)
        - [响应](#%E5%93%8D%E5%BA%94-16)
      - [```POST /landRequire/updateLandRequire```](#post-landrequireupdatelandrequire)
        - [请求](#%E8%AF%B7%E6%B1%82-17)
        - [响应](#%E5%93%8D%E5%BA%94-17)
      - [```POST /landRequire/getLandRequires```](#post-landrequiregetlandrequires)
        - [（1）传json筛选条件](#1%E4%BC%A0json%E7%AD%9B%E9%80%89%E6%9D%A1%E4%BB%B6)
        - [请求](#%E8%AF%B7%E6%B1%82-18)
        - [响应](#%E5%93%8D%E5%BA%94-18)
      - [```GET /landRequire/getLandRequires?```](#get-landrequiregetlandrequires)
        - [（2）按时间查找](#2%E6%8C%89%E6%97%B6%E9%97%B4%E6%9F%A5%E6%89%BE)
        - [请求](#%E8%AF%B7%E6%B1%82-19)
        - [响应](#%E5%93%8D%E5%BA%94-19)
    - [Collection](#collection)
      - [```GET /collection/addMyCollection?```](#get-collectionaddmycollection)
        - [请求](#%E8%AF%B7%E6%B1%82-20)
        - [响应](#%E5%93%8D%E5%BA%94-20)
      - [```GET /collection/isCollected?```](#get-collectioniscollected)
        - [请求](#%E8%AF%B7%E6%B1%82-21)
        - [响应](#%E5%93%8D%E5%BA%94-21)
      - [```GET /collection/getMyCollection```](#get-collectiongetmycollection)
        - [请求](#%E8%AF%B7%E6%B1%82-22)
        - [响应](#%E5%93%8D%E5%BA%94-22)
      - [```GET /collection/deleteMyCollection?```](#get-collectiondeletemycollection)
        - [请求](#%E8%AF%B7%E6%B1%82-23)
        - [响应](#%E5%93%8D%E5%BA%94-23)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# ApiDocs



## 写在前面

这里是微信小程序闲置土地信息共享工具服务端API接口文档。

由于微信云托管自带用户鉴权：微信小程序中``wx.cloud.callContainer``中的header自动携带了用户**openid**

所以一切需要识别用户的接口在自己用PostMan或ApiPost等工具测试时，均需在header中携带"**X-WX-OPENID**"字段；部分其他接口也需要该字段，是为了日志中显示是哪位用户在操作

**注意：**

- "**X-WX-OPENID**"字段的携带
- 请求体RequestBody的格式为json：**application/json**，而不是 multipart/form-data
- GET接口中，若请求路径带有“**?**”，说明需要在url中携带参数



## 具体说明

### User

#### ```GET /my/loginOrRegister```

登录或注册，由于微信小程序和微信云托管的特殊性，我暂且把它们放在了一块

##### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

##### 响应

会返回用户角色1 或 2，已经角色信息

```json
{
	"code": "00000",
	"msg": "未实名用户，直接登录",
	"result": {
		"role": "1",
        "telenumber": null
	}
}
```

```json
{
	"code": "00000",
	"msg": "已实名用户，直接登录",
	"result": {
		"role": "2",
        "telenumber": "18355442634"
	}
}
```

```json
{
	"code": "00000",
	"msg": "自动注册，直接登录",
	"result": {
		"role": "1",
        "telenumber": null
	}
}
```



##### 调用示例

微信小程序版：见前端代码

本地测试版：利用Postman或ApiPost

- Method: GET
- URL： http://127.0.0.1:80/my/loginOrRegister
- Header：{"X-WX-OPENID": "xxxxxx"}
- 如果看到上面的result就成功了
- 还可以去本地数据库中看看有没有添加数据



#### ```GET /my/deleteUser```

用户注销

##### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

##### 响应

```json
{
    "code": "00000",
    "msg": "用户已注销",
    "result": null
}
```

```json
{
    "code": "10001",
    "msg": "用户不存在",
    "result": null
}
```



#### ```POST /my/realName```

用户实名 / 更改实名信息

##### 请求

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

##### 响应

```json
{
	"code": "00000",
	"msg": "用户已实名",
	"result": null
}
```



#### ```GET /my/getUserInfo```

获取用户信息，不包括openid和收藏信息

##### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

##### 响应

```json
{
	"code": "00000",
	"msg": "实名信息获取成功",
	"result": {
		"uid": 4,
		"telenumber": "183****2634",
		"username": "刘云辉",
		"idnumber": "340***20021225****",
		"role": "2"
	}
}
```



### LandPost

#### ```POST /landPost/createLandPost```

用户发布土地

##### 请求

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
    "landType": "耕地/水田",
    "transferType": "出租",
    "area": 14.5,
    "transferTime": 5,
    "price": 10000,
    "address": "安徽省淮南市凤台县",
    "longtitude": 118.238,
    "latitude": 30.223,
    "adInfo": "安徽省淮南市凤台县",
    "description": "非常nice",
    "pictureFileID":"fgshjfj.jpg|ehfhsjf.png",
    "videoFileID": "lyhlyh.mp4",
    "warrantsFileID": "warrantsFileID.png",
    "telenumber": "18355442634"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "土地信息已上传，待审核",
	"result": null
}
```

```json
{
	"code": "10002",
	"msg": "土地信息不完整",
	"result": null
}
```



#### ```GET /landPost/getMyLandPosts```

获取我的所有发布土地，响应结果按时间排序，status为-1指未通过审核，0指待审核，1指审核通过

##### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

##### 响应

```json
{
	"code": "00000",
	"msg": "我的土地发布获取成功",
	"result": []
}
```

```json
{
	"code": "00000",
	"msg": "我的土地发布获取成功",
	"result": [
		{
			"lid": 42,
			"landType": "耕地/荒地",
			"transferType": "合作",
			"area": 123,
			"transferTime": 2,
			"price": 23,
			"address": "江苏省南京市玄武区北京东路41号",
			"longtitude": 118.79647,
			"latitude": 32.05838,
			"adInfo": "江苏省/南京市/玄武区",
			"district": "江苏省南京市玄武区",
			"title": "江苏省南京市玄武区约123亩耕地荒地合作",
			"description": "12",
			"pictureFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/0Utzl46PXoJS48904236c7ef3ef249786aa2b23bdb2a.png|cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/bOr4Rz7Bkk4ae7f34db74e7f75b44f4e7dbab48ff8dd.png",
			"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/0Utzl46PXoJS48904236c7ef3ef249786aa2b23bdb2a.png",
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/c5wHmXSg8Wy8d35619257310f3b7343b93cc972fc798.png",
			"telenumber": "18355442634",
			"status": 0,
			"openid": "ob7d15cPOmz6_y8WAViPMAslKS4g",
			"submitTime": "2022-05-11 23:22:51"
		},
		{
			"lid": 41,
			"landType": "耕地/盐碱地",
			"transferType": "互换",
			"area": 123,
			"transferTime": 23,
			"price": 123,
			"address": "江苏省南京市玄武区北京东路41号",
			"longtitude": 118.79647,
			"latitude": 32.05838,
			"adInfo": "江苏省/南京市/玄武区",
			"district": "江苏省南京市玄武区",
			"title": "江苏省南京市玄武区约123亩耕地盐碱地互换",
			"description": "123",
			"pictureFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/spp8yiK6QWvQ48904236c7ef3ef249786aa2b23bdb2a.png|cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/TLxeoIh30W1We7f34db74e7f75b44f4e7dbab48ff8dd.png",
			"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/spp8yiK6QWvQ48904236c7ef3ef249786aa2b23bdb2a.png",
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/jS0DwVFjfOaG48904236c7ef3ef249786aa2b23bdb2a.png",
			"telenumber": "18355442634",
			"status": 0,
			"openid": "ob7d15cPOmz6_y8WAViPMAslKS4g",
			"submitTime": "2022-05-11 23:18:39"
		}
	]
}
```



#### ```POST /landPost/updateLandPost```

更新特定的土地信息

##### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

body：注意**Content-Type为application/json**，而不是 multipart/form-data

一定要提供 **lid**

```json
{
    "lid": 1,
    "landType": "耕地/水田",
    "transferType": "出租",
    "area": 14.5,
    "transferTime": 5,
    "price": 10000,
    "address": "安徽省淮南市凤台县",
    "longtitude": "118.238",
    "latitude": 30.223,
    "adInfo": "安徽省淮南市凤台县",
    "description": "非常nice",
    "pictureFileID":"fgshjfj.jpg|ehfhsjf.png",
    "videoFileID": "lyhlyh.mp4",
    "warrantsFileID": "warrantsFileID.png",
    "telenumber": "18355442634"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "土地信息已更新，待审核",
	"result": null
}
```

```json
{
    "code": "00000",
    "msg": "用户已注销",
    "result": null
}
```



#### ```GET /landPost/deleteLandPost?```

删除特定的土地

##### 请求

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

**请求 url**: ```landPost/deleteLandPost?lid={lid}```

eg: landPost/deleteLandPost?lid=2

##### 响应

```json
{
	"code": "00000",
	"msg": "删除成功",
	"result": null
}
```

```json
{
	"code": "00000",
	"msg": "土地不存在",
	"result": null
}
```

#### ```POST /landPost/getLandPosts```

返回 10 个土地信息，返回结果已经按时间排序，且status为1，表明已经审核通过

##### 请求

body：注意**Content-Type为application/json**，而不是 multipart/form-data

```json
{
    "landType": "耕地/水田",
    "transferType": "出租",
    "adInfo": "南京市",
  	"submitTime": "2022-05-09 23:34:06"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "土地发布获取成功",
	"result": [
		{
			"lid": 21,
			"landType": "耕地/水田",
			"transferType": "出租",
			"area": 70,
			"transferTime": 2,
			"price": 4000,
			"address": "江苏省南京市江宁区陶杨路",
			"longtitude": 118.771003,
			"latitude": 31.768643,
			"adInfo": "江苏省/南京市/江宁区",
			"district": "江苏省南京市江宁区",
			"title": "江苏省南京市江宁区约70亩耕地水田出租",
			"description": "",
			"pictureFileID": "",
			"defaultPicture": null,
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/4rWUe9StMTvQ1e025ee592b89e038f3598d9adab1eb6.webp",
			"telenumber": "17356482705",
			"status": 0,
			"openid": "ob7d15SV_90UROPT60-h-3C5yJOY",
			"submitTime": "2022-05-09 00:19:57"
		}
	]
}
```



#### ```Post /landPost/searchLandPosts```

根据用户输入搜索土地

##### 请求

body：注意**Content-Type为application/json**，而不是 multipart/form-data

```json
{
    "input": "南京耕地10亩"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "搜索土地成功",
	"result": [
		{
			"lid": 30,
			"landType": "耕地/盐碱地",
			"transferType": "转让",
			"area": 12,
			"transferTime": 23,
			"price": 34354,
			"address": "江苏省南京市玄武区北京东路41号",
			"longtitude": 118.796539,
			"latitude": 32.058441,
			"adInfo": "江苏省/南京市/玄武区",
			"district": "江苏省南京市玄武区",
			"title": "江苏省南京市玄武区约12亩耕地盐碱地转让",
			"description": "23",
			"pictureFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/GdDhcoDLTUKRcc6620abd86094c1357a5972c046beb7.png",
			"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/GdDhcoDLTUKRcc6620abd86094c1357a5972c046beb7.png",
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/7Gq3I6RVQ6Od783a264169dcccea44b89d0efb02fbfb.png",
			"telenumber": "18355442634",
			"status": 0,
			"openid": "ob7d15cPOmz6_y8WAViPMAslKS4g",
			"submitTime": "2022-05-09 00:24:21"
		}
	]
}
```



#### ```Post /landPost/recommendLandPosts```

根据当前土地信息推荐其它土地，最多返回10条

##### 请求

body：注意**Content-Type为application/json**，而不是 multipart/form-data；一定要**携带当前土地lid**，用于排除自身

```json
{
	"lid": 21,
	"adInfo": "江苏省/南京市/玄武区",
	"landType": "耕地",
	"transferType": "合作"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "推荐土地成功",
	"result": [
		{
			"lid": 42,
			"landType": "耕地/荒地",
			"transferType": "合作",
			"area": 123,
			"transferTime": 2,
			"price": 23,
			"address": "江苏省南京市玄武区北京东路41号",
			"longtitude": 118.79647,
			"latitude": 32.05838,
			"adInfo": "江苏省/南京市/玄武区",
			"district": "江苏省南京市玄武区",
			"title": "江苏省南京市玄武区约123亩耕地荒地合作",
			"description": "12",
			"pictureFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/0Utzl46PXoJS48904236c7ef3ef249786aa2b23bdb2a.png|cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/bOr4Rz7Bkk4ae7f34db74e7f75b44f4e7dbab48ff8dd.png",
			"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/0Utzl46PXoJS48904236c7ef3ef249786aa2b23bdb2a.png",
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/c5wHmXSg8Wy8d35619257310f3b7343b93cc972fc798.png",
			"telenumber": "18355442634",
			"status": 0,
			"openid": "ob7d15cPOmz6_y8WAViPMAslKS4g",
			"submitTime": "2022-05-11 23:22:51"
		},
		{
			"lid": 24,
			"landType": "耕地/水浇地",
			"transferType": "合作",
			"area": 13,
			"transferTime": 3,
			"price": 1800,
			"address": "江苏省南京市江宁区圣湖东路",
			"longtitude": 119.049129,
			"latitude": 32.077531,
			"adInfo": "江苏省/南京市/江宁区",
			"district": "江苏省南京市江宁区",
			"title": "江苏省南京市江宁区约13亩耕地水浇地合作",
			"description": "",
			"pictureFileID": "",
			"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/0Utzl46PXoJS48904236c7ef3ef249786aa2b23bdb2a.png",
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/LLVMdQEhfmXLc3bb2f863bb9415564d42ca39bc0e53a.webp",
			"telenumber": "17356482705",
			"status": 0,
			"openid": "ob7d15SV_90UROPT60-h-3C5yJOY",
			"submitTime": "2022-05-09 00:21:41"
		}
	]
}
```



### Article

#### ```POST /article/insertArticle```

插入一篇文章

##### 请求

header无特殊要求

body：注意**Content-Type为application/json**，而不是 multipart/form-data

type取值：policy、news、question、reference

time格式一定要正确

```json
{
    "title": "流转耕地可做什么用途？种树合法吗？",
    "type": "question",
    "url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/questions/question1.md?sign=d359b80b3319d18a2b474cfb4e209f19&t=1651982691",
    "fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/questions/question1.md",
    "time": "2022-05-06"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "文章上传成功",
	"result": null
}
```



#### ```POST /article/updateArticle```

更新文章信息

##### 请求 

同上

##### 响应

```json
{
	"code": "00000",
	"msg": "文章更新成功",
	"result": null
}
```



#### ```GET /article/getArticles?```

获取文章，最多返回10个，按time降序排列

##### 请求

header无特殊要求

对于类型为全部的文章：```/article/getArticles?time=2022-05-08```即可

对应特殊类型的文章：```/article/getArticles?time=2022-05-08&type=news```

##### 响应

```json
{
    "code": "10004",
    "msg": "文章类型错误",
    "result": null
}
```



```json
{
	"code": "00000",
	"msg": "文章获取成功",
	"result": [
		{
			"aid": 1,
			"title": "盘活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-04-27"
		},
		{
			"aid": 5,
			"title": "l5盘活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-04-11"
		},
		{
			"aid": 6,
			"title": "5盘活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-04-11"
		},
		{
			"aid": 4,
			"title": "45盘活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-04-10"
		},
		{
			"aid": 3,
			"title": "4盘活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-04-07"
		},
		{
			"aid": 2,
			"title": "2盘活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-04-06"
		},
		{
			"aid": 11,
			"title": "村村民集体创收万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-03-23"
		},
		{
			"aid": 10,
			"title": "村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-03-21"
		},
		{
			"aid": 8,
			"title": "5活4置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-03-11"
		},
		{
			"aid": 7,
			"title": "5活闲置土地，京郊这个村村民集体创收逾千万元",
			"type": "news",
			"url": "https://7072-prod-9grx0olg9c8cf232-1311076540.tcb.qcloud.la/articles/news/news1.md?sign=cea21e1e97738bfbb531f2233ba90814&t=1651979106",
			"fileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/articles/news/news1.md",
			"time": "2022-02-11"
		}
	]
}
```



### LandRequire 

​	 important：**openid位于head中，且用“X-WX-OPENID”作为Key**

​	注：LandRequire service 与LandPOST service 请求方式一致，内容略有不同 



#### 	```POST /landRequire/createLandRequire```  

用户提交土地需求

##### 请求

```json
{  "lrid":42,
  "landType":"耕地/荒地",
  "transferType":"合作",
  "area":123,
  "transferTime":2,
  "price":23,
  "longtitude":114.514,
  "latitude":11.4514,
  "address":"江苏省南京市玄武区北京东路41号",
  "adInfo":"江苏省南京市玄武区","description":"12",
  "telenumber":"18355442634",
   "submitTime":"2022-05-11 23:22:51"
  }
```

##### 	响应

**success**

```json
{
    "code": "00000",
    "msg": "土地需求信息已上传",
    "result": null
}
```

**error**

```json
{
    "code": "10002",
    "msg": "土地信息不完整",
    "result": null
}
```



#### ```GET /landRequire/deleteLandRequire?```

​	GET方式：

​	`http:// your_own_url/landRequire/deleteLandRequire?lrid={todel}`

​	删除lrid对应的土地需求

##### 请求

##### 响应

**success**

```json
{
    "code": "00000",
    "msg": "删除成功",
    "result": null
}
```

**error**

```json
{
    "code": "00000",
    "msg": "土地需求不存在",
    "result": null
}
```



#### ```Post /landRequires/searchLandRequires```

根据用户输入搜索土地需求

##### 请求

body：注意**Content-Type为application/json**，而不是 multipart/form-data

```json
{
    "input": "南京耕地10亩"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "搜索土地需求成功",
	"result": []
}
```



#### ```POST /landRequire/updateLandRequire```

用户根据lrid更改已存储的土地需求信息

##### 请求

```json
{  "lrid":42,
  "landType":"耕地/荒地",
  "transferType":"合作",
  "area":123,
  "transferTime":2,
  "price":23,
  "longtitude":114.514,
  "latitude":11.4514,
  "address":"江苏省南京市玄武区北京东路41号",
  "adInfo":"江苏省南京市玄武区","description":"12",
  "telenumber":"18355442634",
   "submitTime":"2022-05-11 23:22:51"
  }
```

##### 响应

**success**

```json
{
    "code": "00000",
    "msg": "土地需求信息已更新",
    "result": null
}
```

**error**

```json
{  
    "code": "10002",
    "msg": "土地信息不完整",
    "result": null
  }
```



#### ```POST /landRequire/getLandRequires```

返回 10 个土地信息，返回结果已经按时间排序

##### （1）传json筛选条件

##### 请求

body：注意**Content-Type为application/json**，而不是 multipart/form-data

```json
{
    "landType": "耕地/水田",
    "transferType": "出租",
    "adInfo": "南京市",
  	"submitTime": "2022-05-09 23:34:06"
}
```

##### 响应

```json
{
	"code": "00000",
	"msg": "土地发布获取成功",
	"result": [
		{
			"lid": 21,
			"landType": "耕地/水田",
			"transferType": "出租",
			"area": 70,
			"transferTime": 2,
			"price": 4000,
			"address": "江苏省南京市江宁区陶杨路",
			"longtitude": 118.771003,
			"latitude": 31.768643,
			"adInfo": "江苏省/南京市/江宁区",
			"district": "江苏省南京市江宁区",
			"title": "江苏省南京市江宁区约70亩耕地水田出租",
			"description": "",
			"pictureFileID": "",
			"defaultPicture": null,
			"videoFileID": null,
			"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/4rWUe9StMTvQ1e025ee592b89e038f3598d9adab1eb6.webp",
			"telenumber": "17356482705",
			"status": 0,
			"openid": "ob7d15SV_90UROPT60-h-3C5yJOY",
			"submitTime": "2022-05-09 00:19:57"
		}
	]
}
```



#### ```GET /landRequire/getLandRequires?```

##### （2）按时间查找

##### 请求

请求url：`/article/getArticles?time={yyyy-mm-dd}`

##### 响应

与（1)相似





### Collection



#### ```GET /collection/addMyCollection?```

添加指定土地进我的收藏

##### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

**请求 url**: ```collection/addMyCollection?lid={lid}```

##### 响应

```json
{
	"code": "00000",
	"msg": "成功收藏土地",
	"result": null
}
```



```json
{
	"code": "10005",
	"msg": "土地收藏失败",
	"result": null
}
```



#### ```GET /collection/isCollected?```

判断是否收藏指定土地

##### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

**请求 url**: ```collection/isCollected?lid={lid}```

##### 响应

```json
{
	"code": "00000",
	"msg": "土地已收藏",
	"result": true
}
```

```json
{
	"code": "00000",
	"msg": "土地未收藏",
	"result": false
}
```



#### ```GET /collection/getMyCollection```

获取我的收藏

##### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

##### 响应

```json
{
	"code": "00000",
	"msg": "我的收藏获取成功",
	"result": {
		"openid": "yxb",
		"myCollection": null
	}
}
```



```json
{
	"code": "00000",
	"msg": "我的收藏获取成功",
	"result": {
		"openid": "lyh",
		"myCollection": [
			{
				"lid": 3,
				"landType": "耕地/盐碱地",
				"transferType": "入股",
				"area": 23,
				"transferTime": 3,
				"price": 21000,
				"address": "江苏省南京市玄武区北京东路39号",
				"longtitude": 118.795212,
				"latitude": 32.060966,
				"adInfo": "江苏省/南京市/玄武区",
				"district": "江苏省南京市玄武区",
				"title": "江苏省南京市玄武区约23亩耕地盐碱地入股",
				"description": "非常Niceeeeeee",
				"pictureFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/CtWHH7EEtPyw48904236c7ef3ef249786aa2b23bdb2a.png",
				"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/CtWHH7EEtPyw48904236c7ef3ef249786aa2b23bdb2a.png",
				"videoFileID": null,
				"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/8Z294VpdnA4i48904236c7ef3ef249786aa2b23bdb2a.png",
				"telenumber": "18355442634",
				"status": 0,
				"openid": "ob7d15cPOmz6_y8WAViPMAslKS4g",
				"submitTime": "2022-05-08 23:36:56"
			},
			{
				"lid": 1,
				"landType": "耕地/荒地",
				"transferType": "互换",
				"area": 100,
				"transferTime": 3,
				"price": 30000,
				"address": "江苏省南京市玄武区四牌楼2号",
				"longtitude": 118.794472,
				"latitude": 32.054859,
				"adInfo": "江苏省/南京市/玄武区",
				"district": "江苏省南京市玄武区",
				"title": "江苏省南京市玄武区约100亩耕地荒地互换",
				"description": "非常nice",
				"pictureFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/RJ5quUND4vXld35619257310f3b7343b93cc972fc798.png|cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/v6rkYR8xzcEt48904236c7ef3ef249786aa2b23bdb2a.png",
				"defaultPicture": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/land/RJ5quUND4vXld35619257310f3b7343b93cc972fc798.png",
				"videoFileID": null,
				"warrantsFileID": "cloud://prod-9grx0olg9c8cf232.7072-prod-9grx0olg9c8cf232-1311076540/landPost/warrant/B8fR30i2QO5r4cb8cd0769d4c6e767d80da03e687dc5.png",
				"telenumber": "18355442634",
				"status": 0,
				"openid": "ob7d15cPOmz6_y8WAViPMAslKS4g",
				"submitTime": "2022-05-08 23:34:06"
			}
		]
	}
}
```



#### ```GET /collection/deleteMyCollection?```

删除指定收藏

##### 请求

header

自己用Postman、ApiPost等工具测试的话要在header里携带这项信息

```json
{
    "X-WX-OPENID": "xxxxxx"
}
```

微信小程序中``wx.cloud.callContainer``中的header自动携带了上述信息

**请求 url**: ```collection/deleteMyCollection?lid={lid}```

##### 响应

```json
{
	"code": "00000",
	"msg": "删除该收藏成功",
	"result": null
}
```

