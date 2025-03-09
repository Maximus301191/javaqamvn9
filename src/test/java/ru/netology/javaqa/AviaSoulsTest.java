package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AviaSoulsTest {
    AviaSouls manager = new AviaSouls();
    TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
    Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 10_000, 18, 20);
    Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 12_000, 10, 11);
    Ticket ticket3 = new Ticket("Екатеринбург", "Чита", 30_000, 11, 16);
    Ticket ticket4 = new Ticket("Казань", "Магнитогорск", 10_000, 12, 13);
    Ticket ticket5 = new Ticket("Сочи", "Новосибирск", 35_000, 14, 18);
    Ticket ticket6 = new Ticket("Москва", "Санкт-Петербург", 14_000, 16, 17);


    @BeforeEach
    public void AddAllTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void FindAll() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchTicketAll() {
        Ticket[] expected = {ticket1, ticket2, ticket6};
        Ticket[] actual = manager.search("Москва", "Санкт-Петербург");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchOneTicket() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.search("Екатеринбург", "Чита");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchNoTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Москва", "Чита");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void SearchAndSortByAll() {

        Ticket[] expected = {ticket2, ticket6, ticket1};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Санкт-Петербург", ticketTimeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchAndSortByOneTicket() {

        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.searchAndSortBy("Екатеринбург", "Чита", ticketTimeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchAndSortByNoTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Чита", ticketTimeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void CompareToMin() {
        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareToMax() {
        int expected = 1;
        int actual = ticket5.compareTo(ticket6);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareToEquals() {
        int expected = 0;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ComparatorMax() {
        int expected = 1;
        int actual = ticketTimeComparator.compare(ticket3, ticket1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ComparatorMin() {
        int expected = -1;
        int actual = ticketTimeComparator.compare(ticket2, ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ComparatorEquals() {
        int expected = 0;
        int actual = ticketTimeComparator.compare(ticket2, ticket6);

        Assertions.assertEquals(expected, actual);
    }
}
