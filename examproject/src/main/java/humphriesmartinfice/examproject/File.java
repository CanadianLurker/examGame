/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humphriesmartinfice.examproject;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author darbym26
 */
public class File {

    public final int RECSIZE = 279;
    String username;
    String usernameTemp;
    String inventory;

    File() {
    }

    public void setUsername() {
        StringBuffer temp = new StringBuffer(MainApp.username);
        temp.setLength(30);
        username = temp.toString();
    }

    public String getUsername() {
        return username.trim();
    }

    public void save(String fileName, int record) {
        setUsername();
        try {
            RandomAccessFile recordFile = new RandomAccessFile(fileName, "rw");
            recordFile.seek(RECSIZE * record);
            recordFile.writeChars(username);
            recordFile.writeChars(MainApp.saveInventory());
            recordFile.writeInt(MainApp.level);
            recordFile.writeInt(MainApp.DEX);
            recordFile.writeInt(MainApp.INT);
            recordFile.writeInt(MainApp.STR);
            recordFile.writeInt(MainApp.cigs);
            recordFile.writeDouble(MainApp.health);
            recordFile.writeDouble(MainApp.mana);
            recordFile.writeDouble(MainApp.EXP);
            recordFile.writeBoolean(MainApp.keyB);
            recordFile.writeBoolean(MainApp.key);
            recordFile.writeBoolean(MainApp.ABoss);
            recordFile.writeBoolean(MainApp.WItem);
            recordFile.writeBoolean(MainApp.Warden);
            recordFile.writeInt(MainApp.xplace);
            recordFile.writeInt(MainApp.yplace);
            recordFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open(String fileName, int record) {

        try {

            RandomAccessFile recordFile = new RandomAccessFile(fileName, "rw");
            recordFile.seek(RECSIZE * record);
            char user[] = new char[30];
            for (int i = 0; i < user.length; i++) {
                user[i] = recordFile.readChar();
            }
            username = new String(user);
            System.out.println("start" + username);
            MainApp.username = username.trim();
            char inventoryS[] = new char[81];
            for (int i = 0; i < inventoryS.length; i++) {
                inventoryS[i] = recordFile.readChar();
            }
            inventory = new String(inventoryS);
            System.out.println(inventory);

            MainApp.addToInventory(inventory);
            System.out.println(MainApp.inventory.length);
            for (int i = 0; i < MainApp.inventory.length; i++) {
                MainApp.inventory[i].setAttack1();
                MainApp.inventory[i].setAttack2();
                MainApp.inventory[i].setAttack3();
                MainApp.inventory[i].setAttack4();
                MainApp.inventory[i].setCost();
                MainApp.inventory[i].setSDamage();
            }

            MainApp.level = recordFile.readInt();
            System.out.println(MainApp.level);

            MainApp.DEX = recordFile.readInt();
            System.out.println(MainApp.DEX);

            MainApp.INT = recordFile.readInt();
            System.out.println(MainApp.INT);

            MainApp.STR = recordFile.readInt();
            System.out.println(MainApp.STR);

            MainApp.cigs = recordFile.readInt();
            System.out.println(MainApp.cigs);
            MainApp.health = recordFile.readDouble();
            MainApp.mana = recordFile.readDouble();
            MainApp.EXP = recordFile.readDouble();
            MainApp.keyB = recordFile.readBoolean();
            MainApp.key = recordFile.readBoolean();
            MainApp.ABoss = recordFile.readBoolean();
            MainApp.WItem = recordFile.readBoolean();
            MainApp.Warden = recordFile.readBoolean();
            MainApp.xplace = recordFile.readInt();
            MainApp.yplace = recordFile.readInt();
            MainApp.setHealthMAX();
            MainApp.setManaMAX();
            MainApp.setEXPNeeded();

            recordFile.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String openUser(String fileName, int record) {
        try {

            RandomAccessFile recordFile = new RandomAccessFile(fileName, "rw");
            recordFile.seek(RECSIZE * record);
            char user[] = new char[30];
            for (int i = 0; i < user.length; i++) {
                user[i] = recordFile.readChar();
            }
            usernameTemp = new String(user);

            recordFile.close();
            return usernameTemp.trim();
        } catch (IOException ex) {

            return null;
        }

    }

    public void delete(String file, int recordNumber) {
//move the last record from the file to the top and removes the empty space at the end

        open(file, numRecord(file) - 1);
        save(file, recordNumber);

        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "rw");
            recordFile.setLength(recordFile.length() - RECSIZE);
            for (int i = 0; i < 9; i++) {
                MainApp.inventory[i] = null;
            }
            MainApp.level = 1;
            MainApp.DEX = 1;
            MainApp.INT = 1;
            MainApp.STR = 1;
            MainApp.cigs = 0;
            MainApp.health = 17;
            MainApp.mana = 12;
            MainApp.EXP = 0;
            MainApp.key = false;
            MainApp.keyB = false;
            MainApp.ABoss = false;
            MainApp.WItem = false;
            MainApp.Warden = false;
            recordFile.close();
        } catch (IOException ex) {

        }
    }

    public int numRecord(String file) {
        int numR = 0;
        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "r");
            numR = (int) (recordFile.length() / RECSIZE);
            System.out.println("" + recordFile.length());
        } catch (Exception ex) {
        }
        return numR;
    }

}
