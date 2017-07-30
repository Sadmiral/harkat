package bdfx;

import java.util.ArrayList;

public class BottleDispenser {
    private String moneyS;
    private final String name;
    public int bottles;
    public double money;
    public ArrayList<Bottle> bottle_array;
    private static BottleDispenser bd = null;
    
    private BottleDispenser(String n) {
        name = n;
        bottles = 6;
        money = 0;
        
        bottle_array = new ArrayList<>();
        
        bottle_array.add(new Bottle("Pepsi Max", 0.5, 1.80));
        bottle_array.add(new Bottle("Pepsi Max", 1.5, 2.20));
        bottle_array.add(new Bottle("Coca-Cola Zero", 0.5, 2.00));
        bottle_array.add(new Bottle("Coca-Cola Zero", 0.5, 2.50));
        bottle_array.add(new Bottle("Fanta Zero", 0.5, 1.95));
        bottle_array.add(new Bottle("Fanta Zero", 0.5, 1.95));
    }
    static public BottleDispenser getInstance() {
        if (bd == null)
            bd = new BottleDispenser("Bottle Dispenser");
        return bd;
    }
    public void buyBottle(int val) {
        bottles -= 1;
        money -= bottle_array.get(val-1).getHinta();
        bottle_array.remove(bottle_array.get(val-1));
    }
    public void addMoney(double val) {
        money += val;
    }
    public String returnMoney() {
        money = (double)Math.round(money * 10000d) / 10000d;
        moneyS = String.valueOf(money) + "0€";
        money = 0;
        return moneyS;
    }
    public Bottle getObj(int x) {
        return bottle_array.get(x);
    }
}