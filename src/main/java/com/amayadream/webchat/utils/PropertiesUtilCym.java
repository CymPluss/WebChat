package com.amayadream.webchat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtilCym {

    Logger logger = LoggerFactory.getLogger(PropertiesUtilCym.class);

    public Properties getPrpperites(String propertiesName) throws IOException {
        Properties a = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        InputStream is = new FileInputStream(path+propertiesName);
        a.load(is);
        return a;
    }

    public Properties getProperties(File file) throws IOException {
        Properties a = new Properties();
        a.load(new FileInputStream(file));
        return a;
    }

    public void makePropertiesFile(Map<String,String> params,File file) throws IOException {
        Properties a = new Properties();
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
            params.forEach((key,value)-> {
                if(value == null||value.isEmpty()){
                }else {
                    a.put(key,value);
                }
            });
            Writer c =  new FileWriter(file);
            a.store(c,"createTime"+new CommonDate().getTime24());
        }else {
            updateProperties(file,params);
        }

    }


    public void updateProperties(String content,String name){
        RandomAccessFile pro = null;
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
            pro = new RandomAccessFile(path+name,"rw");
            pro.seek(pro.length());
            pro.write(content.getBytes());
            logger.info(String.format("配置文件：%s 已经被改动，改动内容。%s",name,content));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProperties(File file,Map<String,String> updatePro){
        Properties pro = new Properties();
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String filepath = path + name;
        InputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(file));
            pro.load(in);
            FileOutputStream file2 = new FileOutputStream(file);
            updatePro.forEach((k,v)->{
                if(v == null||v.isEmpty()){
                }else {
                    pro.put(k,v);
                    logger.info(String.format("配置文件：%s 已经被改动，改动内容。Key:%s,Value:%s",file.getName(),k,v));
                }
            });
            pro.store(file2,"update"+new CommonDate().getTime24());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
