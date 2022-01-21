package ePortfolio;

/**
 * Stock.java is a class file that represents an individual
 * stock investment as part of the investment portfolio
 * @author  Khushi Rajput
 * @version 3.0
 * @since   2021-11-06
 */


public class Stock extends Investment{
    private static double comm = 9.99; // commission for stocks

    /**
     * Constructor for the Stock class
     * @param newType type of the investment
     * @param newSymbol   symbol for the investment
     * @param newName     name of the investment
     * @param newQuantity quantity of the stocks
     * @param newPrice    price per stock
     * @param value       book value of the investment
     */
    public Stock(String newType, String newSymbol, String newName, int newQuantity, double newPrice, double value){
        super(newType, newSymbol, newName, newQuantity, newPrice, value);
    }
    /**
     * Default Constructor for the Stock class
     */
    public Stock() {
        super();
    }


    // Methods specific to the Stock subclass

    /**
     * Calculates the book Value of the investment.
     *
     * @param newQuantity quantity of the stocks
     * @param newPrice    price of an individual stock
     * @return book value for the investment
     */

    public double computeBookValue(int newQuantity, double newPrice) {
        double value = (newQuantity)*(newPrice) + comm;
        return value;
    }

    /**
     * Calculates the Gain of the investment The Gain can be positive or negative
     * depending on the price hike or fall
     *
     * @param newQuantity quantity of the investment
     * @param newPrice price of the investment
     * @return Gain for the investment
     */
    public double computeGain(int newQuantity, double newPrice){
        double value = computeBookValue(newQuantity, newPrice);
        double gain = ((newQuantity)*newPrice - comm) - value;
        return gain;
    }

    /**
     * Calculates the payment received after selling investment
     *
     * @param soldStocks number of stocks being sold
     * @param priceSpecified price specified by the user when selling an investment
     * @return payment received
     *
     */
    public double payment(int soldStocks, double priceSpecified) {
        double payment = soldStocks*priceSpecified - comm;
        return payment;
    }

    /**
     * toString method for the Stock class - overrides the toString() method of superclass.
     *
     * @return string that prints out the attributes in order
     */
    @Override
    public String toString() {
        String statement = super.toString();
        return statement;
    }
}