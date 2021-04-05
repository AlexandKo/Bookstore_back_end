package bookstore.com.core.response;

import lombok.Getter;

public class UpdateBookResponse {
    @Getter
    private final int updateBooksCount;

    public UpdateBookResponse(int updateBooksCount) {
        this.updateBooksCount = updateBooksCount;
    }
}