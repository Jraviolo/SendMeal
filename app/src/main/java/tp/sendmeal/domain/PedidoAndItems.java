package tp.sendmeal.domain;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PedidoAndItems {

    @Embedded
    public Pedido pedido;

    @Relation(parentColumn = "id_pedido",entityColumn = "pedidoId",entity = ItemsPedido.class)
    private List<ItemsPedido> items;

    public Pedido getPedido() {
        return pedido;
    }

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
