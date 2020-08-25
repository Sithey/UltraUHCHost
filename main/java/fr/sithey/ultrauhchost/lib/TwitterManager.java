package fr.sithey.ultrauhchost.lib;

import fr.sithey.ultrauhchost.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterManager
{
    private Twitter twitter;
    private Main plugin;
    public static String consumer_key;
    public static String consumer_secret;
    public static String accesstoken;
    public static String accesstoken_secret;
    private long lastId;
    
    public TwitterManager(final Main plugin) {
        this.lastId = 0L;
        this.plugin = plugin;
    }
    
    public void connectToTwitter() {
        try {
            final ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(false);
            cb.setOAuthConsumerKey(consumer_key);
            cb.setOAuthConsumerSecret(consumer_secret);
            cb.setOAuthAccessToken(accesstoken);
            cb.setOAuthAccessTokenSecret(accesstoken_secret);
            final TwitterFactory twitterFactory = new TwitterFactory(cb.build());
            this.twitter = twitterFactory.getInstance();
            this.plugin.getLogger().info("Connected to twitter with @" + this.twitter.getScreenName() + ".");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Status tweet(final String tweet) {
        return this.tweet(tweet, null);
    }

    public Status tweet(final String tweet, final CommandSender sender) {
        if (sender != null && !(sender instanceof ConsoleCommandSender)) {
            this.plugin.getLogger().info(sender.getName() + " try to tweet \"" + tweet + "\"");
        }
        try {
            final Status status = this.twitter.updateStatus(tweet.replace("\\r", "\r").replace("\r", "").replaceAll("\\\\n|\\|", "\n"));
            if (sender != null) {
                final String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
                sender.sendMessage("Succefully tweeted: '" + status.getText() + "' on the account: @" + this.twitter.getScreenName() + "\n\nTweet Link: " + url);
            }
            this.lastId = status.getId();
            return status;
        }
        catch (TwitterException e) {
            final String reply =  "An unknown error occured while posting a tweet.";
            if (sender != null && !(sender instanceof ConsoleCommandSender)) {
                sender.sendMessage(reply);
            }
            System.out.println(reply);
            e.printStackTrace();
            return null;
        }
    }
    
    public long getLastId() {
        return this.lastId;
    }
    
    public Twitter getTwitter() {
        return this.twitter;
    }
}
