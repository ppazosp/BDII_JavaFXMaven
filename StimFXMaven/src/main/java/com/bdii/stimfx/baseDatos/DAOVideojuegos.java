/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.baseDatos;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;

import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Videojuego;
import com.bdii.stimfx.aplicacion.Editor;
import com.bdii.stimfx.aplicacion.DLC;


import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAOVideojuegos extends AbstractDAO{
    public DAOVideojuegos (Connection conexion, FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public void insertarVideojuego(Videojuego v){
        Connection con;
        PreparedStatement stmVideojuego=null;
        
        con=super.getConexion();
        
        try {
            stmVideojuego=con.prepareStatement("insert into videojuego(id, nombre, fechasubida, id_usreditor, descripcion) "+
                                            "values (?,?,?,?,?)");
            // Obtener la fecha actual como un objeto java.sql.Date
            Date fechaActual = new Date(System.currentTimeMillis());

            v.setFechaSubida(fechaActual);
            
            stmVideojuego.setInt(1, v.getId());
            stmVideojuego.setString(2, v.getNombre());
            stmVideojuego.setDate(3, fechaActual);
            stmVideojuego.setString(4, v.getEditor().getId());
            stmVideojuego.setString(5, v.getDescripcion());
            stmVideojuego.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void borrarVideojuego(int id){
                Connection con;
        PreparedStatement stmVideojuego=null;
        
        con=super.getConexion();
        
        try{
            stmVideojuego=con.prepareStatement("delete from videojuego where id = ?");
            stmVideojuego.setInt(1, id);
            stmVideojuego.executeUpdate();
        
        }catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public Videojuego consultarVideojuego(Integer idVideojuego){  // Sirve para la transaccion de obtener el videojuego asociado a un dlc y tmbn para obtener videojuegos asociados a una cartegoria
        Videojuego videojuego=null;
        Connection con;
        PreparedStatement stmVideojuego=null;
        ResultSet rsVideojuego;
        
        con=this.getConexion();
        
        String consulta = "select * "+
                            "from videojuego "+
                            "where id = ?";
        
        
        try{
            stmVideojuego=con.prepareStatement(consulta);
            stmVideojuego.setInt(1, idVideojuego);
            rsVideojuego=stmVideojuego.executeQuery();
            if (rsVideojuego.next()){
                videojuego = new Videojuego(rsVideojuego.getInt("id"), rsVideojuego.getString("nombre"),
                                                rsVideojuego.getDate("fechaSubida"), rsVideojuego.getString("descripcion"), rsVideojuego.getDouble("precio"));
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return videojuego;
    }

    public Videojuego consultarVideojuego(String nombreVideojuego) {
        Videojuego videojuego=null;
        Connection con;
        PreparedStatement stmVideojuego=null;
        ResultSet rsVideojuego;
        ResultSet rsEditor;

        con=this.getConexion();

        String consulta = "SELECT v.id, v.nombre, v.descripcion, v.id_usreditor, v.fechasubida, v.precio, v.imagen, v.banner, COUNT(c.id_videojuego) AS totalCompras\n" +
                "FROM videojuego AS v\n" +
                "LEFT JOIN comprar AS c ON v.id = c.id_videojuego\n" +
                "WHERE v.nombre = ?\n" +
                "GROUP BY v.id, v.nombre, v.descripcion, v.id_usreditor, v.fechasubida, v.precio, v.imagen, v.banner;";

        try{
            stmVideojuego=con.prepareStatement(consulta);
            stmVideojuego.setString(1, nombreVideojuego);
            rsVideojuego=stmVideojuego.executeQuery();
            if(rsVideojuego.next())
            {
                videojuego = new Videojuego(rsVideojuego.getInt("id"), rsVideojuego.getString("nombre"),
                        rsVideojuego.getDate("fechasubida"), rsVideojuego.getString("descripcion"),
                        rsVideojuego.getDouble("precio"), FachadaAplicacion.bytesToImage(rsVideojuego.getBytes("imagen")),
                        FachadaAplicacion.bytesToImage(rsVideojuego.getBytes("banner")));

                String consulta1= "  select * from usuario u " +
                        "  where id= ?;";
                try {
                    stmVideojuego = con.prepareStatement(consulta1);
                    stmVideojuego.setString(1, rsVideojuego.getString("id_usreditor"));
                    rsEditor = stmVideojuego.executeQuery();
                    while (rsEditor.next()) {
                        Editor editor = new Editor(rsEditor.getString("id"), rsEditor.getString("nombre"), rsEditor.getString("contraseña"),
                                null, rsEditor.getString("email"));
                        videojuego.setEditor(editor);
                    }
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                    FachadaAplicacion.muestraExcepcion(e.getMessage());
                }
            }
        }catch (SQLException e){
                System.out.println(e.getMessage());
                FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return videojuego;
    }
        
    public List<Videojuego> consultaVideojuegos(String nombre){
        Videojuego libroActual;
        Connection con;
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        PreparedStatement stmDLC=null;
        ResultSet rsDLC;
        List<Videojuego> resultado = new ArrayList<Videojuego>();
        con=this.getConexion();
        //Cambiar consulta para q devulva tb nombre de usuario
        String consulta = "select * " +
                                         "from videojuego as v join editor e on e.id= v.id_usreditor join usuario u on u.id = e.id and "+
                                         " v.nombre like ?";

        try  {
        stmCatalogo= con.prepareStatement(consulta);
        stmCatalogo.setString(1, "%"+nombre+"%");
        rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            int id=rsCatalogo.getInt("id");
            Videojuego videojuego = new Videojuego(id, rsCatalogo.getString("nombre"),
                        rsCatalogo.getDate("fechaSubida"),
                    rsCatalogo.getString("descripcion"), rsCatalogo.getDouble("precio"),
                    FachadaAplicacion.bytesToImage(rsCatalogo.getBytes("imagen")), FachadaAplicacion.bytesToImage(rsCatalogo.getBytes("banner")));
            Editor editor = new Editor(rsCatalogo.getString("id_usreditor"), rsCatalogo.getString("nombre"),
                    rsCatalogo.getString("contraseña"), null, rsCatalogo.getString("email"));
            videojuego.setEditor(editor);
            resultado.add(videojuego);


            consulta= "select * from DLC where id_videojuego = ?;";
            try{
            stmDLC=con.prepareStatement(consulta);
            stmDLC.setInt(1, id);
            rsDLC= stmDLC.executeQuery();
            while(rsDLC.next()){
                DLC dlc= new DLC(id, rsDLC.getInt("id_dlc"),rsDLC.getString("nombre"),
                        rsDLC.getString("descripcion"), rsDLC.getDouble("precio"),
                        rsDLC.getDate("fecha_lanzamiento"));
                videojuego.addDLC(dlc);
            }
                   // (idVideojuego, idDLC, nombre, descripcion, precio, fechaLanzamiento)
            } catch (SQLException e){
              System.out.println(e.getMessage());
              //Mostar excepcion
              //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }
        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          //Mostar excepcion
          //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{// este esta bien pq es al final
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }//cierra cursosr haya o no excepcion de sql
            //VA cargate todo
        return resultado;
    }
    
    public List<String> consultarPlataformasVideojuego(int id_videojuego){
        List<String> resultado = new ArrayList<String>();
        String plataformaActual;
        Connection con;
        PreparedStatement stmPlataformas=null;
        ResultSet rsPlataformas;
        
        con=this.getConexion();
        
        String consulta = "select nombre_plataforma from plataforma_tiene_videojuego where id_videojuego = ?";
        
        try{
            stmPlataformas=con.prepareStatement(consulta);
            stmPlataformas.setInt(1, id_videojuego);
            rsPlataformas=stmPlataformas.executeQuery();
            while (rsPlataformas.next())
            {
                plataformaActual = rsPlataformas.getString("nombre_plataforma");//new Prestamo(rsPrestamos.getDate("fecha_prestamo"), rsPrestamos.getDate("fecha_devolucion"), rsPrestamos.getDate("fecha_vencimiento"),
                                        //rsPrestamos.getInt("num_ejemplar"), rsPrestamos.getInt("libro"), rsPrestamos.getString("id_usuario"));
                resultado.add(plataformaActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPlataformas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;   
    }
    
    public List<String> consultarCategoriasVideojuego(int id_videojuego){
        List<String> resultado = new ArrayList<String>();
        String categoriaActual;
        Connection con;
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;
        
        con=this.getConexion();
        
        String consulta = "select nombrecategoria from tenercategoria where id_videojuego = ?";
        
        try{
            stmCategorias=con.prepareStatement(consulta);
            stmCategorias.setInt(1, id_videojuego);
            rsCategorias=stmCategorias.executeQuery();
            while (rsCategorias.next())
            {
                categoriaActual = rsCategorias.getString("nombrecategoria");//new Prestamo(rsPrestamos.getDate("fecha_prestamo"), rsPrestamos.getDate("fecha_devolucion"), rsPrestamos.getDate("fecha_vencimiento"),
                                        //rsPrestamos.getInt("num_ejemplar"), rsPrestamos.getInt("libro"), rsPrestamos.getString("id_usuario"));
                resultado.add(categoriaActual);
            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;  
    }

    public List<Videojuego> consultaVideoJuegosInicio(){
        List <Videojuego> resultado = new ArrayList<>(3);
        Connection con;
        PreparedStatement stmVideojuego=null;
        ResultSet rsVideojuegos, rsEditor;

        con=this.getConexion();

        String consulta = "   select v.id, v.nombre, v.fechasubida, v.id_usreditor, v.precio, v.descripcion, v.imagen, v.banner, count(*) as totalCompras\n" +
                "   from videojuego v \n" +
                "   join comprar c on v.id=c.id_videojuego \n" +
                "   group by v.id\n" +
                "   order by totalCompras desc\n" +
                "   limit 3;";

        try{
            stmVideojuego=con.prepareStatement(consulta);
            rsVideojuegos=stmVideojuego.executeQuery();
            while (rsVideojuegos.next())
            {
                Videojuego videojuego = new Videojuego(rsVideojuegos.getInt("id"), rsVideojuegos.getString("nombre"),
                                        rsVideojuegos.getDate("fechasubida"), rsVideojuegos.getString("descripcion"),
                                        rsVideojuegos.getDouble("precio"), FachadaAplicacion.bytesToImage(rsVideojuegos.getBytes("imagen")),
                                        FachadaAplicacion.bytesToImage(rsVideojuegos.getBytes("banner")));
                videojuego.setNumDescargas(rsVideojuegos.getInt("totalCompras"));

                //Consulta para editor

                String consulta1= "  select * from usuario u " +
                        "  where id= ?;";
                try {
                    stmVideojuego = con.prepareStatement(consulta1);
                    stmVideojuego.setString(1, rsVideojuegos.getString("id_usreditor"));
                    rsEditor = stmVideojuego.executeQuery();
                    while (rsEditor.next()) {
                        Editor editor = new Editor(rsEditor.getString("id"), rsEditor.getString("nombre"), rsEditor.getString("contraseña"),
                                null, rsEditor.getString("email"));
                        videojuego.setEditor(editor);
                    }
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                    FachadaAplicacion.muestraExcepcion(e.getMessage());
                }
                resultado.add(videojuego);


            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void inicializarBaseDatos(){
        Connection con;
        PreparedStatement stmVideojuego=null;
        ResultSet rsVideojuegos, rsEditor;
        Videojuego resultado = null;

        con=this.getConexion();

        String consulta = "select v.id, v.nombre" +
                "   from videojuego v;";

        try{
            stmVideojuego=con.prepareStatement(consulta);
            rsVideojuegos=stmVideojuego.executeQuery();
            while (rsVideojuegos.next())
            {
                String nombre = rsVideojuegos.getString("nombre");
                int id = rsVideojuegos.getInt("id");
                String ruta = "StimFXMaven/src/imagenes/" + nombre + "Banner.png";
                byte[] img = FachadaAplicacion.pathToBytes(ruta);

                try {
                    stmVideojuego=con.prepareStatement("UPDATE videojuego\n" +
                            "SET banner = ?" +
                            "WHERE id= ?");
                    stmVideojuego.setBytes(1, img);
                    stmVideojuego.setInt(2, id);

                    stmVideojuego.executeUpdate();
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                    FachadaAplicacion.muestraExcepcion(e.getMessage());
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }

    public Videojuego proximoVideojuego(){
        Connection con;
        PreparedStatement stmVideojuego=null;
        ResultSet rsVideojuegos, rsEditor;
        Videojuego resultado = null;

        con=this.getConexion();

        String consulta = "select v.id, v.nombre, v.fechasubida, v.id_usreditor, v.precio, v.descripcion, v.imagen, v.banner\n" +
                "   from videojuego v  \n" +
                "   where fechasubida  > current_date\n" +
                "   order by fechasubida desc\n" +
                "   limit 1;";

        try{
            stmVideojuego=con.prepareStatement(consulta);
            rsVideojuegos=stmVideojuego.executeQuery();
            while (rsVideojuegos.next())
            {
                resultado = new Videojuego(rsVideojuegos.getInt("id"), rsVideojuegos.getString("nombre"),
                        rsVideojuegos.getDate("fechasubida"), rsVideojuegos.getString("descripcion"),
                        rsVideojuegos.getDouble("precio"), FachadaAplicacion.bytesToImage(rsVideojuegos.getBytes("imagen")),
                        FachadaAplicacion.bytesToImage(rsVideojuegos.getBytes("banner")));

                String consulta1= "  select * from usuario u " +
                        "  where id= ?;";
                try {
                    stmVideojuego = con.prepareStatement(consulta1);
                    stmVideojuego.setString(1, rsVideojuegos.getString("id_usreditor"));
                    rsEditor = stmVideojuego.executeQuery();
                    while (rsEditor.next()) {
                        Editor editor = new Editor(rsEditor.getString("id"), rsEditor.getString("nombre"), rsEditor.getString("contraseña"),
                                null, rsEditor.getString("email"));
                        resultado.setEditor(editor);
                    }
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                    FachadaAplicacion.muestraExcepcion(e.getMessage());
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            FachadaAplicacion.muestraExcepcion(e.getMessage());
        }finally{
            try {stmVideojuego.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
}