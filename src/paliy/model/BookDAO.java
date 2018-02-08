package paliy.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import paliy.util.DBUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class BookDAO {


    private static ObservableList<Book> obsBooksList;

    //*******************************
    //SELECT a Book
    //*******************************

    public static ObservableList<Book> searchBookResult(String searchParam) throws SQLException, ClassNotFoundException{

        String selectStmt;
        //Declare a SELECT statement
        if(searchParam.startsWith("#") || searchParam.startsWith("№")){
            selectStmt = "SELECT * FROM book WHERE book_id='"+searchParam.substring(1,searchParam.length())+"'";
        }else {
            selectStmt = "SELECT * FROM book WHERE LOWER (name) Like LOWER ('%" + searchParam + "%')";
        }

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsBook = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getBookFromResultSet method and get employee object
            return getBookFromResultSet(rsBook);
        } catch (SQLException e) {
            System.out.println("While searching an Book with " + searchParam + " name, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    //Use ResultSet from DB as parameter and set Book Object's attributes and return book object.
    private static ObservableList<Book> getBookFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
       ObservableList<Book> foundItems = FXCollections.observableArrayList();
        while (rs.next()) {
            foundItems.add(getBookData(rs));
        }
        return foundItems;
    }

    //*******************************
    //SELECT  Books
    //*******************************
    public static void getAllItemsFromTable() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM book";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsBooks = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getBookList method and get book object
            getBookList(rsBooks);
            //Return book object
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static ObservableList<Book> getObsBooksList() {
        return obsBooksList;
    }

    //Select * from books operation
    private static ObservableList<Book> getBookList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        obsBooksList = FXCollections.observableArrayList();
        while (rs.next()) {
            obsBooksList.add(getBookData(rs));
        }
        return obsBooksList;
    }

    private static Book  getBookData(ResultSet rs)throws SQLException, ClassNotFoundException {

        Book book = new Book();

        book.setBookId(rs.getInt("BOOK_ID"));
        book.setBookName(rs.getString("NAME"));
        book.setAddedDate(rs.getDate("ADDED_DATE"));
        book.setAge(rs.getString("AGE"));
        book.setDescription(rs.getString("DESCRIPTION"));
        book.setWeight(rs.getInt("WEIGHT"));

        book.setRest(rs.getInt("REST"));
        book.setPrice(rs.getInt("PRICE"));
        book.setSalePrice(rs.getInt("SALE_PRICE"));
        book.setTag(rs.getString("TAGS"));

        /*
        // Moved to Description field
        book.setDimensions(rs.getString("DIMENSIONS"));
        book.setLanguage(rs.getString("LANGUAGE"));
        book.setAuthor(rs.getString("AUTHOR"));
        book.setIllustration(rs.getString("ILLUSTRATIONS"));
        book.setSeries(rs.getString("SERIES"));
        book.setYear(rs.getInt("YEAR"));
        book.setCover(rs.getString("COVER"));
        book.setPages(rs.getInt("PAGES"));*/
        return book;
    }

    //*************************************
    //UPDATE book's fields

    // *************************************
    public static void updateBookName (String bookId, String bookName) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE book\n" +
                        "      SET NAME = '" + bookName + "'\n" +
                        "    WHERE BOOK_ID = " + bookId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    public static void updateBookPrice(String bookId, String price)  throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE book\n" +
                        "      SET PRICE = '" + price + "'\n" +
                        "    WHERE BOOK_ID = " + bookId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void updateBookSalePrice(String bookId, String salePrice)  throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE book\n" +
                        "      SET SALE_PRICE = '" + salePrice + "'\n" +
                        "    WHERE BOOK_ID = " + bookId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void updateBookRest (String bookId, String rest) throws SQLException, ClassNotFoundException {
           //Declare a UPDATE statement
           String updateStmt =
                   "BEGIN\n" +
                           "   UPDATE book\n" +
                           "      SET REST = '" + rest + "'\n" +
                           "    WHERE BOOK_ID = " + bookId + ";\n" +
                           "   COMMIT;\n" +
                           "END;";
           //Execute UPDATE operation
           try {
               DBUtil.dbExecuteUpdate(updateStmt);
           } catch (SQLException e) {
               System.out.print("Error occurred while UPDATE Operation: " + e);
               throw e;
           }
    }

    public static void updateBookDescript (String bookId, String descr) throws SQLException, ClassNotFoundException {
           //Declare a UPDATE statement
           String updateStmt =
                   "BEGIN\n" +
                           "   UPDATE book\n" +
                           "      SET DESCRIPTION = '" + descr + "'\n" +
                           "    WHERE BOOK_ID = " + bookId + ";\n" +
                           "   COMMIT;\n" +
                           "END;";
           //Execute UPDATE operation
           try {
               DBUtil.dbExecuteUpdate(updateStmt);
           } catch (SQLException e) {
               System.out.print("Error occurred while UPDATE Operation: " + e);
               throw e;
           }
    }

    public static void updateBookImage(String bookName, File file)  {
           //Declare a UPDATE statement
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String updateStmt =
                   "BEGIN\n" +
                           "   UPDATE book\n" +
                           "      SET IMAGE = ? WHERE NAME ='" + bookName + "';\n" +
                           "   COMMIT;\n" +
                           "END;";
           //Execute UPDATE operation
        PreparedStatement ps = null;
        try {
            Connection conn = DBUtil.dbGetConnection();
            ps = conn.prepareStatement(updateStmt);
            ps.setBinaryStream(1, fis, file.length());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
            DBUtil.dbDisconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //*************************************

    //DELETE books
    //*************************************
    public static void deleteBooksByName(List<String> bookNames) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        StringBuilder names = new StringBuilder(bookNames.size());
        for(String s : bookNames){
                names.append("'").append(s).append("',");
        }
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM book\n" +
                        "         WHERE name in ("+ names.deleteCharAt(names.length() -1)+");\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Book Operation: " + e);
            throw e;
        }
    }
    //*************************************

    //INSERT new book
    //*************************************
    public static boolean insertBook(String name, String age, String descrip, String weight, String price, String sale_price, String rest, String tags) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO book\n" +
                        "(Book_ID, NAME, ADDED_DATE, AGE, DESCRIPTION, WEIGHT, REST, PRICE, SALE_PRICE, TAGS)\n" +
                        "VALUES\n" +
                        "(book_id_seq.nextval, '" + name +
                        "'," + " SYSDATE, '" + age +
                        "','" + descrip +
                        "','" + weight +
                        "','" + rest +
                        "','" + price +
                        "','" + sale_price +
                        "','"+tags +
                        "');\n" +
                        "END;";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            return false;
        }
        return true;
    }

