<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Force database installation</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database initialization in progress</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "WhatsAppDB";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname + ";create=true", "root", "toor");
            
            Statement stmt = con.createStatement();

            try {
                stmt.execute("CREATE SCHEMA " + dbname);
                // schema was created; it will appear into: Other schemas -> "dbname"
            } catch (SQLException e) {
                // schema already exists; do nothing.
            }
            
            /* drop tables if they exist */
            String tablenames[] = new String[]{
                "USERTABLE",  "MESSAGETABLE"
            };
            for (String tablename : tablenames) {
                try {
                    stmt.executeUpdate("DROP TABLE " + dbname + "." + tablename);
                    out.println("<pre> -> DROP TABLE " + dbname + "." + tablename + "<pre>");
                } catch (SQLException e) {
                    // table didn't exist; it is the first time
                }
            }

            /* creating tables */
            String tables[] = new String[]{
                    "CREATE TABLE " + dbname + ".USERTABLE (" +
                    " USERNAME VARCHAR(20) PRIMARY KEY," + 
                    " USERLNAME VARCHAR(20)," + " PHONE VARCHAR(20)," + " EMAIL VARCHAR(20)," + " PWD LONG VARCHAR)",
                    
                    "CREATE TABLE " + dbname + ".MESSAGETABLE (" +
                    " MAINUSER VARCHAR(20)," + " CHATUSER VARCHAR(20)," + " MESSAGES LONG VARCHAR)"
               
            };
            for (String table : tables) {
                try {
                     stmt.execute(table);
                } catch (SQLException e) {
                    out.println("<span class='error'>Error creating table: " + table + "</span>");
                    return;
                }
                out.println("<pre> -> " + table + "<pre>");
            }

            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
             String data[] = new String[]{
            "INSERT INTO " + dbname + ".USERTABLE(USERNAME, USERLNAME, PHONE, EMAIL, PWD) VALUES ('so','so','so','so','a1d9890884c1b4b960c279cfe7554a900d169422d6cec980beef67761487d3b9')",};
             for (String datum : data) {
             if (stmt.executeUpdate(datum) <= 0) {
             out.println("<span class='error'>Error inserting data: " + datum + "</span>");
             return;
             }
             out.println("<pre> -> " + datum + "<pre>");
             }
        %>
         <a  href="index.jsp">Go home</a>
        
    </body>
</html>