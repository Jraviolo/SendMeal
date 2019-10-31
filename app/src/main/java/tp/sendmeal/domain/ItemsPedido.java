package tp.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Pedido.class, parentColumns = "id_pedido", childColumns = "idpedido"))
public class ItemsPedido {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_itemPedido")
    private Integer id;
    @Embedded private Pedido pedido;
    @Embedded private Plato plato;
    private Integer cantidad;
    private Float precio;

    @ColumnInfo(name = "idpedido")
    private int idpedido;

    public ItemsPedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
