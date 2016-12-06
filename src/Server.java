import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lef on 06/12/2016.
 */
public class Server{

    private final int NTHREADS = 10;
    private int PORT ;
    private String IP_ADDRESS;
    private ExecutorService pool;
    private List<String> connectedClients;

    public Server(String IP_ADDRESS, int PORT) {
        this.PORT = PORT;
        this.IP_ADDRESS = IP_ADDRESS;
        pool = Executors.newFixedThreadPool(NTHREADS);
    }

    public void launch(){
        try {
            ServerSocket s = new ServerSocket(PORT);
            while (true) {
                // accept connection
                Socket incoming = s.accept();
                // create connection
                ConnectionThread t = new ConnectionThread(incoming);
                // add IP to connected IP list
//                this.connectedClients.add(String.valueOf(t.getIncoming().getInetAddress()));
                // create a thread for each connection
                pool.submit(t);
            }
        } catch (IOException e1) {}
    }

    public List<String> getConnectedIPs(){
        return this.connectedClients;
    }
}
