/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service;

import java.util.List;

/**
 *
 * @author saikatsakura
 */
public interface TwitterService {
    
    public List<String> search(String text);
    public List<String> getHomeTimeline();
}
