/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;

import com.bdii.stimfx.aplicacion.Categoria;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;


public class DAOOpUnicas extends AbstractDAO{

    public DAOOpUnicas(Connection conexion, FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(password.getBytes());

            // bytes hasheados
            byte[] hashedBytes = md.digest();

            // Converison a base 64
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
            //le quitamos el '=' que esta al final del base64 ya que no aporta nada
            String finalPassword = hashedPassword.substring(0, hashedPassword.length() - 1);
            return finalPassword;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public void hashAllPasswords() {
        Connection con;
        PreparedStatement stmUsuario=null;

        con=super.getConexion();
        // Consulta para obtener todos los usuarios
        String selectQuery = "SELECT id, contraseña FROM usuario";
        try {
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            ResultSet resultSet = selectStmt.executeQuery();
            // Iterar sobre los resultados
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String oldPassword = resultSet.getString("password");
                // Hashear la contraseña antigua
                String hashedPassword = hashPassword(oldPassword);
                // Actualizar la contraseña hasheada en la base de datos
                String updateQuery = "UPDATE usuario SET contraseña = ? WHERE id = ?";
                try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, hashedPassword);
                    updateStmt.setInt(2, userId);
                    updateStmt.executeUpdate();
                    System.out.println("Contraseña hasheada actualizada para el usuario con ID: " + userId);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}