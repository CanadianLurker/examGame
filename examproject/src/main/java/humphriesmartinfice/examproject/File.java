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
    public final int RECSIZE=162; //CHANGE THIS 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     public void save(String fileName, int record) {
        try {
             RandomAccessFile recordFile = new RandomAccessFile(fileName, "rw");
            recordFile.seek(RECSIZE * record);
            recordFile.writeChars(MainApp.saveInventory());
            recordFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open(String fileName, int record) {

        try {

            RandomAccessFile recordFile = new RandomAccessFile(fileName, "rw");
            recordFile.seek(RECSIZE * record);
            char inventoryS[] = new char[81];
            for (int i = 0; i < inventoryS.length; i++) {
                inventoryS[i] = recordFile.readChar();
            }
            
           

           
            recordFile.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    
    
    
     public void delete(String file, int recordNumber) {
//move the last record from the file to the top and removes the empty space at the end

open(file, numRecord(file) - 1);
save(file, recordNumber);

try {
RandomAccessFile recordFile = new RandomAccessFile(file, "rw");
recordFile.setLength(recordFile.length() - RECSIZE);
recordFile.close();
} catch (IOException ex) {

}
}
public int numRecord(String file) {
int numR =0;
try {
RandomAccessFile recordFile = new RandomAccessFile(file, "r");
numR = (int)(recordFile.length() / RECSIZE);

} catch (Exception ex) { }
return numR;
}

    
    
}
