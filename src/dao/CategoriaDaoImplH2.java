package dao;

import config.JdbcConfiguration;
import dao.dto.CategoriaDto;
import entities.Categoria;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoriaDaoImplH2 implements CategoriaDao{

    private static final String INSERT_INTO_CATEGORY = "INSERT INTO category (nombre) VALUES (?)";
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM category WHERE nombre = ?";

    private final Connection connection;

    public CategoriaDaoImplH2(Connection connection){
       this.connection = connection;
    }
    @Override
    public void insert(CategoriaDto catDto) throws DAOException {

        try( PreparedStatement ps = connection.prepareStatement(INSERT_INTO_CATEGORY);){//try con recursos

            Categoria categoria = mapDtoToCategoria(catDto);

            ps.setString(1, categoria.getNombre());

           int affectedRows = ps.executeUpdate();
            //validando el resultado de la ejecucion del statement
            // si no devuelve filas afectadas hubo un error al insertar en la bd
            if(affectedRows == 0){
                throw new DAOException("Error al insertar la categoria en la base de datos, 0 filas afectadas.");
            }

        }catch(SQLException | DAOException e){
            assert e instanceof SQLException;
            throw new DAOException("Error en el  DAO", (SQLException) e);  //propagando la excepcion
        }
    }



    @Override
    public CategoriaDto getCategoryByName(String name) throws DAOException {
        CategoriaDto catDto = new CategoriaDto();
        System.out.println("nombre de la categoria a buscar: " + name);

        try( PreparedStatement ps = connection.prepareStatement(GET_CATEGORY_BY_NAME)){

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
           if(rs.next()){
               Categoria newCategoria =  new Categoria(rs.getInt("id_cat") , rs.getString("nombre")) ;
               System.out.println(newCategoria.getId() + " " + newCategoria.getNombre());
               catDto = mapCategoriaToDto(newCategoria);
               System.out.println(catDto.getId() + " " + catDto.getNombre());
               return catDto;
           }
           return null;


        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategoriaDto> getAll() {
        return null;
    }

    @Override
    public void update(CategoriaDto catDto) {

    }

    @Override
    public void delete(int id) {

    }
    public void closeDBConnection(){
        try {
            this.connection.close();
            System.out.println("The connection is closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Categoria mapDtoToCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDto.getNombre());
        return categoria;
    }
    private CategoriaDto mapCategoriaToDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(categoria.getId());
        categoriaDto.setNombre(categoria.getNombre());

        return categoriaDto;
    }


}
