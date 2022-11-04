package com.xdavide9.turbobuy.aws;

import com.amazonaws.services.s3.AmazonS3;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BucketService {

    private final AmazonS3 s3client;

    @Bean
    public CommandLineRunner createBuckets() {
        return args -> {
            String profilePictureBucketName = Bucket.ProfilePicturesBucket.getName();
            String salesPictureBucketName = Bucket.SalesPictureBucket.getName();
            try {
                if (!(s3client.doesBucketExistV2(profilePictureBucketName)))
                    s3client.createBucket(profilePictureBucketName);
                if (!(s3client.doesBucketExistV2(salesPictureBucketName)))
                    s3client.createBucket(salesPictureBucketName);
            } catch (Exception e) {
                log.error("Bucket name taken", e);
            }
        };
    }
}
