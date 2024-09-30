package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {

    /**
     * Get the time period spanned by tweets.
     *
     * @param tweets list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            throw new IllegalArgumentException("The list of tweets cannot be empty");
        }
        
        Instant start = tweets.get(0).getTimestamp();
        Instant end = tweets.get(0).getTimestamp();
        
        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (timestamp.isBefore(start)) {
                start = timestamp;
            }
            if (timestamp.isAfter(end)) {
                end = timestamp;
            }
        }
        
        return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     *
     * @param tweets list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     * A username-mention is "@" followed by a Twitter username (as defined by Tweet.getAuthor()'s spec).
     * The username-mention cannot be immediately preceded or followed by any character valid in a Twitter username.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        Pattern pattern = Pattern.compile("(?<!\\w)@(\\w+)");
        
        for (Tweet tweet : tweets) {
            Matcher matcher = pattern.matcher(tweet.getText());
            while (matcher.find()) {
                mentionedUsers.add(matcher.group(1).toLowerCase());
            }
        }
        
        return mentionedUsers;
    }
}
