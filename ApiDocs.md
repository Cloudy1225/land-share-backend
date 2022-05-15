# ApiDocs



[TOC]

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



### Land

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

