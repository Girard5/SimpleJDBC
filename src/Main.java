import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File input = new File("Test.txt");

        readIn(input);


    }



    static void readIn(File buff){


        String db_con = "";
        String username = "";
        String password = "";

        PreparedStatement preparedStatement = null;

        String insert = "INSERT INTO FINALS19"
                + "(Employee ID, First Name, Last Name, Department Name) VALUES"
                + "(?,?,?,?)";

        try {
            Connection con = DriverManager.getConnection(db_con, username, password);
            con.setAutoCommit(false);


            try (BufferedReader br = new BufferedReader(new FileReader(buff))) {

                Scanner sc = new Scanner(br).useDelimiter("\\s*,\\s*");


                while(br.readLine() != null) {

                    preparedStatement = con.prepareStatement(insert);

                    preparedStatement.setInt(1, Integer.parseInt(sc.next()));
                    preparedStatement.setString(2,sc.next());
                    preparedStatement.setString(3,sc.next());
                    preparedStatement.setString(4,sc.next());

                    preparedStatement.executeUpdate();

                }

                sc.close();
                preparedStatement.close();


            } catch (IOException e) {
                System.out.println(e.getCause());
            }


        } catch (SQLException s) {
            System.out.println(s.getErrorCode());
        }
    }
}
