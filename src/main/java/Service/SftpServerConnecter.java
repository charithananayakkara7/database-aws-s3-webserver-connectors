package Service;

import com.jcraft.jsch.*;


import static java.lang.String.format;

public class SftpServerConnecter {

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


    //You can skip this return classes if you have already implemented project lombok
    //Return host Name
    public String getHostName() {
        return hostname;
    }

    //Return user name
    public String  getUser() {
        return username;
    }

    //Return password
    public String getPassword() {
        return userpassword;
    }

    //This is for creating session and sftp channel creation
    public void Setupsftpconnection() throws JSchException {
        session = jsch.getSession(username, hostname);
        session.setPassword(userpassword);
        //As explanation in the article you can use either yes or no depend on your scenario
        session.setConfig("StrictHostKeyChecking", "no");


        //Connection to a SSH server
        session.connect();
        System.out.println("Sftp session created sucessfully = " + session);
        sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("Sftp channel created sucessfully =" +sftpChannel);
    }

    //Closing Sftp Channel
    public void SftpchannelClose() {
        sftpChannel.disconnect();
    }

    //Disconnects the current session
    public void SessionClose() {
        session.disconnect();
    }

}
