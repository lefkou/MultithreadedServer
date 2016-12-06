import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lef on 06/12/2016.
 */
public class ConnectionThread implements Runnable{
    private Socket incoming;
    private ConcurrentHashMap<String, Double> prices = 
            new ConcurrentHashMap<String, Double>(){{
        put("SKY", 777.0);
        put("VOD", 123.4);
        put("TSCO", 235.6);
        put("BP", 401.5);
    }};

    public ConnectionThread(Socket incoming) {
        this.incoming = incoming;
    }

    public Socket getIncoming(){
        return incoming;
    }

    @Override
    public void run () {
        // set up streams for bidirectional transfer across connection socket
        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter
                    (incoming.getOutputStream(), true /* auto flush */);
            out.println("\nYou are connected to the share price "
                    + "quotation server: ");
            out.println(incoming.getInetAddress().getHostAddress()
                    + " on port " + incoming.getLocalPort()
                    + "\n"
            );
            prices.forEachEntry(prices.size(), e -> 
                    out.println("\r" + e.getKey()+": "+ "\t"+e.getValue() ));
            boolean done = false;
            while (!done) {
                out.println("Enter a buy/sell order in format BUY/SELL "
                    + "stockname number, or\n\renter QUIT to quit: ");
                String str = in.readLine();
                if (str.trim().equals("QUIT")) done = true;
                String[] array = str.split(" ");
                if("SELL".equals(array[0].toUpperCase())){
                    prices.put(array[1], Double.valueOf(array[2]));
                }
                if("BUY".equals(array[0].toUpperCase())){
                    
                }
//                if (str == null) {
//                    done = true;
//                } else {
////                    out.println("ECHO: " + str);
//                    
//                }
            }
            out.println("\nGoodbye.\n");
            incoming.close();
            System.out.println("Connection killed.");
        }catch (IOException e){}
    }
}