//INSERT new book
    //*************************************
    public static boolean insertBook(String name, String age, String descrip, String weight, String price, String sale_price, String rest, String tags, File file) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                        "INSERT INTO book\n" +
                        "(Book_ID, NAME, ADDED_DATE, AGE, DESCRIPTION, WEIGHT, REST, PRICE, SALE_PRICE, TAGS, IMAGE)\n" +
                        "VALUES\n" +
                        "(book_id_seq.nextval, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?)";

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Execute UPDATE operation
        PreparedStatement ps = null;
        try {
            Connection conn = DBUtil.dbGetConnection();
            ps = conn.prepareStatement(updateStmt);
            ps.setString(1,name);
            ps.setString(2,age);
            ps.setString(3,descrip);
            ps.setInt(4, Integer.parseInt(weight));
            ps.setInt(5, Integer.parseInt(rest));
            ps.setInt(6, Integer.parseInt(price));
            ps.setInt(7, Integer.parseInt(sale_price));
            ps.setString(8, tags);
            ps.setBinaryStream(9, fis, file.length());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
            DBUtil.dbDisconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Image getImageByName(String bookName) throws SQLException  {
        try ( PreparedStatement ps = DBUtil.dbGetConnection().prepareStatement("SELECT image FROM book WHERE name = ?")){
            ps.setString(1, bookName);
            ResultSet rs = ps.executeQuery();
            Image img = null ;
            if (rs.next()) {
                Blob blob = rs.getBlob("image");
                if(blob != null){
                    InputStream is = blob.getBinaryStream();
                    img = new Image(is) ; // false = no background loading
                    is.close();
                }else {
                    System.out.println("Warning: no image in table!");
                }
            }
            rs.close();
            return img ;
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
    }

    public static void deleteBookImgByName(String name) throws ClassNotFoundException {
        String delStmt = "UPDATE book SET image=null WHERE name='" + name+"'";
        try {
            DBUtil.dbExecuteUpdate(delStmt);
            System.out.print("Book image was Deleted! ");
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Image Operation: " + e);
        }
    }
}
