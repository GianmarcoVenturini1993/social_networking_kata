package com.kata.socialnetworking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.ocpsoft.prettytime.PrettyTime;

public class SocialUser {

    private String name;
    private ArrayList<HashMap<String, String>> timeline;
    private ArrayList<SocialUser> following;

    public SocialUser(String str) {
        this.name = str;
        this.timeline = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public ArrayList<HashMap<String, String>> getTimeline() {
        return timeline;
    }

    public void updateTimeline(String input) {

        Date date = new Date();

        HashMap<String, String> post = new HashMap<>();
        post.put("body", input);
        post.put("date", date.toString());

        this.timeline.add(post);

    }

    public String printTimeline() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date now = new Date();
        Date curr_date = formatter.parse(now.toString());

        StringBuilder result = new StringBuilder();

        for (int i = timeline.size() - 1; i >= 0; i--) {
            Date postTime = formatter.parse(timeline.get(i).get("date"));
            System.out.println();

            //specs say either 'minutes ago' or 'seconds ago'... must manage this feature
            if (TimeUnit.MILLISECONDS.toMinutes(curr_date.getTime() - postTime.getTime()) != 0)
                result.append(timeline.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toMinutes(curr_date.getTime() - postTime.getTime()) + " minutes ago)" + "\n");
            else
                result.append(timeline.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toSeconds(curr_date.getTime() - postTime.getTime()) + " seconds ago)" + "\n");
        }

        return result.toString();
    }

    public void follows(SocialUser user) {
        following.add(user);
    }

    public String printWall() {

        ArrayList<HashMap<String, String>> container = new ArrayList<>();

        //adding all timeline's post
        for (HashMap<String, String> el : timeline) {
            container.add(el);
        }

        //adding all followed user's timeline posts
        for (SocialUser us : following) {
            for (HashMap<String, String> el : us.getTimeline()) {
                container.add(el);
            }
        }

        StringBuilder result = new StringBuilder();

        //must be able to sort a global arrayList containing both timeline posts and followed user's posts
        return "";
    }
}

