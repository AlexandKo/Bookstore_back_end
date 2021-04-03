package bookstore.com.core.response;

import lombok.Getter;

public class AddBookResponse {
    @Getter
    private final boolean isWasSaved;

    public AddBookResponse(boolean isWasSaved) {
        this.isWasSaved = isWasSaved;
    }
}
