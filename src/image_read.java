import java.io.*;
import java.sql.*;
import java.util.Date;

public class image_read {


    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public ByteArrayInputStream binImage;


    public void read() throws Exception {

            // This will load the MySQL driver, each DB has its own driver
            // Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/image?"
                                                        + "user=root&password=");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from image.person");
            writeResultSet(resultSet);


    }
    private void writeResultSet(ResultSet resultSet) throws SQLException, IOException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {

            InputStream in = resultSet.getBinaryStream(4);

            // Data Processing
            byte[] buff = new byte[8000];
            int bytesRead = 0;
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            while((bytesRead = in.read(buff)) != -1) {
                bao.write(buff, 0, bytesRead);
            }
            byte[] data = bao.toByteArray();
            ByteArrayInputStream bin = new ByteArrayInputStream(data);
            binImage = bin;
            System.out.println(bin);
        }

    }

//    public InputStream image(){
//        return imgStream;
//    }
}