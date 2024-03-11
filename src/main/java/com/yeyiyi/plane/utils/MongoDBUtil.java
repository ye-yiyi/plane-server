package com.yeyiyi.plane.utils;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MongoDBUtil {
    //连接数据库
    private static DB db;

    private static MongoDatabase mongoDatabase;

    public static void init(){
        System.out.println("===============MongoDBUtil初始化========================");
        List<ServerAddress> adds = new ArrayList<>();
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress("127.0.0.1", 3717);
        adds.add(serverAddress);

        List<MongoCredential> credentials = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("user", "admin", "123456".toCharArray());
        credentials.add(mongoCredential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(adds, credentials);

        //连接到数据库
        mongoDatabase = mongoClient.getDatabase("demo");
        System.out.println("===============MongoDBUtil已连接至ET数据库========================");

        MongoURI uri = new MongoURI("mongodb://user:123456@127.0.0.1:3717/admin");
        //连接服务器 ,线上肯定是带单独的ip 应该这样使用 Mongo("ip")
        Mongo mongo = new Mongo(uri);
        //连接数据库
        db = mongo.getDB("demo");

        System.out.println("===============MongoDBUtil已创建GridFS对象DB========================");
    }

    public static DB getDB(){
        return db;
    }
    //需要密码认证方式连接
    public static MongoDatabase getConnect(){
        //返回连接数据库对象
        return mongoDatabase;
    }


    public static void saveImgFile(File readFile,String contentType,DB db) throws Exception {

        //文件操作是在DB的基础上实现的，与表和文档没有关系
        GridFS gridFS = null;
        gridFS = new GridFS(db);

        GridFSInputFile mongofile = gridFS.createFile(readFile);
        //可以再添加属性
        mongofile.put("contentType", contentType);
        //保存
        mongofile.save();
    }

    public static void readImgFile() throws Exception {
        //链接服务器
        Mongo mongo = new Mongo();
        //连接数据库
        DB db = mongo.getDB("ImgGridFS");
        GridFS gridFs = null;
        gridFs = new GridFS(db);

        //查找条件
        DBObject query = new BasicDBObject();
        //查询的结果：
        List<GridFSDBFile> listfiles = gridFs.find(query);
        GridFSDBFile gridDBFile = listfiles.get(0);

        //获得其中的文件名
        //注意 ： 不是fs中的表的列名，而是根据调试gridDBFile中的属性而来
        String fileName = (String) gridDBFile.get("filename");

        System.out.println("从Mongodb获得文件名为：" + fileName);

        File writeFile = new File("F:\\360data\\重要数据\\桌面\\page\\" + fileName);
        if (!writeFile.exists()) {
            writeFile.createNewFile();
        }

        System.out.println("可以访问的地址:"+writeFile.getAbsoluteFile());
        System.out.println("总占用空间:"+writeFile.getTotalSpace());
        System.out.println("总占用空间:"+gridDBFile.getInputStream());


        //把数据写入磁盘中
        //查看相应的提示
        gridDBFile.writeTo("F:\\360data\\重要数据\\桌面\\page\\lovemm.jpg");
        //写入文件中
        gridDBFile.writeTo(writeFile);

    }

    public static void main(String[] args) {
//        MongoCollection<Document> collection = MongoDBUtil.getConnect().getCollection("test");
//
//        Document document = new Document("name","张三")
//                .append("sex", "男")
//                .append("age", 18);
//
//        collection.insertOne(document);

    }
}
