package dao;

import dao.dto.GastoDto;
import entities.Gasto;
import exceptions.DAOException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class GastoDaoImplH2 implements GastoDao{

    private static final String INSERT_INTO_EXPENSE = "INSERT INTO expense (description, amount, date, id_category) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_EXPENSES = "SELECT * FROM expense";

    //generando una instancia de conexion que tiene que ser provista por otro obj
    private final Connection connection; //esto es una dependencia


    //inicializo la conexion en el constructor
    //cuando yo le pase una  conexion por constructor a mi clase voy a tener disponible una
    // instancia de esa conexion para poder usarla
    public GastoDaoImplH2(Connection connection){

        this.connection = connection;
    }

    @Override
    public void insert(GastoDto gastoDto) {
        try( PreparedStatement ps = connection.prepareStatement(INSERT_INTO_EXPENSE)){

            //mapeo a una entidad y manipulo la entidad hacia la bd
            Gasto gasto = mapDtoToGasto(gastoDto);

            ps.setString(1, gasto.getDescripcion());
            ps.setDouble(2, gasto.getValor());
            ps.setString(3, gasto.getFecha());
            ps.setInt(4, gasto.getCategoriaId());

            int affectedRows = ps.executeUpdate();

            if(affectedRows == 0){
                throw new DAOException("Error al insertar el gasto, 0 filas afectadas");
            }

        }catch (SQLException | DAOException e ){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<GastoDto> getAll() throws DAOException {
        List<GastoDto> gastosDto = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(GET_ALL_EXPENSES)){

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                gastosDto.add(mapResultSetToGastoDto(resultSet));
            }

            return gastosDto;

        }catch(SQLException e){
            throw new DAOException("Error al recuperar la lista de gastos", e);
        }

    }

    @Override
    public void update(GastoDto gastoDto) {
        try{  //creo la entidad Gasto con los valores que trae el Dto
           Gasto newGasto = new Gasto();

           newGasto.setId(gastoDto.getId());
           newGasto.setDescripcion(gastoDto.getDescripcion());
           newGasto.setValor(gastoDto.getValor());
           newGasto.setFecha(gastoDto.getFecha());
           newGasto.setCategoriaId(gastoDto.getCategoriaId());
           PreparedStatement ps = connection.prepareStatement(
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

            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM expense WHERE id_exp = ?"
            );

            ps.setInt(1, gastoId);
            ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);

        }


    }

private Gasto mapDtoToGasto(GastoDto gastoDto){
    Gasto newGasto = new Gasto();
    newGasto.setCategoriaId(gastoDto.getCategoriaId());
    newGasto.setDescripcion(gastoDto.getDescripcion());
    newGasto.setFecha(gastoDto.getFecha());
    newGasto.setValor(gastoDto.getValor());
    return newGasto;
}

private GastoDto mapResultSetToGastoDto(ResultSet rs) throws SQLException {
        //creo un objeto gastodto
    GastoDto newGastoDto = new GastoDto();
    //obtengo el dato del result set dado el nombre de la columna en la base de datos y lo agrego a mi objeto de gastosdto
        newGastoDto.setId(rs.getInt("id_exp"));
        newGastoDto.setDescripcion(rs.getString("description"));
        newGastoDto.setValor(rs.getDouble("amount"));
        newGastoDto.setFecha(rs.getString("date"));
        newGastoDto.setCategoriaId(rs.getInt("id_category"));

        return newGastoDto;//retorno mi objeto gastoDto ya con todos los valores incorporados
   }
}
