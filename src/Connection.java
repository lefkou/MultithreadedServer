import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import javax.swing.SwingUtilities;
/**
 * Created by lef on 06/12/2016.
 */
public class Connection implements Runnable{
    private Socket incoming;
    private GUI gui;
    private Server server;

    public Connection(Socket incoming, Server server, GUI gui) {
        this.incoming = incoming;
        this.gui = gui; 
        this.server = server;
    }

    public Socket getIncoming(){
        return incoming;
    }
    
    public void log(String text){
        SwingUtilities.invokeLater(()-> {
            this.gui.getServerLog_textArea().append(text+"\n");
        });
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
            out.println(incoming.getLocalAddress()
                    + " on port " + incoming.getLocalPort()
                    + "\n"
            );
            log("\n" + this.server.getCurrentServerTime() +
                    ": Client with IP "+ incoming.getInetAddress() +
                    " at port " + incoming.getPort() + "\n\t  connected."
            );
            // INSERT CODE FOR LOG 
            StockMarket.getPrices().values().forEach(v -> {
                out.println("\r" + v.getCode() +": "+ "\t"+ v.getPrice());
            });
            boolean done = false;
            while (!done) {
                out.println("Enter a buy/sell order in format BUY/SELL "
                    + "stockname number, or\n\renter QUIT to quit: ");
                String str = in.readLine();
                if (str.trim().toUpperCase().equals("QUIT"))
                    break;
                String[] array = str.trim().split(" ");
                // get action
                String action = array[0].toUpperCase();
                // get company code
                String code = array[1].toUpperCase();
                // how many shares to buy/sell
                int numberOfSharesReq = Integer.valueOf(array[2].toUpperCase());
                // company code exists in stockmarket
                if(StockMarket.getPrices().containsKey(code)){
                    // get the corresponding value in hashmap
                    CompanyShare cs = (CompanyShare) StockMarket.getPrices().get(code);
                     // if first word = BUY
                    if(action.equals("BUY")){
                        int difference = cs.getNumberOfShares() - numberOfSharesReq;
                        if(difference >= 0){
                            cs.setNumberOfShares(difference);
                            out.println("Order Confirmed");
                            // INSERT CODE FOR LOG 
                            log("\n"+ this.server.getCurrentServerTime() + 
                                ": Client with IP "+ incoming.getInetAddress() +
                                " at port " + incoming.getPort() + "\n\t  bought "+
                                numberOfSharesReq + " shares of "+ cs.getCode()
                        );
                        }else{
                            out.println("Order failed. Not enough shares.");
                        }
                    }
                    // if first word = SELL
                    if(action.equals("SELL")){
                        cs.setNumberOfShares(cs.getNumberOfShares()+ numberOfSharesReq);
                        out.println("Order Confirmed");
                        // INSERT CODE FOR LOG 
                        log("\n"+ this.server.getCurrentServerTime() + 
                            ": Client with IP "+ incoming.getInetAddress() +
                            " at port " + incoming.getPort() + "\n\t  sold "+
                            numberOfSharesReq + " shares of "+ cs.getCode()
                        );
                    }
                } 
            }
            out.println("\nGoodbye.\n");
            incoming.close();
            log("\n"+ this.server.getCurrentServerTime() + 
                    ": Client with IP "+ incoming.getInetAddress() +
                    " at port " + incoming.getPort() + "\n\t  disconnected."
            );
        }catch (IOException e){}
    }
}
