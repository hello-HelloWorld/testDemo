package hdfs;

/*
 * @author: sunxiaoxiong
 */

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test01 {
    public static void main(String[] args) {
        try {
            //获取前一天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            Date time = calendar.getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String format = df.format(time);
            System.out.println(format);

            String aa3 = "buyertest|D20210615135617027333356||支付|退货|901.53|0.00|3075.35|2021-06-15 14:00:23|余额支付退货,原单号:D20210615133538549222237|2021-06-15||||";
            String[] split = aa3.split("\\|",-1);
            System.out.println(split);
            URI uri = new URI("hdfs://nbd-name-01:8020");
            //获取配置文件信息
            Configuration conf = new Configuration();
            //注意：因为集群设置了地址映射关系，本地访问集群的时候不能通过内网访问，需要配置下行代码，设置通过域名访问集群
            conf.set("dfs.client.use.datanode.hostname", "true");
            // conf.set("dfs.defaultFS","hdfs://master2:9000");
            //获取文件系统
            FileSystem fs = FileSystem.get(uri, conf);
            // FileSystem fs = FileSystem.get(uri, conf, user);  user为linux系统的用户名
            // FileSystem fs = FileSystem.get(conf);

//             readFile(fs);
//            readFile2(fs);
            // mkdir(fs);
            // getFI(fs);
            // upload(fs);
             dele(fs);
//            chmodFile(fs);
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //修改文件夹权限
    public static void chmodFile(FileSystem fs) throws IOException {
        Path path = new Path("/apps/hive/warehouse/table_tmp");
        fs.setOwner(path,"rtmap","hive");
        fs.setPermission(path,new FsPermission("777"));
        System.out.println("修改成功");
    }

    //读取集群文件夹的列表
    public static void readFile(FileSystem fs) throws IOException {
        Path path = new Path("/apps/hive/warehouse/wanda_bi_ads.db/agencylevelinfo_test");
        FileStatus[] files = fs.listStatus(path);
        for (FileStatus file : files) {
            System.out.println(file.isDirectory());
            System.out.println(file.getPath().getName());
            System.out.println(file.getModificationTime());
            System.out.println(file.getLen());
        }
    }

    //读取文件
    public static void readFile2(FileSystem fs) throws IOException {
        Path path = new Path("/xiong_test2/a.txt");
        FSDataInputStream in = fs.open(path);
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = read.readLine()) != null) {
            String[] s = line.split(" ");
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i]);
            }
        }
    }

    //创建文件夹
    public static void mkdir(FileSystem fs) throws IOException {
        Path path = new Path("/xiong_test2/xiong");
        fs.mkdirs(path);
    }

    //删除文件夹
    public static void dele(FileSystem fs) throws IOException {
        Path path = new Path("/apps/hive/warehouse/test.db/table_tmp");
        if (fs.exists(path)) {
            fs.delete(path, true);
            System.out.println("删除成功");
        }
    }

    //下载集群文件到本地
    public static void getFI(FileSystem fs) throws IOException {
        Path path = new Path("/xiong_test2/a.txt");
        // fs.copyToLocalFile("srcPath","dstPath");
        FSDataInputStream in = fs.open(path);
        FileUtils.copyInputStreamToFile(in, new File("D://1111.txt"));
        System.out.println("下载成功");
    }

    //上传文件到集群
    public static void upload(FileSystem fs) throws IOException {
        Path srcPath = new Path("D://1111.txt");
        Path path = new Path("/xiong_test2/");
        // FSDataOutputStream out = fs.create(path);
        // FileUtils.copyFile(new File("D://1111.txt"), out);
        fs.copyFromLocalFile(false, srcPath, path);
        System.out.println("上传成功");
    }
}
