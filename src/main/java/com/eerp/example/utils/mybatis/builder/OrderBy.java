package com.eerp.example.utils.mybatis.builder;

/**
 * @author jiutou
 * @version : v1.0
 * date : 2018-09-05 19:12
 */
public class OrderBy {

    private static final String ASC = " ASC";

    private static final String DESC = " DESC";

    private StringBuilder order;

    public static OrderBy builder() {
        return new OrderBy();
    }

    public OrderBy asc(String... columns) {
        if (order == null) {
            order = new StringBuilder();
        }
        for (String column : columns) {
            if (order.length() == 0) {
                order.append(column).append(ASC);
            } else {
                order.append(", ").append(column).append(ASC);
            }
        }
        return this;
    }

    public OrderBy desc(String... columns) {
        if (order == null) {
            order = new StringBuilder();
        }
        for (String column : columns) {
            if (order.length() == 0) {
                order.append(column).append(DESC);
            } else {
                order.append(", ").append(column).append(DESC);
            }
        }
        return this;
    }

    public String build() {
        return order.toString();
    }

}
