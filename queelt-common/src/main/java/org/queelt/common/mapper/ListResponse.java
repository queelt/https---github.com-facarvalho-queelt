package org.queelt.common.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.queelt.common.page.PageList;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type List response.
 *
 */
@SuppressWarnings("rawtypes")
public class ListResponse<T> extends ResourceSupport {

    @JsonIgnore
    private static final String PARAM_PAGE = "page";
    @JsonIgnore
    private static final String PARAM_SIZE = "size";

    private List<T> items;

    /**
     * Instantiates a new List response.
     *
     * @param pageList the page list
     */
    public ListResponse(final PageList pageList) {
        add(buildPageLink(pageList.getSelfPage().getActualPage(), pageList.getSelfPage().getSize(), Link.REL_SELF));
        add(buildPageLink(pageList.getFirstPage().getActualPage(), pageList.getFirstPage().getSize(), Link.REL_FIRST));
        if (pageList.hasPrevious()) {
            add(buildPageLink(pageList.getPreviousPage().getActualPage(), pageList.getPreviousPage().getSize(),
                    Link.REL_PREVIOUS));
        }
        if (pageList.hasNext()) {
            add(buildPageLink(pageList.getNextPage().getActualPage(), pageList.getNextPage().getSize(), Link.REL_NEXT));
        }
        add(buildPageLink(pageList.getLastPage().getActualPage(), pageList.getLastPage().getSize(), Link.REL_LAST));
    }

    private Link buildPageLink(final Integer page, final Integer size, final String rel) {
        String path = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .queryParam(PARAM_PAGE, page)
                .queryParam(PARAM_SIZE, size)
                .build()
                .toUriString();

        return new Link(path, rel);
    }

    public List<T> getItems() {
        return items;
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(final T item) {
        if (getItems() == null) {
            items = new ArrayList<T>();
        }
        getItems().add(item);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
