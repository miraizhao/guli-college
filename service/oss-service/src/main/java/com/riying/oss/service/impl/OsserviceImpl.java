package com.riying.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.riying.oss.service.OssService;
import com.riying.oss.util.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author: Mirai Zhao
 * @create: 2021-07-29  15:52
 * @Description:
 */
@Service
public class OsserviceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
// yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            //1.在文件名前加uuid，放置重复
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename=uuid+filename;
            //2.把文件夹按照日期分类
            // 2021/7/79
            //获取当前日期
            String timePath = new DateTime().toString("yyyy/MM/dd");
            filename=timePath+"/"+filename;
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
//         https://guli-college-mirai.oss-cn-shanghai.aliyuncs.com/6a.jpg
            return  "https://"+bucketName+"."+endpoint+"/"+filename;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
