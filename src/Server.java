import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingWorker;

/*
 * This server creates many connections using the connection class and allows 
 * more than one user to connect
 */
public class Server extends SwingWorker{

    private final int NTHREADS = 3;
    private int PORT ;
    private String IP_ADDRESS;
    private ExecutorService pool;
    private List<Socket> connectedClients;
    private GUI gui;
    private ServerSocket s;
    private ShareMutexMonitor mtx;

    public Server(String IP_ADDRESS, int PORT, GUI gui, ShareMutexMonitor m) {
        this.PORT = PORT;
        this.IP_ADDRESS = IP_ADDRESS;
        this.gui = gui;
        this.mtx = m;
        this.pool = Executors.newFixedThreadPool(NTHREADS);
        
    }
    
    @Override
    public Object doInBackground(){
        this.start();
        return null;
    }
    
    // get server's time
    public String getCurrentServerTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date); //yyyy/MM/dd HH:mm:ss
    }
    
    // method starts accepting connections 
    public void start(){
        try {
            s = new ServerSocket(PORT);
            log(getCurrentServerTime()+": Server started.");
            while (true) {
                // accept connection
                Socket incoming = s.accept();
                // create connection
                Connection t = new Connection(incoming, this, this.gui, mtx);
                // add IP to connected IP list
                pool.submit(t);
                // create a thread for each connection
            }
        } 
        catch (IOException e) {}
        catch (Exception e1){ 
            log(getCurrentServerTime()+": Server launch failed.");
        }
    }
    
    // stops the server and prints info in server log
    public void stop() {
        try{
            pool.shutdown();
            s.close();
            log(getCurrentServerTime()+": Server stopped.");
        } 
        catch (IOException e) {}
        catch(Exception e1){log("Server stop failed.");}
        
    }
    
    // logs information to server log
    public void log(String text){
         this.gui.getServerLog_textArea().append(text + "\n");
    }
    
 


    

    
}
