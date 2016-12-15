/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lef
 */
public class MutexMonitor {
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
