package com.daw.muro.model;

import com.daw.muro.Mensaje;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/** JDBC DAO implementation */
@Dependent
@MensajeDAOJdbcBean
public class MensajeDAOJDBC implements MensajeDAO, Serializable{

    @Resource(name = "jdbc/muro")
    private DataSource ds;
    
    public MensajeDAOJDBC ()  {
//        Context context;
//
//        try {
//            context = new InitialContext(); //Accedemos al contenedor de Servlets
//            ds = (DataSource) context.lookup("java:comp/env/jdbc/muro"); //Localizamos el pool
//        } catch (NamingException ex) {
//            Logger.getLogger(MensajeDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
    
       /**Recupera un Mensaje a partir del registro actual del RS (MAPPING)*/
    private static Mensaje mensajeMapper(ResultSet rs) throws SQLException {
        Mensaje m;
        m=new Mensaje(  rs.getInt("id"),
                        rs.getString("identificador"),
                        rs.getString("mensaje"));
        return m;
    }  
    
    @Override
    public List<Mensaje> buscaTodos() {
        String SQL_BUSCATODOS="Select * from Mensajes";
        List<Mensaje> mensajes=new ArrayList<Mensaje>();
        try (
            Connection conn=ds.getConnection(); //Obtenemos conexión del pool de conexiones
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(SQL_BUSCATODOS);
           ){
             while (rs.next()) {
                 mensajes.add(mensajeMapper(rs));
             }
        } catch (SQLException ex) {
            Logger.getLogger(MensajeDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajes;
    }

    @Override
    public boolean nuevoMensaje(Mensaje m) {
        String SQL_INSERT="insert into Mensajes (identificador,mensaje) values(?,?)";
        Integer insertados=0;
        try (Connection conn=ds.getConnection();
             PreparedStatement stmn=conn.prepareStatement(SQL_INSERT)) {

            stmn.setString(1, m.getIdentificador());
            stmn.setString(2, m.getMensaje());
            insertados=stmn.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("MensajeDAOJDBC").log(Level.SEVERE, ex.getMessage(), ex);
        }  //Autoclose resources (jdk>7)
        return insertados==1;
    }
    
    
}
