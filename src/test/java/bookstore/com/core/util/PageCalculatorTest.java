package bookstore.com.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageCalculatorTest {

    @Test
    public void startPage_1_newPage_1() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageOffset = pageCalculator.calculatePageOffset(1);

        assertEquals(0, pageOffset);
    }

    @Test
    public void startPage_1_newPage_2() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageOffset = pageCalculator.calculatePageOffset(2);

        assertEquals(50, pageOffset);
    }

    @Test
    public void startPage_1_newPage_3() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageOffset = pageCalculator.calculatePageOffset(3);

        assertEquals(100, pageOffset);
    }

    @Test
    public void startPage_4_newPage_1() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        pageCalculator.calculatePageOffset(2);
        pageCalculator.calculatePageOffset(3);
        pageCalculator.calculatePageOffset(4);
        int pageOffset = pageCalculator.calculatePageOffset(1);

        assertEquals(0, pageOffset);
    }

    @Test
    public void startPage_4_newPage_2() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        pageCalculator.calculatePageOffset(2);
        pageCalculator.calculatePageOffset(3);
        pageCalculator.calculatePageOffset(4);
        int pageOffset = pageCalculator.calculatePageOffset(2);

        assertEquals(50, pageOffset);
    }

    @Test
    public void startPage_4_newPage_3() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        pageCalculator.calculatePageOffset(2);
        pageCalculator.calculatePageOffset(3);
        pageCalculator.calculatePageOffset(4);
        int pageOffset = pageCalculator.calculatePageOffset(3);

        assertEquals(100, pageOffset);
    }

    @Test
    public void getPageCount_test1() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageCount = pageCalculator.getPageCount(10);

        assertEquals(1, pageCount);
    }

    @Test
    public void getPageCount_test2() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageCount = pageCalculator.getPageCount(2);

        assertEquals(1, pageCount);
    }

    @Test
    public void getPageCount_test3() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageCount = pageCalculator.getPageCount(79);

        assertEquals(2, pageCount);
    }

    @Test
    public void getPageCount_test4() {
        PageCalculator pageCalculator = new PageCalculator();
        pageCalculator.setElementsOnPageQuantity(50);
        int pageCount = pageCalculator.getPageCount(124);

        assertEquals(3, pageCount);
    }
}