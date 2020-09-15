package org.example;

import com.jcraft.jsch.*;

/**
 * @author javagists.com
 */
 class SftpConnectionApp {

    public static void main(String[] args) throws Exception {
        String hostname=args[0];
        int port=Integer.parseInt(args[1]);
        String username=args[2];
        String password=args[3];
        if(args.length<4){
            throw  new Exception("Please pass all the arguments needed for connecting to sftp");
        }

        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(username,hostname,port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            System.out.println("conneection succefful..........");
            //sftpChannel.get("/tmpremote/testDownload.txt", "/tmplocal/testDownload.txt");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }

    }

}