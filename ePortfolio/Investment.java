package ePortfolio;

/**
 * Investment.java is a class file that represents an individual investment as part of the investment portfolio.
 * The investment can either be a mutual fund or stock. It functions as the superclass the classes Stock.java and MutualFund.java
 * The Investment class is an abstract class. Investments can only be created using the Stock or MutualFund constructors
 *
 * @author Khushi Rajput
 * @version 3.0
 * @since 2021-10-18
 */

//superclass
public abstract class Investment {
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private String type;

    /**
     * Constructor for the Investment class
     *
     * @param newType type of investment - stock or mutual fund
     * @param newSymbol   symbol for the investment
     * @param newName     name of the investment
     * @param newQuantity quantity of the funds
     * @param newPrice    price per fund
     * @param value       book value of the investment
     */
    public Investment(String newType, String newSymbol, String newName, int newQuantity, double newPrice, double value){
        this.type = newType;
        this.symbol = newSymbol;
        this.name = newName;
        this.quantity = newQuantity;
        this.price = newPrice;
        this.bookValue = value;
    }

    /**
     * Default Constructor for the Investment class
     */
    public Investment() {
        this.type = "";
        this.symbol = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
        this.bookValue = 0.0;
    }
    //mutators

    /**
     * Setter for the symbol
     *
     * @param newSymbol symbol for the investment
     */
    public void setSymbol(String newSymbol) {
        this.symbol = newSymbol;
    }

    /**
     * Setter for the name
     *
     * @param newName name for the investment
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Setter for the quantity
     *
     * @param newQuantity quantity of stocks
     */
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Setter for the Price
     *
     * @param newPrice price of the individual stock
     */
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * Setter for the book value
     *
     * @param newValue book value for the investment
     */
    public void setBookValue(double newValue) {
        this.bookValue = newValue;
    }

    /**
     * Setter for the type of the investment
     *
     * @param newType type of the investment
     */
    public void setType(String newType) {
        this.type = newType;
    }


    //accessors

    /**
     * Getter for the type
     *
     * @return symbol for the investment
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter for the symbol
     *
     * @return symbol for the investment
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Getter for the name
     *
     * @return name for the investment
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the quantity
     *
     * @return quantity of the stocks
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Getter for the price
     *
     * @return price of an individual stock
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * toString method for the Investment class
     *
     * @return string that prints out the attributes in order
     */
    public String toString() {
        String Str = "Type = " + "\"" + type + "\"" + "\n" + "Symbol = " + "\"" + symbol + "\"" + "\n" + "Name = " +"\"" + name + "\"" +
                "\n" + "Quantity = " +"\""+ quantity +"\""+ "\n" + "Price = " +"\""+ price + "\""+"\n" + "Book value = " + "\""+ bookValue +"\""+ "\n";
        return Str;
    }



}
