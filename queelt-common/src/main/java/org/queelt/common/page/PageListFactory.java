package org.queelt.common.page;

import java.util.List;

import org.queelt.common.annotation.ServiceLayer;
import org.springframework.data.domain.Pageable;

@ServiceLayer
public class PageListFactory<T> {

	public PageList<T> getPageList(@SuppressWarnings("rawtypes") final org.springframework.data.domain.Page page, final List<T> list) {
		return createPageList(page, list);
	}

	private PageList<T> createPageList(@SuppressWarnings("rawtypes") final org.springframework.data.domain.Page page, final List<T> list) {
		Page previousPage = createPage(page.previousPageable());
		Page nextPage = createPage(page.nextPageable());
		return new PageList<T>(page.getNumber(), page.getSize(), page.getTotalPages(), nextPage, previousPage, list);
	}

	private Page createPage(final Pageable pageable) {
		if (pageable == null) {
			return null;
		}
		return new Page(pageable.getPageNumber(), pageable.getPageSize());
	}
}
