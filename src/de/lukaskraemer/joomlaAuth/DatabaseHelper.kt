package de.lukaskraemer.joomlaAuth

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*


class DatabaseHelper {

    companion object {
        lateinit var  conn: Connection
        var tablename = ""

        fun connect(username: String, password: String, database: String, tablename: String){
            DatabaseHelper.tablename = tablename

            val connectionProps = Properties()
            connectionProps["user"] = username
            connectionProps["password"] = password
            try {
                Class.forName("com.mysql.jdbc.Driver")//.newInstance()
                conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/" +
                            database,
                            connectionProps)

            } catch (ex: SQLException) {
                ex.printStackTrace()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        fun loadUser(username: String): Triple<Boolean, Int, String> {
            var result: ResultSet? = null
            var check = false
            var id = 0
            var password = ""
            try {
                val ps = conn!!.prepareStatement("SELECT id, username, password from $tablename WHERE username like ?")
                ps.setString(1, username)
                result = ps.executeQuery()

                check = result.isBeforeFirst
                if(check){
                    id= result.getInt("id")
                    password = result.getString("password")
                }
                result.close()
                ps.close()
            } catch (ex: SQLException) {}
            return Triple(check, id,password)
        }
    }
}