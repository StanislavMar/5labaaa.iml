package com.company.data;

import java.util.ArrayList;
import java.util.Scanner;

public class JobFile {
    protected ArrayList<Animals> el = new ArrayList <Animals>();
    public JobFile() {
        el.add(new Mlekopit("Медведь", 100, "Бурый"));
        el.add(new ParnoKopit("Олень", 3000, "Самка"));
        el.add(new Beards("Орел", 12, "Окрас сине-зеленый"));
        el.add(new Beards("Колибри", 12, "Серый"));
        el.add(new Beards("Собака", 12, "Окрас сине-зеленый"));
        el.add(new Beards("Колибри", 12, "Окрас сине-зеленый"));
    }
    public int getCount()
    {
        return this.el.size();
    }//количество элементов
    public Animals getAnimals(int index)
    {
        return el.get(index);
    }//возвращает индекс элемента
    public void addAnimals(Animals animals)
    {
        el.add(animals);
    }//добавляет элемент
    public void remove(int ind)
    {
        this.el.remove(ind);
    }//удаление строки
    }

