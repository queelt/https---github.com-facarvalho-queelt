package org.queelt.common.mapper;

import org.queelt.common.page.PageList;

public interface ListResponseMapper<O, I> {

    /**
     * Map list.
     *
     * @param input the i
     * @return the list response
     */
    ListResponse<O> mapList(PageList<I> input);
}