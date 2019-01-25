package model.cart;

public class CartItem {
    private int id;
    private String product;
    private int buc;
    private int discount;
    private int pretUnitar;
    private int pretFaraDiscount;
    private int pretCuDiscount;

    public CartItem(int id, String product, int buc, int discount, int pretUnitar, int pretFaraDiscount, int pretCuDiscount) {
        this.id = id;
        this.product = product;
        this.buc = buc;
        this.discount = discount;
        this.pretUnitar = pretUnitar;
        this.pretFaraDiscount = pretFaraDiscount;
        this.pretCuDiscount = pretCuDiscount;
    }
}
