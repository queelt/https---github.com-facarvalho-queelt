package org.queelt.common.page;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Page {

    private final Integer actualPage;
    private final Integer size;

    /**
     * Instantiates a new Page.
     *
     * @param actualPage the actual page
     * @param size       the size
     */
    public Page(final Integer actualPage, final Integer size) {
        this.actualPage = actualPage;
        this.size = size;
    }

    /**
     * Get actual actual page.
     *
     * @return the integer
     */
    public Integer getActualPage() {
        return actualPage;
    }

    /**
     * Get size.
     *
     * @return the integer
     */
    public Integer getSize() {
        return size;
    }

    @Override
    public boolean equals(final Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
