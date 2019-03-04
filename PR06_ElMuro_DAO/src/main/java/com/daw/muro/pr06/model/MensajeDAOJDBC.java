package com.daw.muro.pr06.model;

import com.daw.muro.pr06.model.qualifiers.DAOJdbc;
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
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;


/** JDBC DAO implementation */
@RequestScoped
@DAOJdbc
public class MensajeDAOJDBC implements MensajeDAO{

    private Logger logger=Logger.getLogger(MensajeDAOJDBC.class.getName());
    
    @Resource(lookup = "java:app/jdbc/muro")
    private DataSource ds;
    
    public MensajeDAOJDBC ()  {

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
        List<Mensaje> mensajes=new ArrayList<>();
        try (
            Connection conn=ds.getConnection(); //Obtenemos conexión del pool de conexiones
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(SQL_BUSCATODOS);
           ){
             while (rs.next()) {
                 mensajes.add(mensajeMapper(rs));
             }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
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
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }  //Autoclose resources (jdk>7)
        return insertados==1;
    }
    
    
}
