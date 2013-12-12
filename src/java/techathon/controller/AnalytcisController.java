/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import techathon.core.CacheManager;
import techathon.service.TwitterService;

/**
 *
 * @author saikatsakura
 */
@Controller
@RequestMapping(value="/analytics")
public class AnalytcisController {

    private static final String OWS_FILE = "ows.json";
    
    @Autowired
    private TwitterService twitterService;
    
    @RequestMapping
    public String index() {
        return "index";
    }
    
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String search(@RequestParam String text, Model model, HttpServletRequest request) {
        List<String> tweets = twitterService.search(text);
        model.addAttribute("tweets", tweets);
        String cacheKey = UUID.randomUUID().toString();
        model.addAttribute(CacheManager.KEY, cacheKey);
        CacheManager.getManager().put(cacheKey, tweets);
        return "analytics/search";
    }
    
    @RequestMapping(value="/search", method= RequestMethod.GET)
    public String search() {
        
        return "analytics/search";
    }

    @RequestMapping(value = "/controlChart", method = RequestMethod.GET)
    public String controlChart() {
        return "analytics/controlChart";
    }

    @RequestMapping(value = "/controlChartData", method = RequestMethod.GET)
    public void controlChartData(HttpServletRequest request, HttpServletResponse response) {
        String infilename = request.getSession().getServletContext().getRealPath("/")
                + "/" + OWS_FILE;
        JSONArray result = twitterService.getControlChartDataTrend(infilename);
        try {
            response.getWriter().write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
