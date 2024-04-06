package com.ocado.basket;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class BasketSplitterTest extends TestCase {
    static String pathToBasket1 = "C:/Java/Java Stack/basket-splitter/src/test/resources/basket-1.json";
    static String pathToBasket2 = "C:/Java/Java Stack/basket-splitter/src/test/resources/basket-2.json";
    static List<String> basket1 = new ArrayList<>();
    static List<String> basket2 = new ArrayList<>();
    BasketSplitter basketSplitter = new BasketSplitter(
            "C:/Java/Java Stack/basket-splitter/src/main/resources/config.json");

    @BeforeAll
    static void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            basket1 = objectMapper.readValue(new File(pathToBasket1), List.class);
            basket2 = objectMapper.readValue(new File(pathToBasket2), List.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testConfigurationLoad() {
        assertNotNull(basketSplitter.getConfigurationList());
    }
    @Test
    public void testSplitMethodShouldReturnProperValues() {
        String resultFor1Basket = "{Courier=[Cocoa Butter, Tart - Raisin And Pecan, Table Cloth 54x72 White, " +
                "Flower - Daisies, Cookies - Englishbay Wht], Mailbox delivery=[Fond - Chocolate]}";
        String resultFor2Basket = "{Pick-up point=[Sauce - Mint, Numi - Assorted Teas], " +
                "Same day delivery=[Garlic - Peeled], Courier=[Cake - Miini Cheesecake Cherry], " +
                "Express Collection=[Fond - Chocolate, Chocolate - Unsweetened, Nut - Almond, Blanched, Whole, " +
                "Haggis, Mushroom - Porcini Frozen, Longan, Bag Clear 10 Lb, Nantucket - Pomegranate Pear," +
                " Puree - Strawberry, Apples - Spartan, Cabbage - Nappa, Bagel - Whole White Sesame, Tea - Apple Green Tea]}";
        assertEquals(basketSplitter.split(basket1).toString(), resultFor1Basket);
        assertEquals(basketSplitter.split(basket2).toString(), resultFor2Basket);
    }
    @Test
    public void testSplitMethodShouldThrowExeption() {
        assertThrows(IllegalArgumentException.class, () -> basketSplitter.split(new ArrayList<>()));
    }
    @Test
    public void testBasketSplitterConstructorShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new BasketSplitter(""));
    }
}
