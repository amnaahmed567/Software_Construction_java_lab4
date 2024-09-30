package twitter;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    /**
     * Find tweets by a specific author.
     * 
     * @param tweets   list of tweets.
     * @param username the author's username.
     * @return list of tweets written by the specified user.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (tweet.getAuthor().equalsIgnoreCase(username)) {
                result.add(tweet);
            }
        }
        return result;
    }

    /**
     * Find tweets that were sent during a specified time period.
     * 
     * @param tweets   list of tweets.
     * @param timespan the time range.
     * @return list of tweets that fall within the timespan.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (!tweet.getTimestamp().isBefore(timespan.getStart()) && !tweet.getTimestamp().isAfter(timespan.getEnd())) {
                result.add(tweet);
            }
        }
        return result;
    }

    /**
     * Find tweets that contain any of the specified words.
     * 
     * @param tweets list of tweets.
     * @param words  list of words to search for.
     * @return list of tweets containing any of the words.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            String tweetText = tweet.getText().toLowerCase();
            for (String word : words) {
                if (tweetText.contains(word.toLowerCase())) {
                    result.add(tweet);
                    break; // Break to avoid adding the same tweet multiple times
                }
            }
        }
        return result;
    }
}
