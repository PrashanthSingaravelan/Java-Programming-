/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class FileReaderDemo {
    public static void main(String args[]) throws IOException {
	FileReader fr = new FileReader("D:/fff.txt");
	BufferedReader br = new BufferedReader(fr);
	String s;
	while((s = br.readLine()) != null) {
		System.out.println(s);
	}
	fr.close();
	}
}
