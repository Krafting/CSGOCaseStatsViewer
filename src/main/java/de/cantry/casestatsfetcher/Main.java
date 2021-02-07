package de.cantry.casestatsfetcher;

import javax.sound.midi.Soundbank;
import java.lang.instrument.ClassDefinition;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu");
        System.out.println("1. -> Dump inventory history (can take some time)");
        System.out.println("2. -> Analyse dumped inventory history");
        System.out.println("Type 1 or 2 and press enter.");

        int input = Integer.parseInt(scanner.nextLine());
        Dumper dumper = new Dumper();
        switch (input) {
            case 1:
                System.out.println("Why does it need your cookies?");
                System.out.println("It needs your cookies to request your inventory history from steamcommunity.com/my/inventoryhistory");
                System.out.println("If you dont know how to get your cookies check the readme for instructions");
                System.out.println("Please paste your cookies and press enter");
                String cookies = scanner.nextLine();
                if (!cookies.startsWith("Cookies:")) {
                    cookies = "Cookies: " + cookies;
                }
                dumper.setCookies(cookies);
                long latestDump = dumper.lookForOldDumps();
                boolean startFromNow = false;
                if(latestDump != 99999999999L){
                    System.out.println("Older dumps found");
                    System.out.println("1. -> Continue from old dumps");
                    System.out.println("2. -> Start from the beginning");
                    System.out.println("Type 1 or 2 and press enter.");
                    if (Integer.parseInt(scanner.nextLine()) == 2) {
                        latestDump = 99999999999L;
                        startFromNow = true;
                    }
                }
                dumper.dumpFromTime(latestDump,startFromNow);
                System.out.println("You can close this now and run the execute.bat again");
                break;
            case 2:
                Analyser analyser = new Analyser();
                analyser.outPutStats(dumper.getDumpDirectory());
                break;
        }


    }

}
