package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        MyList<Obiekcik> lista = new MyList<Obiekcik>();
        Random losowe = new Random();
        int choice;
        int x;
        Obiekcik obiekt= new Obiekcik(5);
        lista.add(obiekt);
        Scanner scan= new Scanner(System.in);
        while(true)
        {
            System.out.println("1. Dodaj element do listy.");
            System.out.println("2. Dodaj element do listy, no indeksie.");
            System.out.println("3. Wyczysc liste.");
            System.out.println("4. Sprawdz czy zawiera.");
            System.out.println("5. Ustaw pojemnosc.");
            System.out.println("6. Pobierz element.");
            System.out.println("7. Znajdz indeks obiektu.");
            System.out.println("8. Ustaw element.");
            System.out.println("9. Usun element.");
            System.out.println("10. Pobierz rozmiar listy.");
            System.out.println("11. Wyswietl liste.");
            choice=scan.nextInt();

            switch(choice)
            {
                case 1:
                    lista.add(new Obiekcik(losowe.nextInt()));
                    break;
                case 2:
                    System.out.print("Podaj indeks: ");
                    x=scan.nextInt();
                    lista.add(x, new Obiekcik(losowe.nextInt()));
                    break;
                case 3:
                    lista.clear();
                    break;
                case 4:
                    System.out.println(lista.contains(obiekt));
                    break;
                case 5:
                    System.out.println("Podaj minimalna pojemnosc: ");
                    x=scan.nextInt();
                    lista.ensureCapacity(x);
                    System.out.println(lista.minCapacity);
                    break;
                case 6:
                    System.out.println("Podaj indeks: ");
                    x=scan.nextInt();
                    System.out.println(lista.get(x).getNumerek());
                    break;
                case 7:
                    System.out.println(lista.indexOf(obiekt));
                    break;
                case 8:
                    System.out.println("Podaj indeks: ");
                    x=scan.nextInt();
                    lista.set(x, obiekt);
                    System.out.println(lista.get(x).getNumerek());
                    break;
                case 9:
                    lista.remove(lista.indexOf(obiekt));
                    break;
                case 10:
                    System.out.println(lista.size());
                    break;
                case 11:
                    for(int i=0; i<lista.size(); i++)
                    {
                        System.out.print(lista.get(i).getNumerek()+" | ");
                    }
                    System.out.println("\n");
                    break;
                default:
                    System.exit(0);
            }

        }
    }
}
