/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author saikatsakura
 */
@Controller
@RequestMapping(value="/analytics")
public class AnalytcisController {
    
    @RequestMapping
    public String index() {
        return "index";
    }
    
}
