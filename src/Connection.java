
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.SwingUtilities;

/**
 * Created by lef on 06/12/2016.
 */
public class Connection implements Runnable {

    private Socket incoming;
    private GUI gui;
    private Server server;
    private ShareMutexMonitor mtx;

    public Connection(Socket incoming, Server server, GUI gui, ShareMutexMonitor m) {
        this.incoming = incoming;
        this.gui = gui;
        this.server = server;
        this.mtx = m;
    }

    public Socket getIncoming() {
        return incoming;
    }

    public void log(String text) {
        SwingUtilities.invokeLater(() -> {
            this.gui.getServerLog_textArea().append(text + "\n");
        });
    }

    @Override
    public void run() {
        // set up streams for bidirectional transfer across connection socket
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter(incoming.getOutputStream(), true /* auto flush */);
            // CODE FOR LOG 
            out.println("\nYou are connected to the share price "
                    + "quotation server: ");
            out.println(incoming.getLocalAddress()
                    + " on port " + incoming.getLocalPort()
                    + "\n"
            );
            // CODE FOR LOG 
            log("\n" + this.server.getCurrentServerTime()
                    + ": Client with IP " + incoming.getInetAddress()
                    + " at port " + incoming.getPort() + "\n\t  connected."
            );
            // CODE FOR LOG 
            this.mtx.enterCrit(); // monitor start
            StockMarket.getPrices().values().forEach(v -> {
                out.println("\r" + v.getCode() + ": " + "\t" + v.getPrice());
            });
            this.mtx.exitCrit(); // monitor end
            boolean done = false;
            while (!done) {
                out.println("Enter a buy/sell order in format BUY/SELL "
                        + "stockname number, or\n\renter QUIT to quit: ");
                String str = in.readLine();
                if (str.trim().toUpperCase().equals("QUIT")) {
                    break;
                }
                String[] array = str.trim().split(" ");
                if (array.length == 3) {
                    // get user action
                    String action = array[0].toUpperCase();
                    // get company code
                    String code = array[1].toUpperCase();
                    // how many shares to buy/sell
                    try {
                        int numberOfSharesReq = Integer.valueOf(array[2].toUpperCase());

                        this.mtx.enterCrit(); // monitor start
                        // check if company code exists in stockmarket
                        boolean flag = StockMarket.getPrices().containsKey(code);
                        this.mtx.exitCrit(); // monitor end
                        if (flag) {
                            // get the corresponding value in hashmap
                            this.mtx.enterCrit(); // monitor start
                            CompanyShare cs = (CompanyShare) StockMarket.getPrices().get(code);
                            this.mtx.exitCrit(); // monitor end
                            // if first word = BUY
                            switch (action) {
                                case "BUY":
                                    int difference = cs.getNumberOfShares() - Math.abs(numberOfSharesReq);
                                    if (difference >= 0) {
                                        // enter critical code section
                                        this.mtx.enterCrit();// monitor start
                                        cs.setNumberOfShares(difference);
                                        this.mtx.exitCrit();// monitor end
                                        // exit
                                        out.println("Order Confirmed");
                                        // CODE FOR LOG
                                        log("\n" + this.server.getCurrentServerTime()
                                                + ": Client with IP " + incoming.getInetAddress()
                                                + " at port " + incoming.getPort() + "\n\t  bought "
                                                + numberOfSharesReq + " shares of " + cs.getCode()
                                        );
                                    } else {
                                        out.println("Order failed. Not enough shares.");
                                    }
                                    break;
                                case "SELL":
                                    // enter critical code section
                                    this.mtx.enterCrit();
                                    cs.setNumberOfShares(cs.getNumberOfShares() + Math.abs(numberOfSharesReq));
                                    this.mtx.exitCrit();
                                    // exit
                                    out.println("Order Confirmed");
                                    // CODE FOR LOG
                                    log("\n" + this.server.getCurrentServerTime()
                                            + ": Client with IP " + incoming.getInetAddress()
                                            + " at port " + incoming.getPort() + "\n\t  sold "
                                            + numberOfSharesReq + " shares of " + code
                                    );
                                    break;
                                default:
                                    out.println("Invalid Action. Please try again.");
                                    break;
                            }
                        } else {
                            out.println("Invalide share code. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        out.print("Invalid number of shares please try again.");
                    }
                }
            }
            out.println("\nGoodbye.\n");
            incoming.close();
            // CODE FOR LOG 
            log("\n" + this.server.getCurrentServerTime()
                    + ": Client with IP " + incoming.getInetAddress()
                    + " at port " + incoming.getPort() + "\n\t  disconnected."
            );
        } catch (IOException e) {
        }
    }
}
