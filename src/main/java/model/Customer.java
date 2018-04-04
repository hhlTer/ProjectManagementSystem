package model;

public class Customer {
    private long id;
    private String customer_name;
    private String adress;

    @Override
    public String toString() {
        return String.format("Company :%s\naddress: %s\n", customer_name, adress);
    }

    public String[] getParam(){
        return new String[]{
                "customer_name",
                "adress"
        };
    }
}
