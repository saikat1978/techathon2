/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service.impl;

import Chapter4.classification.bayes.Classification;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import techathon.core.Application;
import techathon.service.SentimentAnalyzerService;

/**
 *
 * @author saikatsakura
 */
@Service
public class SentimentAnalyzerServiceImpl implements SentimentAnalyzerService {

    public Classification classify(String text) {
        Classification posC = Application.getInstance().getPositiveClassifier().classify(text);
        Classification negC = Application.getInstance().getNegativeClassifier().classify(text);
        System.out.println("pos " + posC);
        System.out.println("neg " + negC);
        //int compare = Double.compare(posC.getConfidence(), negC.getConfidence());
        double pConf = posC.getConfidence();
        double nConf = negC.getConfidence();
        if (pConf > nConf) {
            return posC;
        } else if (pConf < nConf) {
            return negC;
        } else {
            return new Classification("neutral", 0.0);
        }
        
    }

    public Map<String, Classification> classify(Collection<String> textCollection) {
        Map<String, Classification> classificationMap = new LinkedHashMap<String, Classification>();
        for (String text : textCollection) {
            final Classification classification = classify(text);
            classificationMap.put(text, classification);
        }
        return classificationMap;
    }
    
}
