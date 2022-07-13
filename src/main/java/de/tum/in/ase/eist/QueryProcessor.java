package de.tum.in.ase.eist;

import java.util.regex.*;

import org.springframework.stereotype.Service;

@Service
public class QueryProcessor {

    Pattern plus = Pattern.compile("what is (\\d+) plus (\\d+)");

    public String process(String query) {
        var plusmatcher = plus.matcher(query);
        query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("name")) {
            return query.substring(0, query.indexOf(":"));
        } else if (plusmatcher.matches()) {
            return "" + (Integer.valueOf(plusmatcher.group(1)) + Integer.valueOf(plusmatcher.group(2)));
        } else { // TODO extend the programm here
            return "";
        }
    }
}
