package com.kata.socialnetworking;

import java.text.ParseException;
import java.util.Scanner;

public class SocialNetwork {

    /*
    A simple main to run the application... users are already set and are not meant to be created by command line.
    As it is specified in the trace, just posting following and printing one's wall is possible.
     */

    public static void main(String[] args) {

        String POST_DELIMITER = " -> ";
        String FOLLOW_DELIMITER = " follows ";
        String WALL_DELIMITER = " wall";

        String[] param;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String cmd = sc.nextLine();

            if (cmd.contains("->")) {
                //posting:

                param = cmd.split(POST_DELIMITER);
                String user = param[0];
                String post = param[1];

                Users.MAPPING.get(user).updateTimeline(post);

            } else if (cmd.contains("follows")) {
                //follows:

                param = cmd.split(FOLLOW_DELIMITER);
                String user1 = param[0];
                String user2 = param[1];

                Users.MAPPING.get(user1).follows(Users.MAPPING.get(user2));

            } else if (cmd.contains("wall")) {
                //print wall:

                param = cmd.split(WALL_DELIMITER);

                try {
                    System.out.println(Users.MAPPING.get(param[0]).printWall());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else if (!cmd.equals("")) {

                try {
                    System.out.println(Users.MAPPING.get(cmd).printTimeline());
                } catch (ParseException f) {
                    f.printStackTrace();
                }
            } else  //I'm putting this here to prevent exception by pressing enter without feeding the System.in -- just a functional plus, meant for fluidity
                break;

        }

    }
}
