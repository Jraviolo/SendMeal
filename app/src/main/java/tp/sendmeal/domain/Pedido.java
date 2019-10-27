package tp.sendmeal.domain;

import java.util.Date;
import java.util.List;

public class Pedido {

    private Integer id;
    private Date fecha;
    private Integer estado;
    private Double lat;
    private Double lng;
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


    public String getEstado() {
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
