package tp.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;
import tp.sendmeal.domain.PedidoAndItems;

@Dao
public abstract class PedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long _insertPedido(Pedido pedido);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void _insertItem(ItemsPedido item);


    @Update
    public  abstract void updatePedido(Pedido pedido);

    @Delete
    public abstract void deletePedido(Pedido pedido);

    @Query("SELECT * FROM Pedido WHERE id_pedido = :id")
    public abstract Pedido selectPedidoById(int id);


    //@Query("SELECT * FROM Pedido")
//    public  abstract List<PedidoAndItems> loadPedidoAndItems();


    @Transaction    @Query("SELECT * FROM Pedido WHERE id_pedido =:id")
    public abstract PedidoAndItems loadPedidoAndItemsById(long id);

@Transaction
    public long insertPedidoAndItems(Pedido pedido) {
        long idpedido=_insertPedido(pedido);
        List<ItemsPedido> items=pedido.getItems();
        if (!items.isEmpty()){
            for (int i=0; i<items.size();i++){
                ItemsPedido item=items.get(i);
                item.setPedidoId(idpedido);
                this._insertItem(item);
            }
        }
        return idpedido;
    }
}
