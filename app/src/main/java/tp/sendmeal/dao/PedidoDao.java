package tp.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;

@Dao
public interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPedido(Pedido pedido);

    @Update
    public  void updatePedido(Pedido pedido);

    @Delete
    public void deletePedido(Pedido pedido);

    @Query("SELECT * FROM Pedido WHERE id_pedido = :id")
    public Pedido selectPedidoById(int id);

}
