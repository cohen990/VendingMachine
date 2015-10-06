package com.codedojos;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

class VendingMachine{
    public int Nickels = 0;
    public int Dimes = 0;
    public int Quarters = 0;
    public int Current = 0;
    public ArrayList<Product> Products = new ArrayList<Product>();

    public void insert(Coins... coins) {
        for(int i = 0; i < coins.length; i++) {
            if (coins[i] == Coins.Quarter) {
                Quarters++;
                Current += 25;
            }
            if (coins[i] == Coins.Nickel) {
                Nickels++;
                Current += 5;
            }
            if (coins[i] == Coins.Dime) {
                Dimes++;
                Current += 10;
            }
        }
    }

    public void service(int numNickels, int numDimes, int numQuarters, ArrayList<Product> products) {
        Nickels = numNickels;
        Dimes = numDimes;
        Quarters = numQuarters;

        Products = products;
    }

    public Coins[] coinReturn() {
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;

        while(Quarters > 0 && Current / 25 > 0){
            numQuarters ++;
            Quarters --;
            Current -= 25;
        }
        while (Dimes > 0 && Current / 10 > 0) {
            numDimes ++;
            Dimes --;
            Current -= 10;
        }
        while (Nickels > 0 && Current / 5 > 0) {
            numNickels ++;
            Nickels --;
            Current -= 5;
        }

        Coins[] coins = new Coins[numQuarters + numDimes + numNickels];
        for(int i = 0; i < numQuarters; i++){
            coins[i] = Coins.Quarter;
        }
        for(int i = 0; i < numDimes; i++){
            coins[i + numQuarters] = Coins.Dime;
        }
        for(int i = 0; i < numNickels; i++){
            coins[i + numQuarters + numDimes] = Coins.Nickel;
        }

        return coins;
    }
}

class Product{
    public String Name;
    public int Cost;

    private Product(String name, int cost){
        Name = name;
        Cost = cost;
    }

    public static Product A(){
        return new Product("A", 65);
    }

    public static Product B(){
        return new Product("B", 100);
    }

    public static Product C(){
        return new Product("C", 150);
    }
}

enum Coins{
    Nickel,
    Dime,
    Quarter
}

