package com.example.proyectoIntegrador.dao;



import com.example.proyectoIntegrador.bd.BD;
import com.example.proyectoIntegrador.domain.Domicilio;
import com.example.proyectoIntegrador.domain.Odontologo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo>{
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("INSERT INTO ODONTOLOGOS (" +
                    "NOMBRE, APELLIDO , MATRICULA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);//aca vemos el id autoincremental
            ps.setString(1, odontologo.getNombre());
            ps.setString(2, odontologo.getApellido());
            ps.setString(3, odontologo.getMatricula());
            ps.execute();
            ResultSet clave= ps.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));//esta es diferente
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
        return odontologo;
    }

    @Override
    public Odontologo buscar(int id) {
        Connection connection=null;
        Odontologo odontologo=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps=connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                odontologo=new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }
//por que a odontologo se usa 4 en buscar?
    @Override
    public void eliminar(int id) {

        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID=?");
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

    @Override
    public Odontologo actualizar(Odontologo odontologo) {

        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=? WHERE ID=?");
            ps.setString(1, odontologo.getNombre());
            ps.setString(2, odontologo.getApellido());
            ps.setString(3, odontologo.getMatricula());
            ps.setInt(4,odontologo.getId());//ID ODONTOLOGO DEL WHERE
            ps.execute();//importante ejecuta la sentencia SQL
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
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection=null;
        List<Odontologo> odontologos=new ArrayList<>();
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){

                odontologos.add(new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4)));//NOS INTERESA COMPLETO
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
        return odontologos;
    }

    @Override
    public Odontologo buscarXCriterioString(String criterio) {
        return null;
    }
}
