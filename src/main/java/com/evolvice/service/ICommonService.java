package com.evolvice.service;

import org.springframework.data.domain.PageRequest;

public interface ICommonService {

	final int defaultPageSize = 25;

	default PageRequest gotoPage(int... pageDetails) {
		int pageNumber = pageDetails.length > 0 ? pageDetails[0] : 1;
		int pageSize = pageDetails.length == 2 ? pageDetails[1] : defaultPageSize;
		return PageRequest.of(pageNumber - 1, pageSize);
	}
}