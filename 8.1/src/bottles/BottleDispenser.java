package bottles;

import java.util.ArrayList;

public class BottleDispenser {
    private String moneyS, name;
    public int bottles, i, val, len;
    public double money, koko, hinta;
    public Bottle[] bottle_array;
    static private BottleDispenser bd = null;
    
    private BottleDispenser(String n) {
        name = n;
        bottles = 6;
        money = 0;       
    }
    
    static public BottleDispenser getInstance() {
        if (bd == null)
            new BottleDispenser("Bottle Dispenser");
        
        return bd;
    }

    public void addMoney() {
        money += 1;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
    }
    
    public void buyBottle(String nimi, double hinta, double koko, ArrayList list, int val) {
        int x = 0;
        
        if (money > hinta) {    
            if (bottles > 0) {
                if (val == 1) {
                    System.out.println("KACHUNK! " + list.get(0) + " tipahti masiinasta!");
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                }
                if (val == 2) {
                    System.out.println("KACHUNK! " + list.get(3) + " masiinasta!");
                    list.remove(3);
                    list.remove(3);
                    list.remove(3); 
                }
                if (val == 3) {
                    System.out.println("KACHUNK! " + list.get(6) + " tipahti masiinasta!");
                    list.remove(6);
                    list.remove(6);
                    list.remove(6);
                }
                if (val == 4) {
                    System.out.println("KACHUNK! " + list.get(9) + " tipahti masiinasta!");
                    list.remove(9);
                    list.remove(9);
                    list.remove(9);
                }
                if (val == 5) {
                    System.out.println("KACHUNK! " + list.get(12) + " tipahti masiinasta!");
                    list.remove(12);
                    list.remove(12);
                    list.remove(12);
                }
                if (val == 6) {
                    System.out.println("KACHUNK! " + list.get(15) + " tipahti masiinasta!");
                    list.remove(15);
                    list.remove(15);
                    list.remove(15);
                }
                money -= hinta;
                bottles -= 1;
            }
            else
                System.out.println("Pullot on loppu!");
        }
        else
            System.out.println("Syötä rahaa ensin!");
    }
    
    public void returnMoney() {
        money = (double)Math.round(money * 10000d) / 10000d;
        moneyS = String.valueOf(money);
        len = moneyS.length();
        
        if (len < 4) 
            System.out.println("Klink klink. Sinne menivät rahat! Rahaa tuli ulos " + moneyS.charAt(0) + "," + moneyS.charAt(2) + moneyS.charAt(3) + "0€");
        
        else if (len >= 4) {
            System.out.println("Klink klink. Sinne menivät rahat! Rahaa tuli ulos " + moneyS.charAt(0) + moneyS.charAt(1) + "," + moneyS.charAt(3) + "0€");
        }
        money = 0;
    }
}
