package agh.ics.oop;

import java.util.Scanner;

public class World {
    public static void main(String[] args){
        System.out.println("system wystartował");
        String[] lista = new String[4];
        Scanner x = new Scanner(System.in);
        for (int i=0; i<lista.length; i+=1){
            lista[i] = x.next();
        }
        run(lista);
        System.out.println("system zakończył działanie");
    }
    static void run(String[] a){
        System.out.println("zwierzak idzie do przodu");
        int i = a.length;
        for (int x=0; x<i-1; x+=1){
            System.out.println(a[x] + ",");
        }
        System.out.println(a[i-1]);
    }
}
