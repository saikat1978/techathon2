/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.scratchpad;

import java.util.HashMap;
import java.util.Map;

import techathon.core.utils.TextUtils;
import techathon.service.TwitterService;
import techathon.service.impl.TwitterServiceImpl;
import twitter4j.GeoLocation;
import twitter4j.Trend;
import twitter4j.TwitterException;


/**
 *
 * @author saikatsakura
 */
public class Twitter {
    
    private static TwitterService service = null;
    
    public static void main(String[] args) throws TwitterException {
        service = new TwitterServiceImpl();
        GeoLocation geo = new GeoLocation(22.566, 88.366);
        Map<String, Trend[]> map = service.getLocalTrends(geo); 
        for (String place : map.keySet()) {
            System.out.println("place " + place);
            System.out.println("====================================");
            for (Trend t : map.get(place)) {
                System.out.println(t);
            }
            System.out.println("-------------------------------------------------------");
        }
    }
    
    public static void homeline() throws TwitterException {
        
        int count = 0;
        TextUtils tu = new TextUtils();
        tu.LoadStopWords("C:\\TwitterDA\\TwitterDataAnalytics\\stopwords.txt");
        Map<String, Integer> trendsMap = new HashMap<String, Integer>();
        Map<String, Integer> tweetTrendsMap = null; 
        
        for (String s : service.getHomeTimeline()) {
            count++;
            tweetTrendsMap = tu.TokenizeText(s, false, false);
            for (String token : tweetTrendsMap.keySet()) {
                if (trendsMap.containsKey(token)) {
                    trendsMap.put(token, trendsMap.get(token) + tweetTrendsMap.get(token));
                } else {
                    trendsMap.put(token, tweetTrendsMap.get(token));
                }
            }
            
            //System.out.println(s);
        }
        //System.out.println(count);
        
        for (String s : trendsMap.keySet()) {
            System.out.println(s + " " + trendsMap.get(s));
        }
    }
}
