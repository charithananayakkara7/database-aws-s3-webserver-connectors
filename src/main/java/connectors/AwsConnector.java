package Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Connector class to establish connection with AWS S3.
 */
public class AwsConnector {
    private static final Logger logger = LogManager.getLogger(AwsConnector.class);
    private final AmazonS3 s3client;
    private final String accessKey;
    private final String secretKey;
    private final String regionName;

    public AwsConnector(String accessKey, String secretKey, String regionName) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.regionName = regionName;

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(this.regionName);

        AmazonS3 client = null;
        try {
            client = builder.build();
            logger.info("AWS S3 connection established.");
        } catch (Exception e) {
            logger.error("AWS S3 connection failed.", e);        }

        this.s3client = client;    }

    /**
     * Returns the AmazonS3 client.
     *
     * @return the AmazonS3 client
     */
    public AmazonS3 getS3client() {
        return s3client;
    }
}
