package tp.sendmeal.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;

@Database(entities = {Pedido.class, ItemsPedido.class}, version = 1,exportSchema = false)
public abstract class PedidoDB extends RoomDatabase {

        public abstract PedidoDao pedidoDao();

}
