package it.unibo.tw.db; 

import java.sql.*;
import java.util.Scanner;

// Classe di prova utile per validare la connessione al DBMS

class Test{

    static {
        try {
            // register the driver with DriverManager
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public static void main(String argv[]) {
		Connection con = null;
       
		Scanner scan = new Scanner(System.in);
        // Per leggere una stringa:
        // String input = scan.nextLine();
        //
        // Per leggere un intero:
        // int ival = scan.nextInt();
        //
        // Per leggere un float:
        // float fval = scan.nextFloat();
        //
        // Per leggere un byte:
        // byte bval = scan.nextByte();

        // URL is jdbc:db2:dbname
        String url = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

        try {
            if (argv.length == 2) {
                String userid = argv[0];
                String passwd = argv[1];

                // connect with user-provided username and password
                con = DriverManager.getConnection(url, userid, passwd);
            }
            else {
                System.out.println("\nUsage: java Esame [username password]\n");
                System.exit(0);
            }

        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
