package model;

public class Customer {
    private long id;
    private String customer_name;
    private String adress;

    public void setId(long id) {
        this.id = id;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public long getId() {
        return id;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return String.format("Customer :%s\naddress: %s\n", customer_name, adress);
    }

    public static String[] getParam(){
        return new String[]{
                "id",
                "customer_name",
                "adress"
        };
    }

    public String[] getAll(){
        return new String[]{
                String.valueOf(id),
                customer_name,
                adress
        };
    }
}
