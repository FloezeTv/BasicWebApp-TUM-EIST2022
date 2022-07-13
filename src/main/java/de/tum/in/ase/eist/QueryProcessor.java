package de.tum.in.ase.eist;

import java.util.Arrays;
import java.util.regex.*;

import org.springframework.stereotype.Service;

@Service
public class QueryProcessor {

    Pattern plus = Pattern.compile(".*what is (\\d+) plus (\\d+).*");
    Pattern mul = Pattern.compile(".*what is (\\d+) multiplied by (\\d+).*");
    Pattern max = Pattern.compile(".*which of the following numbers is the largest: ([\\d, ]*)");

    public String process(String query) {
        var plusmatcher = plus.matcher(query);
        var maxmatcher = max.matcher(query);
        var mulmatcher = mul.matcher(query);
        query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("name")) {
            return query.substring(0, query.indexOf(":"));
        } else if (plusmatcher.matches()) {
            return "" + (Integer.valueOf(plusmatcher.group(1)) + Integer.valueOf(plusmatcher.group(2)));
        } else if (mulmatcher.matches()) {
            return "" + (Integer.valueOf(mulmatcher.group(1)) * Integer.valueOf(mulmatcher.group(2)));
        } else if (maxmatcher.matches()) {
            return "" + Arrays.stream(maxmatcher.group(1).split(", ")).mapToInt(Integer::valueOf).max().getAsInt();
        } else { // TODO extend the programm here
            return "";
        }
    }
}
