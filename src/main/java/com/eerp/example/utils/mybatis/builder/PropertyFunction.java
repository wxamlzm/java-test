package com.eerp.example.utils.mybatis.builder;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author jiutou
 * @version : v1.0
 * date : 2018/7/15 20:36
 */
public interface PropertyFunction<T, R> extends Function<T, R>, Serializable {

}
