package entity;

public class PhoneSubscriber {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String creditCardNumber;
    private double debit;
    private double credit;
    private int localCallMinutes;
    private int internationalCallMinutes;

    public PhoneSubscriber() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getLocalCallMinutes() {
        return localCallMinutes;
    }

    public void setLocalCallMinutes(int localCallMinutes) {
        this.localCallMinutes = localCallMinutes;
    }

    public int getInternationalCallMinutes() {
        return internationalCallMinutes;
    }

    public void setInternationalCallMinutes(int internationalCallMinutes) {
        this.internationalCallMinutes = internationalCallMinutes;
    }

    @Override
    public String toString() {
        return "PhoneSubscriber{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", address='" + address + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", debit=" + debit +
                ", credit=" + credit +
                ", localCallMinutes=" + localCallMinutes +
                ", internationalCallMinutes=" + internationalCallMinutes +
                '}';
    }
}