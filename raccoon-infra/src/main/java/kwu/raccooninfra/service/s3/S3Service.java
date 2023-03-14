package kwu.raccooninfra.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;


    public String upload(MultipartFile multipartFile) {
        try {
            return this.upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RaccoonException(RetConsts.ERR500);
        }
    }

    public void delete(String url) {
        amazonS3.deleteObject(bucket, urlToS3Key(url));
    }

    private String urlToS3Key(String url) {
        URL urlObject = null;
        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            throw new RaccoonException(RetConsts.ERR501);
        }

        return URLDecoder.decode(urlObject.getPath().substring(1));
    }
    private String upload(InputStream inputStream, String fileName) {
        String s3FileName = createFileName(fileName);
        try {
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(inputStream.available());
            amazonS3.putObject(bucket, s3FileName, inputStream, objMeta);
        } catch (IOException e) {
            throw new RaccoonException(RetConsts.ERR500);
        }

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    private String createFileName(String originalFileName) {
        return UUID.randomUUID() + "-" + originalFileName;
    }

}