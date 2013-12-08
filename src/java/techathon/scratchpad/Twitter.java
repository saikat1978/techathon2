/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.scratchpad;

import techathon.service.TwitterService;
import techathon.service.impl.TwitterServiceImpl;

/**
 *
 * @author saikatsakura
 */
public class Twitter {
    public static void main(String[] args) {
        TwitterService service = new TwitterServiceImpl();
        int count = 0;
        for (String s : service.getHomeTimeline()) {
            count++;
            System.out.println(s);
        }
        System.out.println(count);
    }
}
