package com.kata.socialnetworking;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


public class TestClass {

    private SocialUser alice;
    private SocialUser bob;
    private SocialUser charlie;


    @Before
    public void setUp() {
        SocialUser alice = new SocialUser("Alice");
        SocialUser bob = new SocialUser("Bob");
        SocialUser charlie = new SocialUser("Charlie");
    }


    @Test
    public void postingOwnTimelineTest() {

        String input = "Alice -> I love the weather today";
        alice.updateTimeline(input);

        assertThat(alice.getTimeline()).isEqualTo("Alice -> I love the weather today");
    }


}
