package com.company;

public class PhoneDirectoryEntry implements Comparable<PhoneDirectoryEntry>
{
   private String name;
   private String number;
   
   public PhoneDirectoryEntry(String s, String n)
   {
      name = s;
      number = n;

   }
   
   public String getName()
   {
      return name;
   }
   
   public String getNumber()
   {
       return number;
   }

   @Override
   public int compareTo(PhoneDirectoryEntry other) {
      return name.compareTo(other.name);
   }


}