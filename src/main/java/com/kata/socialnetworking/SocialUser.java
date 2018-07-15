package com.kata.socialnetworking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SocialUser {

    private String name;
    private ArrayList<HashMap<String, String>> timeline;
    private ArrayList<SocialUser> following;

    public SocialUser(String str) {
        this.name = str;
        this.timeline = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public String getName() {
        return name;
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

    public String printWall() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date now = new Date();
        Date curr_date = formatter.parse(now.toString());
        ArrayList<HashMap<String, String>> container = new ArrayList<>();

        //adding all timeline's post
        for (HashMap<String, String> el : timeline) {
            HashMap<String, String> timelineElement = new HashMap<>();
            timelineElement.put("body", name + " - " + el.get("body"));
            timelineElement.put("date", el.get("date"));
            container.add(timelineElement);
        }

        //adding all followed user's timeline posts
        for (SocialUser us : following) {
            for (HashMap<String, String> el : us.getTimeline()) {
                HashMap<String, String> followedElement = new HashMap<>();
                followedElement.put("body", us.getName() + " - " + el.get("body"));
                followedElement.put("date", el.get("date"));
                container.add(followedElement);
            }
        }

        StringBuilder result = new StringBuilder();

        //must be able to sort a global arrayList containing both timeline posts and followed user's posts
        //not sorted:
        for (int i = container.size() - 1; i >= 0; i--) {
            Date postTime = formatter.parse(container.get(i).get("date"));

            //specs say either 'minutes ago' or 'seconds ago'... must manage this feature
            if (TimeUnit.MILLISECONDS.toMinutes(curr_date.getTime() - postTime.getTime()) != 0)
                result.append(container.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toMinutes(curr_date.getTime() - postTime.getTime()) + " minutes ago)" + "\n");
            else
                result.append(container.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toSeconds(curr_date.getTime() - postTime.getTime()) + " seconds ago)" + "\n");
        }

        return result.toString();
    }
}

