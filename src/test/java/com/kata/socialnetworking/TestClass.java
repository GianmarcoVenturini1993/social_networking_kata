package com.kata.socialnetworking;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


public class TestClass {

    private SocialUser alice = new SocialUser("Alice");;
    private SocialUser bob = new SocialUser("Bob");
    private SocialUser charlie = new SocialUser("Charlie");;

    @Test
    public void postingOwnTimelineTest() {

        String input = "I love the weather today";
        alice.updateTimeline(input);

        assertThat(alice.getTimeline()).isEqualTo("I love the weather today (moments ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleTest() {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        alice.updateTimeline(input);
        alice.updateTimeline(input2);

        assertThat(alice.getTimeline()).isEqualTo("This is a great day (moments ago)\nI love the weather today (moments ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleUserTest() {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        String inputBob = "Damn! We lost!";
        String inputBob2 = "Good game though.";

        alice.updateTimeline(input);
        alice.updateTimeline(input2);
        bob.updateTimeline(inputBob);
        bob.updateTimeline(inputBob2);

        assertThat(alice.getTimeline()).isEqualTo("This is a great day (moments ago)\nI love the weather today (moments ago)\n");
        assertThat(bob.getTimeline()).isEqualTo("Good game though. (moments ago)\nDamn! We lost! (moments ago)\n");
    }


}
