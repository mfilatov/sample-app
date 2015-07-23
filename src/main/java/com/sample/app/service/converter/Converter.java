package com.sample.app.service.converter;

import java.util.List;

public interface Converter<T, P> {
    List<T> convert(P p) throws Exception;
}
