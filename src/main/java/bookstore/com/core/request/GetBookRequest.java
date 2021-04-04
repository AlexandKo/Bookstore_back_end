package bookstore.com.core.request;

import lombok.Getter;

public class GetBookRequest {
    @Getter
    private final int pageNumber;

    public GetBookRequest(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}