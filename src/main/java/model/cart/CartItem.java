package model.cart;

public class CartItem {
    private int id;
    private String product;
    private int buc;
    private int discount;
    private int pretUnitar;
    private int pretFaraDiscount;
    private int pretCuDiscount;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", buc=" + buc +
                ", discount=" + discount +
                ", pretUnitar=" + pretUnitar +
                ", pretFaraDiscount=" + pretFaraDiscount +
                ", pretCuDiscount=" + pretCuDiscount +
                '}';
    }

    public CartItem(int id, String product, int buc, int discount, int pretUnitar, int pretFaraDiscount, int pretCuDiscount) {
        this.id = id;
        this.product = product;
        this.buc = buc;
        this.discount = discount;
        this.pretUnitar = pretUnitar;
        this.pretFaraDiscount = pretFaraDiscount;
        this.pretCuDiscount = pretCuDiscount;
    }

    public int getBuc() {
        return buc;
    }

    public String getProduct() {
        return this.product;
    }

    public int getDiscount() {
        return this.discount;
    }

    public int getPretUnitar() {
        return pretUnitar;
    }

    public int getPretFaraDiscount() {
        return pretFaraDiscount;
    }

    public int getPretCuDiscount() {
        return pretCuDiscount;
    }

    public int getId() {
        return this.id;
    }

    public void setPretUnitare(int newpretUnitar) {
        this.pretUnitar = newpretUnitar;
    }

    public void setPretFaraDiscount(int newpretFaraDiscount) {
        this.pretFaraDiscount = newpretFaraDiscount;
    }

    public void setBuc(int newbuc) {
        this.buc = newbuc;
    }

    public void setPretCuDiscount(int newpretCuDiscount) {
        this.pretCuDiscount = pretCuDiscount;
    }
}
