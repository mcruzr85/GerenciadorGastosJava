package dao;

import config.JdbcConfiguration;
import dao.dto.GastoDto;
import entities.Gasto;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class GastoDaoImplH2 implements GastoDao{

    private final Connection con; //esto es una dependencia


    //inicializo la conexion en el constructor
    public GastoDaoImplH2(){
        this.con = JdbcConfiguration.getDBConnection();
    }

    @Override
    public void insertar(GastoDto gastoDto) {
        try{
            Gasto newGasto = new Gasto();
            newGasto.setCategoria(gastoDto.getCategoria());
            newGasto.setDescripcion(gastoDto.getDescripcion());
            newGasto.setFecha(gastoDto.getFecha());
            newGasto.setValor(gastoDto.getValor());

            PreparedStatement ps = con.prepareStatement("INSERT INTO expense(description, amount, date, id_category) VALUES( ?, ?, ?, ?))");
            ps.setString(2, newGasto.getDescripcion());
            ps.setDouble(3, newGasto.getValor());
            ps.setString(4, newGasto.getFecha());
            ps.setInt(5, newGasto.getCategoria().getId());

            ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<GastoDto> getAll() {
        return null;
    }
}
