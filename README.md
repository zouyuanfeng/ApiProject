### ApiProject
#### 其他条件：
* mysql数据库
* nginx映射上传的图片目录

#### mysql
```
  #安装mysql
  $sudo apt install mysql-server
```

#### sql
```sql
CREATE TABLE API (
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  method      VARCHAR(30)     NOT NULL,
  response    VARCHAR(1000)   NOT NULL,
  groupname   VARCHAR(30)     NOT NULL,
  description VARCHAR(1000)
);
```

#### tomcat
```
  #下载
  $sudo wget http://mirrors.hust.edu.cn/apache/tomcat/tomcat-8/v8.5.15/bin/apache-tomcat-8.5.15.tar.gz
  #解压
  $sudo tar zxf apache-tomcat-8.5.15.tar.gz
  #启动,默认是8080端口
  $./apache-tomcat-8.5.15/bin/startup.sh
```

#### nginx配置
安装
```
  $sudo apt-get install nginx
```

/etc/nginx/nginx.conf
```
修改user nobody --> user root
```
/etc/nginx/sites-available/default

```
server {
        listen 8081 default_server;
        listen [::]:8081 default_server;
        # SSL configuration
        #
        # listen 443 ssl default_server;
        # listen [::]:443 ssl default_server;
        

        #root /home/zou/tomcat/apache-tomcat-8.5.15/temp_img;

        # 首页配置
        index index.html index.htm index.nginx-debian.html;


        server_name _;

        access_log /home/zou/nginx.log;

        #上传的文件路径映射             
        location /pic {
            alias /home/zou/tomcat/apache-tomcat-8.5.15/temp_img;
        }
        
        location / {
            proxy_pass http://192.168.11.115:8080/;
        }
}
```




#### 效果图
* 主页

![主页](http://itzyf.qiniudn.com/20170704170534.jpg)
* 访问

![访问](http://itzyf.qiniudn.com/20170704170625.jpg)
* 添加

![添加](http://itzyf.qiniudn.com/20170704170608.jpg)


