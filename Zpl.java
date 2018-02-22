/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpl;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.standard.PrinterName;
/**
 *
 * @author imran
 */
public class Zpl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PrintException {
        // TODO code application logic here
        PrintServiceAttribute printerName = new PrinterName("ZTC-GT800", null);
        //PrintServiceAttribute printerName = new PrinterName("ZDesigner TLP 2844", null);
        AttributeSet attr = new HashPrintServiceAttributeSet(printerName);
        PrintService[] printServiceArray = PrintServiceLookup.lookupPrintServices(null, attr);
        PrintService printService = printServiceArray[0];
        
        //PrintService pservice = ... // acquire print service of your printer
        DocPrintJob job = printService.createPrintJob();  
        //String commands = "^XA\n\r^MNM\n\r^FO050,50\n\r^B8N,100,Y,N\n\r^FD1234567\n\r^FS\n\r^PQ3\n\r^XZ";
        
        String str1="Code 128";
        String str2="Code Text";
        String barcodedata="100000118SRM";
        
        String commands = "^XA" +
                              //"^LH0,0^LL500" +
                              "^FO230,15^AD^FD"+str1+"^FS" +
                              "^FO230,140^AD^FD"+str2+"^FS"+
                              "^FO230,40^BY2" +
                              "^BCN,70,Y,N,N"+
                              "^FD"+barcodedata+"^FS"+
                              "^XZ";
        
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(commands.getBytes(), flavor, null);
        job.print(doc, null);
    }
    
}
