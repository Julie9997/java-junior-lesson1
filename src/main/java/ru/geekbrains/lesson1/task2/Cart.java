package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        final boolean[] proteins = {false};
        final boolean[] fats = {false};
        final boolean[] carbohydrates = {false};

        proteins[0] = foodstuffs.stream().anyMatch(Food::getProteins);
        fats[0] = foodstuffs.stream().anyMatch(Food::getFats);
        carbohydrates[0] = foodstuffs.stream().anyMatch(Food::getCarbohydrates);

        if (proteins[0] && fats[0] && carbohydrates[0]) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(clazz).stream()
                .filter(thing -> !proteins[0] && thing.getProteins())
                .findFirst()
                .ifPresent(thing -> {
                    proteins[0] = true;
                    foodstuffs.add(thing);
                });

        market.getThings(clazz).stream()
                .filter(thing -> !fats[0] && thing.getFats())
                .findFirst()
                .ifPresent(thing -> {
                    fats[0] = true;
                    foodstuffs.add(thing);
                });

        market.getThings(clazz).stream()
                .filter(thing -> !carbohydrates[0] && thing.getCarbohydrates())
                .findFirst()
                .ifPresent(thing -> {
                    carbohydrates[0] = true;
                    foodstuffs.add(thing);
                });

        if (proteins[0] && fats[0] && carbohydrates[0])
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
    }


    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }

}
