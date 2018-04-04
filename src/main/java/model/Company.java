package model;

public class Company {
    private long id;
    private String company_name;
    private String adress;

    public void setId(long id) {
        this.id = id;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return String.format("Company :%s\naddress: %s\n", company_name, adress);
    }

    public String[] getParam(){
        return new String[]{
                "company_name",
                "adress"
        };
    }
}
