
import java.util.HashMap;

/*
 * This represents a stock market and is used to create specific shares and is
 * accessed at least once every 100ms
 */

public class StockMarket {
    private static HashMap<String, CompanyShare> prices = new HashMap<String, CompanyShare>(){{
            put("SKY",  new CompanyShare("SKY" , 777.2, 1000));
            put("VOD",  new CompanyShare("VOD" , 123.4, 1000));
            put("TSCO", new CompanyShare("TSCO", 235.6, 1000));
            put("BP",   new CompanyShare("BP"  , 401.5, 1000));
        }};
    
    protected static HashMap<String, CompanyShare> getPrices(){
        return prices;
    }
    
    public static String status() {
        String output = "The current state of the stock market is: \n";
        output = prices.values().stream()
                                .map((c) -> c.toString()+"\n\n")
                                .reduce(output, String::concat);
        return output;
    }
}
