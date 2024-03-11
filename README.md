## 项目文件说明

>`examination` 文件夹为前端代码。
>
>` exam_system` 文件夹为后端代码，需配合`IDEA`，`Tomcat`使用。
>
>`examination_system.sql `文件为数据库文件，需要配合数据库服务程序以及`Tomcat`使用。

---

## 部署数据库相关

1. 启动数据库服务软件，运行数据库。
2. 启动`Navicat`连接到数据库，新建数据库，数据库名字设为`examination_system`，字符集设置为`UTF-8`
3. 右键`examination_system`数据库，点击`运行SQL文件`，
4. 选择`examination_system.sql` 数据库文件，点击`开始`

---

## 部署后端相关

1. 打开`IDEA`，打开`exam_system`项目
1. 点击`编辑配置`，选择本地最新的`Tomcat`
1. 在服务器中`URL`设置成`http://localhost:8080/`
1. 在部署中`应用程序上下文`设置为`空`
1. 点击`应用`，`确定`保存
1. 点击运行按钮，启动后端服务器，此时跳出的网页可直接关闭。（后端服务器端口号应该为8080）

 >如果部署数据库时，数据库名字不为`examination_system`，则需要进行以下操作
   >
   >1. 打开`src/resources`下的`mybatis-config.xml`文件
   >
   >2. 修改`name="url"`的那一行，将`value`修改成`jdbc:mysql://localhost:3306/${创建的数据库名字}?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai"`
   >
   >   > 如果数据库端口号不为3306则类似操作，修改即可

---

## 部署前端相关

1. 双击进入前端代码文件夹
2. 在空白区域`Shift + 右键`
3. 选择`在终端打开`
4. 输入`npm install`，安装前端依赖包
5. 输入`npm run serve`运行Vue项目（前端应用端口号应该为8081）

---

## 部署相关注意事项

应该先运行后端服务器，再运行前端Vue项目。

> 因为前端访问API的地址是固定写在main.js里的，如果运行的先后次序搞错前端项目会占用8080端口，而后端服务器会占用8081端口，这样会导致访问端口出错

--- 

## 账号
```
管理员账号：092220100   密码：123456
学生账号：  092220117   密码：123456
学生账号：  092220121   密码：123456
```

---

## 运行注意事项

教师端添加试卷时，课程号可以在科目信息查看# Student_Examination
