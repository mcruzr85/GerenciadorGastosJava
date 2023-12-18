package dao;

import dao.dto.CategoriaDto;
import entities.Categoria;
import exceptions.DAOException;

import java.sql.Connection;
import java.util.List;

public interface CategoriaDao {


    //CRUD
    //CREATE
    void insert(CategoriaDto catDto);
    CategoriaDto getCategoryByName(String name) throws DAOException;

    //READ
    List<CategoriaDto> getAll();

    //UPDATE
    void update(CategoriaDto catDto);

    //DELETE

    void delete(int id);

    public void closeDBConnection() ;
}
