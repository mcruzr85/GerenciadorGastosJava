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
    public void insert(CategoriaDto catDto) {

        try{

            Categoria categoria = mapDtoToCategoria(catDto);

            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_CATEGORY);
            ps.setString(1, categoria.getNombre());

            ps.executeUpdate();
            ps.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



    @Override
    public CategoriaDto getCategoryByName(String name) throws DAOException {
        CategoriaDto catDto = new CategoriaDto();

        try{

            PreparedStatement ps = connection.prepareStatement(GET_CATEGORY_BY_NAME);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
           if(rs.next()){
               Categoria newCategoria =  new Categoria(rs.getInt("id_cat") , rs.getString("nombre")) ;
               catDto = mapCategoriaToDto(newCategoria);
           }
            rs.close();
            ps.close();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return catDto;
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
        categoriaDto.setNombre(categoria.getNombre());
        categoriaDto.setId(categoriaDto.getId());
        return categoriaDto;
    }


}
