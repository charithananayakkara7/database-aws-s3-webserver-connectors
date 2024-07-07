package Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Connector class to establish a connection with an SFTP server.
 */
public class SftpServerConnector {
    private static final Logger logger = LogManager.getLogger(SftpServerConnector.class);

    private final JSch jsch;
    private Session session;
    private ChannelSftp sftpChannel;

    private final String hostname;
    private final String username;
    private final String userpassword;

    public SftpServerConnector(String hostname, String username, String userpassword) throws JSchException {
        this.hostname = hostname;
        this.username = username;
        this.userpassword = userpassword;
        this.jsch = new JSch();
        setupSftpConnection();
    }

    /**
     * Sets up the SFTP connection by establishing a session and opening an SFTP channel.
     *
     * @throws JSchException if a connection error occurs
     */
    private void setupSftpConnection() throws JSchException {
        try {
            session = jsch.getSession(username, hostname);
            session.setPassword(userpassword);
            session.setConfig("StrictHostKeyChecking", "no");

            // Connect to the SSH server
            session.connect();

            if (session.isConnected()) {
                logger.info("SFTP session created successfully.");
            } else {
                logger.error("Error while connecting to SFTP session.");
            }

            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();

            if (sftpChannel.isConnected()) {
                logger.info("SFTP channel created successfully.");
            } else {
                logger.error("Error while opening SFTP channel.");
            }

        } catch (JSchException e) {
            logger.error("Error setting up SFTP connection: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Closes the SFTP channel.
     */
    public void closeSftpChannel() {
        if (sftpChannel != null && sftpChannel.isConnected()) {
            sftpChannel.disconnect();
            logger.info("SFTP channel closed.");
        }
    }

    /**
     * Disconnects the current session.
     */
    public void closeSession() {
        if (session != null && session.isConnected()) {
            session.disconnect();
            logger.info("SFTP session closed.");
        }
    }
}
