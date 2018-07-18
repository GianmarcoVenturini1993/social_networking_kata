package com.kata.socialnetworking;

import com.kata.socialnetworking.command.*;

import java.util.Scanner;

public class SocialNetwork {

    /*
    A simple main to run the application... users are already set and are not meant to be created by command line.
    As it is specified in the trace, just posting following and printing one's wall is possible.
     */
    private static final String POST_DELIMITER = " -> ";
    private static final String FOLLOW_DELIMITER = " follows ";
    private static final String WALL_DELIMITER = " wall";
    private static final String EXIT = "";

    public static void main(String[] args) {

        String[] param;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String cmd = sc.nextLine();

            if (cmd.contains(POST_DELIMITER)) {
                //posting:

                CommandInterface command = new UpdateTimeline();
                command.doCommand(cmd, POST_DELIMITER);

            } else if (cmd.contains(FOLLOW_DELIMITER)) {
                //follows:

                CommandInterface command = new FollowUser();
                command.doCommand(cmd, FOLLOW_DELIMITER);

            } else if (cmd.contains(WALL_DELIMITER)) {
                //print wall:

                CommandInterface command = new PrintUserWall();
                command.doCommand(cmd, WALL_DELIMITER);

            } else if (!cmd.equals(EXIT)) {

                CommandInterface command = new PrintTimeline();
                command.doCommand(cmd, null);

            } else  //I'm putting this here to prevent exception by pressing enter without feeding the System.in -- just a functional plus, meant for fluidity
                break;

        }

    }
}
