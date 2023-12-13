package dao;

import java.util.List;
import dao.dto.GastoDto;


    public interface GastoDao {

        //CRUD
        //CREATE
        void insertar(GastoDto gastoDto);

        //READ
        List<GastoDto> getAll();

        //UPDATE

        //DELETE

    }