package model;


import javax.persistence.*;

@Table(name = "customers")
@Entity
public class Customer implements GenerallyTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "adress")
    private String address;

    public void setId(long id) {
        this.id = id;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public void setAddress(String adress) {
        this.address = adress;
    }
    public long getId() {
        return id;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("Customer :%s\naddress: %s\n", customer_name, address);
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
                address
        };
    }

    @Override
    public String[] getPrm() {
        return new String[]{
                "id",
                "customer_name",
                "adress"
        };
    }

    @Override
    public String getTableName() {
        return "customers";
    }
}
