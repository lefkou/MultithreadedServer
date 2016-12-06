/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lef
 */
public class Run {
    public static void main(String[] args) {
        Server s = new Server("localhost", 8189);
        s.launch();
//        String a = "BUY BP 1000";
//        String[] ar = a.split(" ");
//        for (String string : ar) {
//            System.out.println(string);
//        }   
    }
}
