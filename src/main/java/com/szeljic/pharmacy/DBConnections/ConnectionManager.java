package com.szeljic.pharmacy.DBConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

   static Connection conn;
   static String url;

   public static Connection getConnection()
   {
      try
      {
         String url = "jdbc:mysql://localhost/pharmacy";
         Class.forName("com.mysql.jdbc.Driver");

         try
         {
            conn = DriverManager.getConnection(url, "root", "stralezelja");
         }
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }
      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

      return conn;
   }
}
