/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import techathon.service.TwitterService;

/**
 *
 * @author saikatsakura
 */
@Controller
@RequestMapping(value="/analytics")
public class AnalytcisController {
    
    @Autowired
    private TwitterService twitterService;
    
    @RequestMapping
    public String index() {
        return "index";
    }
    
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String search(@RequestParam String text) {
        twitterService.search(text);
        return "analytics/search";
    }
    
    @RequestMapping(value="/search", method= RequestMethod.GET)
    public String search() {
        
        return "analytics/search";
    }
    
}
