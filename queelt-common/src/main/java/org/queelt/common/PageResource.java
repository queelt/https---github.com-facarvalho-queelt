package org.queelt.common;

import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


public class PageResource<T> extends ResourceSupport implements Page<T> {
	
	private final Page<T> page;
	
	public PageResource(Page<T> page, String pageParam,
			String sizeParam) {
		super();
		this.page = page;
		if(page.hasPrevious()) {
			String path = createBuilder()
				.queryParam(pageParam,page.getNumber()-1)
				.queryParam(sizeParam,page.getSize())
				.build()
				.toUriString();
			Link link = new Link(path, Link.REL_PREVIOUS);
			add(link);
		}
		if(page.hasNext()) {
			String path = createBuilder()
				.queryParam(pageParam,page.getNumber()+1)
				.queryParam(sizeParam,page.getSize())
				.build()
				.toUriString();
			Link link = new Link(path, Link.REL_NEXT);
			add(link);
		}
		
		Link link = buildPageLink(pageParam,0,sizeParam,page.getSize(),Link.REL_FIRST);
		add(link);
		
		int indexOfLastPage = page.getTotalPages() - 1;
		link = buildPageLink(pageParam,indexOfLastPage,sizeParam,page.getSize(),Link.REL_LAST);
		add(link);
		
		link = buildPageLink(pageParam,page.getNumber(),sizeParam,page.getSize(),Link.REL_SELF);
		add(link);
	}
	
	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}
	
	private Link buildPageLink(String pageParam,int page,String sizeParam,int size,String rel) {
		String path = createBuilder()
				.queryParam(pageParam,page)
				.queryParam(sizeParam,size)
				.build()
				.toUriString();
		Link link = new Link(path,rel);
		return link;
	}
	
	public int getNumber() {
		return page.getNumber();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	public long getTotalElements() {
		return page.getTotalElements();
	}

	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean hasNext() {
		return page.hasNext();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public Iterator<T> iterator() {
		return page.iterator();
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public boolean hasContent() {
		return page.hasContent();
	}

	public Sort getSort() {
		return page.getSort();
	}

	
	public Pageable nextPageable() {
		return page.nextPageable();
	}

	public Pageable previousPageable() {
		return page.previousPageable();
	}

	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		
		return page.map(converter);
	}
	
}