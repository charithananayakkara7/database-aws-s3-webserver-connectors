package Service;

import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static java.lang.String.format;

public class SftpServerConnecter {
    Logger logger = LogManager.getLogger(SftpServerConnecter.class);

    JSch jsch = new JSch();
    Session session = null;
    ChannelSftp sftpChannel = null;
    private String hostname;
    private String username;
    private String userpassword;

    public SftpServerConnecter(String hostName, String userName, String userPassword) throws JSchException {
        this.hostname = hostName;
        this.username = userName;
        this.userpassword = userPassword;
        Setupsftpconnection();
    }

    //This is for creating session and sftp channel creation
    public void Setupsftpconnection() throws JSchException {
        session = jsch.getSession(username, hostname);
        session.setPassword(userpassword);
        session.setConfig("StrictHostKeyChecking", "no");


        //Connection to a SSH server
        session.connect();
        sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        if ( session != null) {
            logger.info("Sftp session created sucessfully =" +session);

        } else {
            logger.error("Error while connecting to sftp session =" +session);
        }
    }

    //Closing Sftp Channel
    public void SftpchannelClose() {
        sftpChannel.disconnect();
        logger.info("Sftp channel closed");
    }

    //Disconnects the current session
    public void SessionClose() {
        session.disconnect();
        logger.info("Sftp session closed");
    }

}
