package tp.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;
import tp.sendmeal.domain.PedidoAndItems;

@Dao
public interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertPedido(Pedido pedido);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItem(ItemsPedido item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItem(List<ItemsPedido> items);

    @Update
    public  void updatePedido(Pedido pedido);

    @Delete
    public void deletePedido(Pedido pedido);

    @Query("SELECT * FROM Pedido WHERE id_pedido = :id")
    public Pedido selectPedidoById(int id);

    @Query("SELECT * FROM Pedido")
    public List<PedidoAndItems> loadPedidoAndItems();

    @Query("SELECT * FROM Pedido WHERE id_pedido =:id")
    public PedidoAndItems loadPedidoAndItemsById(long id);
}
