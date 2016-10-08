package org.queelt.common.mapper;

import org.queelt.common.page.PageList;

public interface ResponseMapper<T, I> {

    T map(I i);


    ListResponse<T> mapList(PageList<I> input);
}