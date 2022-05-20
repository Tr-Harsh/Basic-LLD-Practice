package org.dailyDelivery.Controller;

import com.jcraft.jsch.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class Test {

    private final String username = "";
    private final String password = "";

    @GetMapping("getPath")
    public void getPath(@RequestParam("path") String path) throws JSchException, SftpException {
        setupJsch();
        uploadFile(path);
    }

    private ChannelSftp setupJsch() throws JSchException, JSchException {
        JSch jsch = new JSch();
        jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
        String remoteHost = "";
        Session jschSession = jsch.getSession(username, remoteHost);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public void uploadFile(String path) throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();
        String localFile = "src/main/resources/sample.txt";
        channelSftp.put(localFile, path + "jschFile.txt");
        channelSftp.exit();
    }
}

