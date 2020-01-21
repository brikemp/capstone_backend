package com.capstone.project.api;

import org.springframework.web.bind.annotation.*;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//@CrossOrigin(origins = {"http://localhost:3000"})
@CrossOrigin(origins = "*")
@RequestMapping("api/dynamictwitter/{account}")
@RestController
public class TwitterDynamicSearchController {

    @GetMapping
//    public String getTwitterUsername(@PathVariable(value = "account") String account) {
//        String username = "default";
//        username = account;
//        return username;
//    }
    public ArrayList<ArrayList<String>> getTwitter(@PathVariable(value = "account") String account) {
        String username = "default";
        username = account;
        int tweetCount = 0;
        ArrayList<ArrayList<String>> twitterFeed = new ArrayList<ArrayList<String>>(20);
        List<Status> tweets = null;
        HashMap<Integer, ArrayList<String>> tweetObject = new HashMap<Integer, ArrayList<String>>();

        System.out.println(tweetObject);
        // https://stackoverflow.com/questions/13387025/simplest-java-example-retrieving-user-timeline-with-twitter-api-version-1-1
        Twitter twitter = new TwitterFactory().getInstance();

        AccessToken accessToken = new AccessToken("618219014-JQU3uPUByJKB6vdhD4vgP7RChK8IecXsFEQNq1zd", "0kzHrlRofJ3Ch9dtyLGcglzDGqTXIYS6o5N82DDuDFaF5");
        twitter.setOAuthConsumer("igXBifuXBqpxvzEwDWO5G2JGP", "OAcISopwkPrm307i5EBIVv0NYxi9VvFr26Z17YGchJmBQlJcZ0");
        twitter.setOAuthAccessToken(accessToken);


        try {
            List<Status> statuses;
            String user;
//            if (args.length == 1) {
//                user = args[0];
//                statuses = twitter.getUserTimeline(user);
//            } else {
//                user = twitter.verifyCredentials().getScreenName();
//                statuses = twitter.getUserTimeline();
//            }

            user = username;
            statuses = twitter.getUserTimeline(user);
            tweets = statuses;
            System.out.println("Showing @" + user + "'s user timeline.");

            int i = 0;
            if (statuses.isEmpty()) {
                ArrayList<String> noTweets = new ArrayList<String>(1);
                noTweets.add("This user does not have any tweets");
                twitterFeed.add(noTweets);
            }
            for (Status status : statuses) {
//                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + " - " + status.getCreatedAt() + " - " + status.getGeoLocation());
                ArrayList<String> tweetArray = new ArrayList<String>(3);
                tweetArray.add(status.getText());
                tweetArray.add(status.getCreatedAt().toString());
                twitterFeed.add(tweetArray);
//                tweetObject.put(i, tweetArray);
                i++;
            }

            username = user;
//            System.out.println(statuses);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            ArrayList<String> notFound = new ArrayList<String>(1);
            notFound.add("User not found");
            twitterFeed.add(notFound);
//            System.exit(-1);
        }
//        return username;
        return twitterFeed;
    }
}

