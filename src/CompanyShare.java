/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lef
 */
public class CompanyShare {
    private String code;
    private double price;
    private int numberOfShares;

    public CompanyShare(String code, double price, int numberOfShares) {
        this.code = code;
        this.price = price;
        this.numberOfShares = numberOfShares;
    }

    public String getCode() {
        return code;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getPrice() {
        return price;
    }
    
    
    
    @Override
    public String toString() {
        return "Code: " + this.code + "\n" +"Price: " + this.price + "\n" +
                "Available Shares: " + this.numberOfShares;
    }

    
    
    
}
