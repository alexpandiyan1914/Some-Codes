import java.sql.*;
import java.util.Scanner;

public class JDBC_Connect {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/testdb";
        String name = "root";
        String password = "*******";

        try{
            Connection con = DriverManager.getConnection(url,name,password);
            System.out.println("MySQL connected Successfully");

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Your Name :");
            String Name = sc.nextLine();

            System.out.print("Enter you mail id :");
            String Email = sc.nextLine();

            String query = "INSERT INTO users (name, email) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Name);
            ps.setString(2, Email);

            ps.executeUpdate();

            System.out.println("User Added Successfully");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            System.out.println("User Table");

            while(rs.next()){
                System.out.println(rs.getInt("id")+" | "+
                        rs.getString("name")+" | "+
                        rs.getString("email"));
            }

            rs.close();
            st.close();
            ps.close();
            con.close();
            sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
