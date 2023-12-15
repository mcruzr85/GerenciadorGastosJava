package dao;

import config.JdbcConfiguration;
import dao.dto.GastoDto;
import entities.Categoria;
import entities.Gasto;

import java.sql.ResultSet;
import java.util.ArrayList;
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

            PreparedStatement ps = con.prepareStatement("INSERT INTO expense (description, amount, date, id_category) VALUES (?, ?, ?, ?)");
            ps.setString(1, newGasto.getDescripcion());
            ps.setDouble(2, newGasto.getValor());
            ps.setString(3, newGasto.getFecha());
            ps.setInt(4, newGasto.getCategoria().getId());

            ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<GastoDto> getAll() {
        List<GastoDto> gastosDto = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM expense");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id_exp");
                String descripcion = rs.getString("description");
                String fecha = rs.getString("date");
                double price = rs.getDouble("amount");
                int id_cat = rs.getInt("id_category");
                String catNameTest = "prueba";

                GastoDto newGastoDto = new GastoDto();
                newGastoDto.setId(id);
                newGastoDto.setDescripcion(descripcion);
                newGastoDto.setValor(price);
                newGastoDto.setFecha(fecha);
                newGastoDto.setCategoria(new Categoria(id_cat, catNameTest));

                gastosDto.add(newGastoDto);
            }


        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return gastosDto;
    }

    @Override
    public void update(GastoDto gastoDto) {
        try{  //creo la entidad Gasto con los valores que trae el Dto
           Gasto newGasto = new Gasto();

           newGasto.setId(gastoDto.getId());
           newGasto.setDescripcion(gastoDto.getDescripcion());
           newGasto.setValor(gastoDto.getValor());
           newGasto.setFecha(gastoDto.getFecha());
           newGasto.setCategoria(gastoDto.getCategoria());
           PreparedStatement ps = con.prepareStatement(
                   "UPDATE expense SET description = ?, date = ?, amount = ? WHERE id_exp = ?");

           ps.setString(1, newGasto.getDescripcion());
           ps.setString(2, newGasto.getFecha());
           ps.setDouble(3, newGasto.getValor());
           ps.setInt(4, newGasto.getId());

           ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int gastoId) {
        try{

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM expense WHERE id_exp = ?"
            );

            ps.setInt(1, gastoId);
            ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);

        }


    }

}
