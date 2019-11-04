package tp.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_pedido")
    private Integer id;
    @Ignore
    private Date fecha;
    private Integer estado;
    private Double lat;
    private Double lng;

    @Ignore
    private List<ItemsPedido> items;


    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public  Integer getNumeroEstado(){
        return estado;
    }


    public String getEstadoText() {
        String stringEstado = new String();

        switch (estado){
            case 1:
                stringEstado = "Pendiente";
                break;
            case 2:
                stringEstado = "Enviado";
                break;
            case 3:
                stringEstado = "Aceptado";
                break;
            case 4:
                stringEstado = "Rechazado";
                break;
            case 5:
                stringEstado = "En preparacion";
                break;
            case 6:
                stringEstado = "En envio";
                break;
            case 7:
                stringEstado = "Entregado";
                break;
            case 8:
                stringEstado = "Cancelado";
                break;
        }



        return stringEstado;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }


}
