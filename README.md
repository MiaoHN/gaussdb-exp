# Opengauss 数据库连接

## 前言

本代码是我用来完成数据库实验的辅助代码，实现的功能是在云服务器的 Opengauss 数据库中的 `Student`，`Course`，`Grade` 表中插入数据，仅供参考

## 连接设置

连接数据库时需要在 `src/` 目录下创建 `conf.json` 文件进行配置，示例配置文件如下：

```json
{
  "url": "192.168.0.1",
  "port": 26000,
  "dbname": "mydb",
  "username": "omm",
  "passwd": "xxx"
}
```

## JDBC 下载地址

[官网](https://opengauss.org/zh/download.html)

## 参考

- [数据库实验九--OpenGauss（使用JDBC连接数据库)](https://blog.csdn.net/qq_51594676/article/details/124730866)