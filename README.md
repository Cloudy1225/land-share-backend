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
			"lid": 1,
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
			"pictureFileID": "fgshjfj.jpg|ehfhsjf.png|good.png",
			"videoFileID": "lyhlyh.mp4",
			"warrantsFileID": "warrantsFileID.png|hhh.png",
			"telenumber": "18355442634",
			"status": 0,
			"openid": "lyh",
			"submitTime": "2022-05-01T13:57:55"
		},
		{
			"lid": 5,
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
			"pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
			"videoFileID": "lyhlyh.mp4",
			"warrantsFileID": "warrantsFileID.png",
			"telenumber": "18355442634",
			"status": 1, 
			"openid": "lyh",
			"submitTime": "2022-05-01T13:55:27"
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



#### ```GET /landPost/deleteLandPost?lid={lid}```

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

#### ```GET /landPost/getLandPosts?submitTime={submitTime}```

返回 10 个土地信息，返回结果已经按时间排序，且status为1，表明已经审核通过

##### 请求

**请求 url**: ```landPost/deleteLandPost?submitTime=2022-05-06 17:00:00```

##### 响应

```json
{
  "code": "00000",
  "msg": "土地发布获取成功",
  "result": [
    {
      "lid": 16,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "adInfo": "安徽省淮南市凤台县",
      "description": "非常nice",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png|good.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png|hhh.png",
      "telenumber": "18355442634",
      "status": 1, 
      "openid": "lyh",
      "submitTime": "2022-05-05 21:28:11"
    },
    {
      "lid": 15,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "adInfo": "安徽省淮南市凤台县",
      "description": "非常nice1344",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:30"
    },
    {
      "lid": 14,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1344",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:29"
    },
    {
      "lid": 13,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1344",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:27"
    },
    {
      "lid": 12,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1344",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:26"
    },
    {
      "lid": 11,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1344",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:24"
    },
    {
      "lid": 10,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:19"
    },
    {
      "lid": 9,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:17"
    },
    {
      "lid": 8,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice1",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:16"
    },
    {
      "lid": 7,
      "landType": "耕地/水田",
      "transferType": "出租",
      "area": 14.5,
      "transferTime": 5.0,
      "price": 10000.0,
      "address": "安徽省淮南市凤台县",
      "longtitude": 118.238,
      "latitude": 30.223,
      "description": "非常nice",
      "pictureFileID": "fgshjfj.jpg|ehfhsjf.png",
      "videoFileID": "lyhlyh.mp4",
      "warrantsFileID": "warrantsFileID.png",
      "telenumber": "18355442634",
      "status": 1,
      "openid": "lyh",
      "submitTime": "2022-05-05 20:58:03"
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



#### ```GET /article/getArticles?time={}&type={}```

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
