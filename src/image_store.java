import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class image_store  {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void Store() {
        try

        {
            File image = new File("H:\\Project\\Saleh Project (image processing)\\Testers\\ImageMatchin\\src\\pic1.jpeg");
            FileInputStream fis = new FileInputStream(image);
            connect = DriverManager.getConnection("jdbc:mysql://localhost/image?"
                                                            + "user=root&password=");

            // Statements allow to issue SQL queries to the database
            String sql="insert into person (image) values (?)";
            statement = connect.createStatement();
            preparedStatement=connect.prepareStatement(sql);
            preparedStatement.setBinaryStream (1, fis, (int) image.length() );
            preparedStatement.executeUpdate();

        } catch (Exception e) {
                e.getMessage();

        }
    }
}
