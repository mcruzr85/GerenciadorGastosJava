package dao;

import java.util.List;
import dao.dto.GastoDto;
import entities.Gasto;
import exceptions.DAOException;


public interface GastoDao {

        //CRUD
        //CREATE
        void insert(GastoDto gastoDto);

        //READ
        List<GastoDto> getAll() throws DAOException;

        //UPDATE
        void update(GastoDto gastoDto);

        //DELETE

        void delete(int id);

    }