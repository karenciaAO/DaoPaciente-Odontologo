package com.example.proyectoIntegrador.dao;
import com.example.proyectoIntegrador.bd.BD;
import com.example.proyectoIntegrador.domain.Domicilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio> {
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        //para conectar a la BD
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("INSERT INTO DOMICILIOS (" +
                    "CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);//aca vemos el id autoincremental
            ps.setString(1, domicilio.getCalle());
            ps.setString(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.execute();
            ResultSet clave= ps.getGeneratedKeys();
            while (clave.next()){
                domicilio.setId(clave.getInt(1));//esta es diferente
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        //se ejecuta siempre el finally para close por seguridad
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscar(int id) {
        Connection connection=null;
        Domicilio domicilio=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                domicilio=new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            //imprimir los error que encuentra
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public void eliminar(int id) {
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID=?");
            ps.setInt(1,id);
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
    //en este caso no necesitamos Domicilio domicilio=null; pues nunca regresamos el domicilio soloe es un void

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?");
            ps.setString(1, domicilio.getCalle());
            ps.setString(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.setInt(5,domicilio.getId());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        Connection connection=null;
        List<Domicilio> domicilios=new ArrayList<>();
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM DOMICILIOS");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                //Domicilio domicilio=new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),
                //        rs.getString(4),rs.getString(5));
                //domicilios.add(domicilio);
                //Lista de domicilios
                //como sta el array- vacio
                //despues de la primera vuelta- dom1
                //despues de la segunda vuelta- dom1, dom2
                //n hasta que tabla este completa - dom1, dom2, .... domn
                domicilios.add(new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),
                                rs.getString(4),rs.getString(5)));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilios;
    }

    @Override
    public Domicilio buscarXCriterioString(String criterio) {
        return null;
    }
}
