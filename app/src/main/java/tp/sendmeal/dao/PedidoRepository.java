package tp.sendmeal.dao;

import android.content.Context;

import tp.sendmeal.domain.Pedido;

public class PedidoRepository {
    private static PedidoRepository _REPO =null;
    private PedidoDao pedidoDao;

    private PedidoRepository(Context ctx){
        //MyDatabase ???
    }
}
