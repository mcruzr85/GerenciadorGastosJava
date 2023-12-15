package dao;

import java.util.List;
import dao.dto.GastoDto;
import entities.Gasto;


    public interface GastoDao {

        //CRUD
        //CREATE
        void insertar(GastoDto gastoDto);

        //READ
        List<GastoDto> getAll();

        //UPDATE
        void update(GastoDto gastoDto);

        //DELETE

        void delete(int id);

    }