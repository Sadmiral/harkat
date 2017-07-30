package bottles;

import java.util.*;

public class Mainclass {
    
    public static void main(String[] args) {
        int val, x = 0;
        
        BottleDispenser bd = BottleDispenser.getInstance();
        
        ArrayList list = new ArrayList();
        
        Bottle pullo1 = new Bottle("Pepsi Max", 0.5, 1.8);
        Bottle pullo2 = new Bottle("Pepsi Max", 1.5, 2.2);
        Bottle pullo3 = new Bottle("Coca-Cola Zero", 0.5, 2.0);
        Bottle pullo4 = new Bottle("Coca-Cola Zero", 1.5, 2.5);
        Bottle pullo5 = new Bottle("Fanta Zero", 0.5, 1.95);
        Bottle pullo6 = new Bottle("Fanta Zero", 0.5, 1.95);
        
        list.add(pullo1.getNimi());
        list.add(pullo1.getKoko());
        list.add(pullo1.getHinta());
        
        list.add(pullo2.getNimi());
        list.add(pullo2.getKoko());
        list.add(pullo2.getHinta());
        
        list.add(pullo3.getNimi());
        list.add(pullo3.getKoko());
        list.add(pullo3.getHinta());
        
        list.add(pullo4.getNimi());
        list.add(pullo4.getKoko());
        list.add(pullo4.getHinta());
        
        list.add(pullo5.getNimi());
        list.add(pullo5.getKoko());
        list.add(pullo5.getHinta());
        
        list.add(pullo6.getNimi());
        list.add(pullo6.getKoko());
        list.add(pullo6.getHinta());
        
        OUTER:
        while (true) {
            System.out.print("\n*** LIMSA-AUTOMAATTI ***\n1) Lisää rahaa koneeseen\n2) Osta pullo\n3) Ota rahat ulos\n4) Listaa koneessa olevat pullot\n0) Lopeta\nValintasi: ");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                val = sc.nextInt();
                switch (val) {
                    case 0:
                        break OUTER;
                    case 1:
                        bd.addMoney();
                        break;
                    case 2:
                        for (int i = 1; i < list.size()/3+1; i++) {
                            System.out.println(i + ". Nimi: " + list.get(x) + "\n\tKoko: " + list.get(x+1) + "\tHinta: " + list.get(x+2));
                            x = x+3;
                        }
                        x = 0;
                        
                        System.out.print("Valintasi: ");
                        Scanner sc1 = new Scanner(System.in);
                        
                        if (sc1.hasNextInt()) 
                            val = sc1.nextInt();
                        if (val == 1)
                            bd.buyBottle(pullo1.getNimi(), pullo1.getHinta(), pullo1.getKoko(), list, val);
                        if (val == 2)
                            bd.buyBottle(pullo2.getNimi(), pullo2.getHinta(), pullo2.getKoko(), list, val);
                        if (val == 3)
                            bd.buyBottle(pullo3.getNimi(), pullo3.getHinta(), pullo3.getKoko(), list, val);
                        if (val == 4)
                            bd.buyBottle(pullo4.getNimi(), pullo4.getHinta(), pullo4.getKoko(), list, val);
                        if (val == 5)
                            bd.buyBottle(pullo5.getNimi(), pullo5.getHinta(), pullo5.getKoko(), list, val);
                        if (val == 6)
                            bd.buyBottle(pullo6.getNimi(), pullo6.getHinta(), pullo6.getKoko(), list, val);
                        break;
                    case 3:
                        bd.returnMoney();
                        break;
                    case 4:
                        for (int i = 0; i < list.size()/3; i++) {
                            
                            System.out.println(i+1 + ". Nimi: " + list.get(x) + "\n\tKoko: " + list.get(x+1) + "\tHinta: " + list.get(x+2));
                            x = x + 3;
                        
                        }
                        x = 0;
                        break;
                    default:
                        break;
                }
            } else {                
                System.out.println("Väärä syöte.");
            }
        }
    }
}