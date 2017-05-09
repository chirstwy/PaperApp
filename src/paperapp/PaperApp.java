/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paperapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Administrator
 */
public class PaperApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FileReader reader = null;
        FileWriter writer = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            reader = new FileReader("cx1_topography.pl");
            writer = new FileWriter("xyzp.txt");
            bufferedReader = new BufferedReader(reader);
            bufferedWriter = new BufferedWriter(writer);
            String line = bufferedReader.readLine();
            double step = 1;
            double totalDepth = 20;
            int intervalPoints = (int) (totalDepth / step);
            while (line != null) {
                if (line.startsWith("VRTX")) {
                    String[] strs = line.split("\\s+");
                    double x = Double.parseDouble(strs[2]);
                    double y = Double.parseDouble(strs[3]);
                    double z = Double.parseDouble(strs[4]);
                    for (int i = 0; i < intervalPoints; i++) {
                        String property = strs[2] + " " + strs[3] + " " + (z - i * step) + " " + Math.random() + "\r\n";
                        bufferedWriter.write(property);
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                reader.close();
                bufferedWriter.close();
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
