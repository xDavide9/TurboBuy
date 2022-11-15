package com.xdavide9.turbobuy.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
@Slf4j
public class BucketService {

    private final AmazonS3 s3client;
    private final String bucketName = "turbobuybucket";

    public BucketService(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    @Bean
    public CommandLineRunner createBucket() {
        return args -> {
            try {
                if (!(s3client.doesBucketExistV2(bucketName)))
                    s3client.createBucket(bucketName);
            } catch (Exception e) {
                log.error("Bucket name taken", e);
            }
            uploadSalesImage(new FileInputStream("/Users/david/IdeaProjects/TurboBuy/src/main/resources/public/images/account.png"),
                    12, "png");
            uploadProfilePicture(new FileInputStream("/Users/david/IdeaProjects/TurboBuy/src/main/resources/public/images/account.png"),
                    14, "png");
        };
    }

    public void uploadSalesImage(InputStream stream, Integer id, String extension) {
        String path = "salesImages/" + id.toString() + "." + extension;
        uploadImage(stream, id, extension, path);
    }

    public void uploadProfilePicture(InputStream stream, Integer id, String extension) {
        String path = "profilePictures/" + id.toString() + "." + extension;
        uploadImage(stream, id, extension, path);
    }

    private void uploadImage(InputStream stream, Integer id, String extension, String path) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/" + extension);
            PutObjectRequest request = new PutObjectRequest(
                    bucketName,
                    path,
                    stream,
                    metadata);
            s3client.putObject(request);
        } catch (Exception e) {
            log.error("Error while uploading image '{}' to aws", id, e);
        }
    }
}
