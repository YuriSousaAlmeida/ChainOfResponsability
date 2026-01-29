package org.example;

public class Main {
    public static void main(String[] args) {
        Nubank nubank = new Nubank(23);
        Paypal paypal = new Paypal(198.20);
        Bitcoin bitcoin = new Bitcoin(900);

        nubank.nextAccount(paypal);
        paypal.nextAccount(bitcoin);

        nubank.pay(240.0);
    }
}