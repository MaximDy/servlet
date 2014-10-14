package com.academysmart.database;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {
    private static volatile EmployeeDatabase instance = null;
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = ""; //login,password removed
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

    private EmployeeDatabase() {}

    public static EmployeeDatabase getDatabase(){
        if (instance == null){
            synchronized (EmployeeDatabase.class)
            {
                if (instance == null){
                    instance = new EmployeeDatabase();
                }
            }
        }
        return instance;
    }

    private Connection getConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);


            try {
                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return dbConnection;
    }

    public void checkCreateTable() {

        String createTable = "CREATE TABLE EMPLOYEE \n" +
                "(ID NUMBER(8, 0) NOT NULL PRIMARY KEY , \n" +
                "F_NAME VARCHAR2(50 BYTE) NOT NULL, \n" +
                "L_NAME VARCHAR2(50 BYTE) NOT NULL, \n" +
                "EMAIL VARCHAR2(50 BYTE) NOT NULL UNIQUE)";
        String createSequince = "create sequence EMPLOYEE_SEQ MINVALUE 1 MAXVALUE 99999 start with 1 increment by 1 nocache";
        String createTrigger = "CREATE OR REPLACE TRIGGER EMPLOYEE_TRG\n" +
                "  BEFORE INSERT\n" +
                "  ON EMPLOYEE\n" +
                "  FOR EACH ROW\n" +
                "  WHEN (NEW.ID IS NULL)\n" +
                "DECLARE\n" +
                "  V_ID EMPLOYEE.ID%TYPE;\n" +
                "BEGIN \n" +
                "  SELECT EMPLOYEE_SEQ.NEXTVAL INTO V_ID FROM DUAL;\n" +
                "  :NEW.ID := V_ID;\n" +
                "END EMPLOYEE_TRG;";

        Connection dbC = null;
        Statement st = null;
        try {
            dbC = getConnection();
            st = dbC.createStatement();
            DatabaseMetaData dbm = dbC.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "EMPLOYEE", null);
            if (!tables.next()) {
                st.execute(createTable);
                st.execute(createSequince);
                st.execute(createTrigger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (dbC != null) {
                try {
                    dbC.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Employee> getData() {
        List<Employee> rs = new ArrayList<>();
        String selectAll = "SELECT * FROM EMPLOYEE";
        Connection dbC = null;
        Statement st = null;
        try {
            dbC = getConnection();
            st = dbC.createStatement();
            ResultSet set = st.executeQuery(selectAll);
            while (set.next()) {
                Employee employee = new Employee(set.getString("F_NAME"), set.getString("L_NAME"), set.getString("EMAIL"));
                employee.setId(Integer.parseInt(set.getString("ID")));
                rs.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectEmailException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (dbC != null) {
                try {
                    dbC.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rs;
    }

    public void insertData(Employee employee) {
        Connection dbc = null;
        Statement st = null;
        try {
            dbc = getConnection();
            st = dbc.createStatement();
            if (!getData().contains(employee)) {
                String insertTable = "INSERT INTO EMPLOYEE " +
                        "(ID, F_NAME, L_NAME, EMAIL) " + "VALUES (null,'" + employee.getFname()
                        + "','" + employee.getLname() + "','" + employee.getEmail() + "')";
                st.executeUpdate(insertTable);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (dbc != null) {
                try {
                    dbc.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
