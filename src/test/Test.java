package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Test {
    
    public static void test {
        System.out.println("Choose a test case");
        System.out.println("actions");
        System.out.println("build");
        System.out.println("ice");
        System.out.println("invalid_commands");
        System.out.println("kergult");
        System.out.println("lose");
        System.out.println("move");
        System.out.println("player_wins");
        System.out.println("plus");
        System.out.println("robot");
        System.out.println("sun");
        System.out.println("teleport");
        System.out.println("ufo");
        boolean testOK = false;
        Scanner caseScan = new Scanner(System.in);
        String testcase = caseScan.nextLine();
        caseScan.close();
        String referencePath = testcase + "_out.txt";
        Scanner inPathScan = new Scanner(System.in);
        System.out.println("Enter the path to the test output file to check: ");
        String inFilePath = inPathScan.nextLine();
        inPathScan.close();
        try {
            List<String> inContent = Files.readAllLines(Paths.get(inFilePath));
            List<String> refContent = Files.readAllLines(Paths.get(referencePath));
            if(inContent == refContent){
                testOK = true;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        if(testOK) {
            System.out.println("Files match. Test passed.");
        }
        else System.out.println("Files do not match. Test failed.");
    }

}
