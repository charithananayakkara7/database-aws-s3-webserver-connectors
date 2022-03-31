package Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class   Awsconnector {
    Logger logger = LogManager.getLogger(Awsconnector.class);
    private final AmazonS3 s3client;
    private String Accesskey;
    private String SecretKey;
    private String Regionname;

    public Awsconnector(String Accesskey, String SecretKey, String Regionname) {
        this.Accesskey = Accesskey;
        this.SecretKey = SecretKey;
        this.Regionname = Regionname;
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(Accesskey, SecretKey);
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials));
        builder.setRegion(Regionname);
        this.s3client = builder.build();
        if (s3client != null) {
            logger.info("AWS S3 connection established " + s3client);

        } else {
            logger.error("AWS S3 connection Failed" + s3client);
        }
    }
}
