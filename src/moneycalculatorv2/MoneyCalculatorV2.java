package moneycalculatorv2;

import java.net.URL;
import java.util.Scanner;

public class MoneyCalculatorV2 {

    public static void main(String[] args) {
        MoneyCalculatorV2 moneyCalculatorV2 = new MoneyCalculatorV2();
        moneyCalculatorV2.exec();
    }
    private double amount;
    private String currency="";
    private double converted;

    private void exec() {
        input();
        process();
        output();
    }

    private void input() {
        System.out.println("Introduce an amount");
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextDouble();
        System.out.println("Introduce una divisa");
        while(currency.equals("")){
            currency = scanner.nextLine().toUpperCase();
        }
    }

    private void process() {
        String divChain = "";
        while (true) {
            try {
                URL url = new URL("https://api.exchangeratesapi.io/latest");
                Scanner scanner1 = new Scanner(url.openStream());
                divChain = scanner1.nextLine();
                break;
            } catch (Exception ex) {
            }
        }
        int i = divChain.indexOf(":");
        int j = divChain.indexOf(",", i);
        String exchange = divChain.substring(i + 2, j); //Par divisa-valor
        String divisa=exchange.substring(1,4);
        while(!divisa.equals(currency)){
            i=j;
            j=divChain.indexOf(",", i+1);
            exchange=divChain.substring(i+1,j);
            divisa=exchange.substring(1,4);
        }
        System.out.println(divisa);
        String exchangeRate = exchange.substring(6);
        converted = amount/Double.parseDouble(exchangeRate);
    }

    private void output() {
        System.out.println(String.format("Son %.2f euros",converted));
    }
    
}
