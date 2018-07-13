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

        assertThat(alice.getTimeline()).isEqualTo("I love the weather today\n");
    }


}
