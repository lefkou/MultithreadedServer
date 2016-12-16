/*
 * This class represent a monitor and is used to ensure safety during concurrency
 */
public class ShareMutexMonitor {
    private boolean critCodeBusy = false;
    
    public synchronized void enterCrit(){
        while(critCodeBusy){
            try {
                wait();
            } catch (InterruptedException e) {}
            critCodeBusy = true;
        }
    }
    public synchronized void exitCrit(){
        critCodeBusy = false;
        notify();
    }
}
