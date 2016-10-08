package org.queelt.common.page;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PageList<T> {

	private static final Integer FIRST_PAGE = 0;

	private List<T> list;
	private Page nextPage;
	private Page previousPage;
	private Page first;
	private Page last;
	private Page self;

	/**
	 * Instantiates a new Page list.
	 *
	 * @param number
	 *            the number
	 * @param size
	 *            the size
	 * @param total
	 *            the total
	 * @param nextPage
	 *            the next page
	 * @param previousPage
	 *            the previous page
	 * @param list
	 *            the list
	 */
	public PageList(final int number, final int size, final int total, final Page nextPage, final Page previousPage,
			final List<T> list) {

		this.list = list;
		self = createSelfPage(number, size);
		first = createFirstPage(size);
		if (nextPage != null) {
			this.nextPage = nextPage;
		}
		if (previousPage != null) {
			this.previousPage = previousPage;
		}
		last = createLastPage(total - 1, size);
	}

	private Page createSelfPage(final Integer page, final Integer size) {
		return new Page(page, size);
	}

	private Page createFirstPage(final Integer size) {
		return new Page(FIRST_PAGE, size);
	}

	private Page createLastPage(final Integer last, final Integer size) {
		return new Page(last, size);
	}

	public List<T> getList() {
		return list;
	}

	public Page getSelfPage() {
		return self;
	}

	public Page getNextPage() {
		return nextPage;
	}

	public Page getPreviousPage() {
		return previousPage;
	}

	public Page getFirstPage() {
		return first;
	}

	public Page getLastPage() {
		return last;
	}

	public Boolean hasNext() {
		return nextPage != null;
	}

	public Boolean hasPrevious() {
		return previousPage != null;
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
