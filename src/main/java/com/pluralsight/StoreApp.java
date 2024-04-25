package com.pluralsight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class StoreApp {

    static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void main(String[] args) {

        loadInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();

            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We dont carry that product");
                continue;
            }
            System.out.printf("We carry %s and the price is $%.2f\n", matchedProduct.getName(), matchedProduct.getPrice());
            System.out.print("Do you want to search again? (y/n)");
            char choice = Character.toLowerCase(scanner.next().charAt(0));
            switch (choice) {
                case 'y':
                    continue;
                case 'n':
                    System.out.println("Goodbye.");
                    System.exit(0);
            }
        }
    }

    public static void loadInventory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));
            String line = "";
            while (( line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                Product product = new Product(id, name, price);
                inventory.put(id, product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
