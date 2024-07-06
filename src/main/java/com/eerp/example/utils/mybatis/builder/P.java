package com.eerp.example.utils.mybatis.builder;


/**
 * @author jiutou
 * @version v1.0
 * date 2019-12-13
 */
public class P<T, R> {


    private PropertyFunction<T, R> function;
    private Object value;

    private P(PropertyFunction<T, R> function, Object value) {
        this.function = function;
        this.value = value;
    }

    public static <T, R> P<T, R> of(PropertyFunction<T, R> function, Object value) {
        return new P<>(function, value);
    }

    public PropertyFunction<T, R> getFunction() {
        return function;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "P{" +
                "value=" + value +
                '}';
    }
}
