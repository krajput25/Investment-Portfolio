package ePortfolio;

/**
 * MutualFund.java is a class file that represents an individual
 *  mutual fund investment as part of the investment portfolio
 * @author  Khushi Rajput
 * @version 3.0
 * @since   2021-11-06
 */


public class MutualFund extends Investment{

    private final int fee = 45; // redemption fee for funds

    /**
     * Constructor for the Mutual Fund class
     * @param newType type of the investment
     * @param newSymbol symbol for the investment
     * @param newName name of the investment
     * @param newQuantity quantity of the funds
     * @param newPrice price per fund
     * @param value book value of the investment
     */
    public MutualFund(String newType, String newSymbol, String newName, int newQuantity, double newPrice, double value){
        super(newType, newSymbol, newName, newQuantity, newPrice, value);
    }

    /**
     * Default Constructor for the Mutual Fund class
     */
    public MutualFund() {
        super();
    }


    //Methods specific to the MutualFund subclass

    /**
     * Calculates the book Value of the investment
     *
     * @param newQuantity quantity of the funds
     * @param newPrice    price of an individual fund
     * @return book value for the investment
     */

    public double computeBookValue(int newQuantity, double newPrice) {
        double value = (newQuantity) * (newPrice);
        return value;
    }


    /**
     * Calculates the Gain of the investment
     * The Gain can be positive or negative depending on the price hike or fall
     *
     * @param newQuantity quantity of the investment
     * @param newPrice price of the investment
     * @return Gain for the investment
     */
    public double computeGain(int newQuantity, double newPrice){
        double value = computeBookValue(newQuantity, newPrice);
        double gain = ((newQuantity)*newPrice - fee) - value;
        return gain;
    }

    /**
     * Calculates the payment received after selling investment
     *
     * @param soldFunds number of funds being sold
     * @param priceSpecified price specified by the user when selling an investment
     * @return payment received
     *
     */
    public double payment(int soldFunds, double priceSpecified) {
        double payment = soldFunds*priceSpecified - fee;
        return payment;
    }

    /**
     * toString method for the Mutual Fund class - overrides the toString() method of superclass.
     *
     * @return string that prints out the attributes in order
     */
    @Override
    public String toString() {
        String statement = super.toString();
        return statement;
    }
}