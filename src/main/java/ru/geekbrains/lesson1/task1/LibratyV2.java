package ru.geekbrains.lesson1.task1;

import java.util.ArrayList;
import java.util.List;

public class LibratyV2 {

    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("Преступление и наказание", "Фёдор Достоевский", 1866),
                new Book("Евгений Онегин", "Александр Пушкин", 1833),
                new Book("Война и мир", "Лев Толстой", 1869),
                new Book("Мастер и Маргарита", "Михаил Булгаков", 1967),
                new Book("Война и мир", "Лев Толстой", 1869)
        );

        System.out.println("Книги Льва Толстого:\n" + books.stream()
                .filter(book -> "Лев Толстой".equals(book.getAuthor()))
                .toList());
        System.out.println("Книги опубликованные после 1866 года:\n" +
                books.stream().filter(book -> book.getYear() > 1866).toList());
        System.out.println("Уникальные наименования книг:\n" +
                books.stream()
                        .map(Book::getTitle)
                        .map(s -> s.length())
                        .distinct()
                        .map(num -> new Employee(num))
                        .toList());


    }

}
