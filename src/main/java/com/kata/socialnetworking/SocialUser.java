package com.kata.socialnetworking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SocialUser {

    private String name;
    private ArrayList<HashMap<String, String>> timeline;
    private ArrayList<SocialUser> following;
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);

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

        Date curr_date = getCurrentDate();

        return getOutput(timeline, curr_date);
    }

    public void follows(SocialUser user) {
        following.add(user);
    }

    public String printWall() throws ParseException {

        Date curr_date = getCurrentDate();

        ArrayList<HashMap<String, String>> container = getAllPostsFromFeed();
        sortPosts(container);

        return getOutput(container, curr_date);

    }

    private Date getCurrentDate() throws ParseException {

        Date now = new Date();
        return FORMATTER.parse(now.toString());

    }

    private boolean compareMinutes(Date a, Date b) {

        return TimeUnit.MILLISECONDS.toMinutes(a.getTime() - b.getTime()) == 0;

    }

    private String getOutput(ArrayList<HashMap<String, String>> list, Date current_date) throws ParseException{

        StringBuilder result = new StringBuilder();

        for (int i = list.size() - 1; i >= 0; i--) {
            Date postTime = FORMATTER.parse(list.get(i).get("date"));

            if (!compareMinutes(current_date, postTime))
                result.append(list.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toMinutes(current_date.getTime() - postTime.getTime()) + " minutes ago)" + "\n");
            else
                result.append(list.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toSeconds(current_date.getTime() - postTime.getTime()) + " seconds ago)" + "\n");
        }

        return result.toString();
    }

    private ArrayList<HashMap<String, String>> getAllPostsFromFeed() {

        ArrayList<HashMap<String, String>> container = new ArrayList<>();

        //adding all timeline's posts with original user's name embedded
        for (HashMap<String, String> el : timeline) {
            HashMap<String, String> timelineElement = new HashMap<>();
            timelineElement.put("body", name + " - " + el.get("body"));
            timelineElement.put("date", el.get("date"));
            container.add(timelineElement);
        }

        //adding all followed user's timeline posts with each user's name embedded
        for (SocialUser us : following) {
            for (HashMap<String, String> el : us.getTimeline()) {
                HashMap<String, String> followedElement = new HashMap<>();
                followedElement.put("body", us.getName() + " - " + el.get("body"));
                followedElement.put("date", el.get("date"));
                container.add(followedElement);
            }
        }

        return container;
    }

    private void sortPosts(ArrayList<HashMap<String, String>> posts) throws ParseException{

        //bubblesort
        for(int i = 0; i < posts.size(); i++) {
            boolean flag = false;

            for(int j = 0; j < posts.size()-1; j++) {
                if (FORMATTER.parse(posts.get(j).get("date")).after(FORMATTER.parse(posts.get(j+1).get("date")))) {
                    Collections.swap(posts, j, j+1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}

