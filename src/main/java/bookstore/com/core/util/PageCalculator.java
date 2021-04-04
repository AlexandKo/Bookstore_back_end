package bookstore.com.core.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class PageCalculator {
    private final int DEFAULT_ELEMENTS_ON_PAGE = 10;
    private final int INITIALIZATION_SET_FIRST_PAGE = 1;
    private final int OFFSET_FIRST_PAGE = 0;
    private int currentPage = INITIALIZATION_SET_FIRST_PAGE;
    private int currentOffset = OFFSET_FIRST_PAGE;
    @Getter
    @Setter
    private int elementsOnPageQuantity = DEFAULT_ELEMENTS_ON_PAGE;

    public int calculatePageOffset(int newPageNumber) {
        int newOffset;
        int pagesOffset = newPageNumber - currentPage;
        if (pagesOffset > 0) {
            newOffset = currentOffset + (pagesOffset * elementsOnPageQuantity);
        } else {
            pagesOffset = currentPage - newPageNumber;
            newOffset = currentOffset - (pagesOffset * elementsOnPageQuantity);
        }
        currentPage = newPageNumber;
        currentOffset = newOffset;

        return newOffset;
    }

    public int getPageCount(long elementsCount) {
        return (int) Math.ceil((double) elementsCount / elementsOnPageQuantity);
    }
}
