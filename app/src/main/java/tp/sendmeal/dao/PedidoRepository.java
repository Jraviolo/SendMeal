package tp.sendmeal.dao;

import android.content.Context;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Update;

import java.util.List;

import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;

public class PedidoRepository {

    private static PedidoRepository PR = null;

    private PedidoDB pedidoDB;

    //private ItemPedidoDao itemPedidoDao;
    private PedidoDao pedidoDao;

    private PedidoRepository(Context ctx){
        pedidoDB = Room.databaseBuilder(ctx,
                PedidoDB.class, "pedido-db").allowMainThreadQueries().build();

        pedidoDao=pedidoDB.pedidoDao();
    }

    public synchronized static PedidoRepository getInstance(Context ctx){
        if(PR ==null){
            PR = new PedidoRepository(ctx);
        }
        return PR;
    }

    public PedidoDB getPedidoDB() {
        return pedidoDB;
    }

    public void insertPedido(Pedido pedido){
        pedidoDao.insertPedido(pedido);
    }
    public  void updatePedido(Pedido pedido){
        pedidoDao.updatePedido(pedido);
    }

    public void deletePedido(Pedido pedido){
        pedidoDao.deletePedido(pedido);
    }

    public Pedido selectPedidoById(int id){
        return pedidoDao.selectPedidoById(id);
    }

    /*
    public void insertItemPedido(ItemsPedido itemPedido){
        itemPedidoDao.insertItemPedido(itemPedido);
    }
    public  void updatePedido(ItemsPedido itemPedido){
        itemPedidoDao.updatePedido(itemPedido);
    }

    public void deletePedido(ItemsPedido itemPedido){
        itemPedidoDao.deletePedido(itemPedido);
    }

    public ItemsPedido selectItemPedidoById(int id){
        return itemPedidoDao.selectItemPedidoById(id);
    }

    public List<ItemsPedido> selectItemsPedidoByIdPlato(int id){
        return itemPedidoDao.selectItemsPedidoByIdPlato(id);
    }*/
}
