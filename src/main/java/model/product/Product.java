package model.product;

public class Product {
    private int id;
    private String piesa;
    private String marca;
    private String model;
    private String categorie;
    private int stoc;
    private int pret;
    private int discount;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", piesa='" + piesa + '\'' +
                ", marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", categorie='" + categorie + '\'' +
                ", stoc=" + stoc +
                ", pret=" + pret +
                ", discount=" + discount +
                '}';
    }

    public Product(int id, String piesa, String marca, String model, String categorie, int stoc, int discount, int pret) {
        this.id = id;
        this.piesa = piesa;
        this.marca = marca;
        this.model = model;
        this.categorie = categorie;
        this.stoc = stoc;
        this.discount = discount;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPiesa() {
        return piesa;
    }

    public void setPiesa(String piesa) {
        this.piesa = piesa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }
}
