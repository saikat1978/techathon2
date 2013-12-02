/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import techathon.core.TwitterSession;
import techathon.service.AbstractTwitterService;
import techathon.service.TwitterService;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author saikatsakura
 */
@Service
public class TwitterServiceImpl extends AbstractTwitterService implements TwitterService {

    public List<String> search(String text) {
        List<String> tweets = new LinkedList<String>();
        Query query = new Query(text);
        query.setCount(100);
        try {
            TwitterSession session = getAuthenticatedTwitterSession();
            QueryResult queryResult = session.getTwitterSession().search(query);
            if (queryResult.hasNext()) {
                while (queryResult.hasNext()) {
                    for (Status s : queryResult.getTweets()) {
                        tweets.add(s.getText());
                    }

                    queryResult = session.getTwitterSession().search(queryResult.nextQuery());
                }
            } else {
                for (Status s : queryResult.getTweets()) {
                    tweets.add(s.getText());
                }
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweets;
    }
}
