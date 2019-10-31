package tp.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tp.sendmeal.domain.ItemsPedido;


@Dao
public interface ItemPedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItemPedido(ItemsPedido itemPedido);

    @Update
    public  void updatePedido(ItemsPedido itemPedido);

    @Delete
    public void deletePedido(ItemsPedido itemPedido);

    @Query("SELECT * FROM ItemsPedido WHERE id_itemPedido = :id")
    public ItemsPedido selectItemPedidoById(int id);

    @Query("SELECT * FROM ItemsPedido WHERE idPlato = :id")
    public List<ItemsPedido> selectItemsPedidoByIdPlato(int id);
}
