package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayBasedPD implements PhoneDirectory {
    // phone directory and related items
    private final int MAXENTRIES = 200; // maximum number of entries in phone directory
    private PhoneDirectoryEntry[] phoneDirectory = new PhoneDirectoryEntry[MAXENTRIES];
    private int numberOfEntries = 0; // number of entries
    private int currentEntry = -1; //index of entry currently displayed

    public void loadData(String filename) {
        // TODO Auto-generated method stub

        try {
            File inputFile = new File(filename);
            Scanner in = new Scanner(inputFile);
            String name;
            String number;
            int index = 0;

            while (in.hasNext()){
                name = in.next();
                number = in.next();
                phoneDirectory[index] = new PhoneDirectoryEntry(name, number);
                index++;
            }

            numberOfEntries = index;
            //now call Arrays.sort() to sort phoneDirectory
            sortPhoneBookByName();
            in.close();

        } catch (IOException exc) {
            System.out.println("File does not exist");
        }
    }

    public void saveData(String filename) {
        // TODO Auto-generated method stub
        try {
            File outputFile = new File(filename);
            Scanner out = new Scanner(outputFile);
            int index = 0;

/*            while (in.hasNext()){
                name = in.next();
                number = in.next();
                phoneDirectory[index] = new PhoneDirectoryEntry(name, number);
                index++;
                */
            while(index > numberOfEntries){
                out.next(phoneDirectory[index].getName());
                out.next(phoneDirectory[index].getNumber());
                index++;
            }
            out.close();
            System.out.println("got here");

        } catch (IOException exc) {
            System.out.println("File does not exist");
        }
    }

    public void addEntry(String name, String number) {
        // TODO Auto-generated method stub
        PhoneDirectoryEntry pde = new PhoneDirectoryEntry(name, number);
        if(numberOfEntries<MAXENTRIES){
            phoneDirectory[numberOfEntries] = pde;
            numberOfEntries++;
            sortPhoneBookByName();

        }
        else{
            JOptionPane.showMessageDialog(null, "phone book is full, please buy more space.");
        }



    }

    public PhoneDirectoryEntry getFirst() {
        // TODO Auto-generated method stub
        if (numberOfEntries != 0) {
            currentEntry = 0;
            return phoneDirectory[0];
        } else
            return null;
    }

    public PhoneDirectoryEntry getNext() {
        // TODO Auto-generated method stub
        if (currentEntry != numberOfEntries - 1) {
            currentEntry++;
            return phoneDirectory[currentEntry];
        } else
            return null;

    }

    public PhoneDirectoryEntry getPrevious() {
        // TODO Auto-generated method stub
        if (currentEntry != 0) {
            currentEntry--;
            return phoneDirectory[currentEntry];
        } else
            return null;

    }

    public String search(String name) {
        // TODO Auto-generated method stub
        PhoneDirectoryEntry pde = new PhoneDirectoryEntry(name, null);
        int num = Arrays.binarySearch(phoneDirectory,0,numberOfEntries,pde);
        if(num > 0) {
            return phoneDirectory[num].getNumber();
        }
        else return null;

    }

    public void sortPhoneBookByName(){
        Arrays.sort(phoneDirectory, 0, numberOfEntries);
    }
}
