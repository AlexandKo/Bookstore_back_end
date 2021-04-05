package bookstore.com.core.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageCalculator {
    private final int INITIALIZATION_SET_FIRST_PAGE = 1;
    private final int OFFSET_FIRST_PAGE = 0;
    private int currentPage = INITIALIZATION_SET_FIRST_PAGE;
    private int currentOffset = OFFSET_FIRST_PAGE;
    @Getter
    @Setter
    @Value("${element.on.page.quantity}")
    private int elementsOnPageQuantity;

    public int calculatePageOffset(int newPageNumber) {
        final int PAGE_POSITION = 0;
        int newOffset;

        int pageOffset = newPageNumber - currentPage;
        if (pageOffset > PAGE_POSITION) {
            newOffset = currentOffset + (pageOffset * elementsOnPageQuantity);
        } else {
            pageOffset = currentPage - newPageNumber;
            newOffset = currentOffset - (pageOffset * elementsOnPageQuantity);
        }
        currentPage = newPageNumber;
        currentOffset = newOffset;

        return newOffset;
    }

    public int getPageCount(long elementsCount) {
        return (int) Math.ceil((double) elementsCount / elementsOnPageQuantity);
    }
}