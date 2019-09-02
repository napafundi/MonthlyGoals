/*
 * The MIT License
 *
 * Copyright 2019 Nick Pafundi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package model;

import java.sql.*;

public class DatabaseManager {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/monthly_goals";
    static final String USER = "root";
    static final String PASS = "Fishycmg11!";
    static Connection conn = null;

    public DatabaseManager() {}

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}