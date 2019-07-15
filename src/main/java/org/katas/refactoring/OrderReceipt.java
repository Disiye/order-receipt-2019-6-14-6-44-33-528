package org.katas.refactoring;

public class OrderReceipt {
    private static final String ORDER_HEADER = "======Printing Orders======\n";
    private static final double SALES_TAX_RATE = 0.10;
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printHeaders(output);
        printCustomerInfo(output);
        printLineItems(output);
        printSalesTaxAndTotalAmount(output);
        return output.toString();
    }

    private void printSalesTaxAndTotalAmount(StringBuilder output) {
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * SALES_TAX_RATE;
            totSalesTx += salesTax;
            tot += lineItem.totalAmount() + salesTax;
        }

        output.append("Sales Tax").append('\t').append(totSalesTx);
        output.append("Total Amount").append('\t').append(tot);
    }

    private void printHeaders(StringBuilder output){
        output.append(ORDER_HEADER);
    }

    private void printCustomerInfo(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private void printLineItems(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(String.format("%s\t%s\t%s\t%s\n",
                    lineItem.getDescription(),
                    lineItem.getPrice(),
                    lineItem.getQuantity(),
                    lineItem.totalAmount()
            ));
        }
    }
}