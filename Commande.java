package com.example.tracking_l;

public class Commande {

    private int id ;
    private String client_name;
    private String status;
    private int delivery_man_id;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDelivery_man_id() {
        return delivery_man_id;
    }

    public void setDelivery_man_id(int delivery_man_id) {
        this.delivery_man_id = delivery_man_id;
    }
}
