package com.company;

import com.company.data.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
private JobFile data;//переменная для работы с JobFile
public MyTableModel(JobFile jobFile)
{
    this.data = jobFile;
}
    @Override
    public int getRowCount() {
        return data.getCount();
    }//Количество строк

    @Override
    public String getColumnName (int column) {//Название столбцов
        switch (column)
        {
            case 0:return "Тип животного";
            case 1:return "Название животного";
            case 2:return "Количество видов";
            case 3:return "Характеристика";

        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {//Информация в таблице
        switch (columnIndex)
        {
            case 0:return data.getAnimals(rowIndex).Info();
            case 1:return data.getAnimals(rowIndex).getName();
            case 2:return data.getAnimals(rowIndex).getNumberVid();
            case 3:
                Animals a = data.getAnimals(rowIndex);
                if (a instanceof Mlekopit)
                    return ((Mlekopit) a).getSkinn();
                if (a instanceof ParnoKopit)
                    return ((ParnoKopit) a).getRoga();
                if (a instanceof Beards)
                    return ((Beards) a).getPerel();


        }
        return "default";
    }

    @Override
    public int getColumnCount() {
        return 4;
    }//Количество столбцов

    @Override
    public Class<?> getColumnClass(int columnIndex) {//Тип возвращаемый в столбцы
        switch (columnIndex)
        {
            case 1:return String.class;
            case 2:return Integer.class;
            case 3:return String.class;



        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {//разрешаем редактировать
        switch (columnIndex)
        {
            case 1:return true;
            case 2:return true;
            case 3:return true;

        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {//Редактирование
        switch (columnIndex)
        {
            case 0:
                break;
            case 1:data.getAnimals(rowIndex).setName((String) aValue);
                break;
            case 2:data.getAnimals(rowIndex).setNumberVid((Integer) aValue);
                break;
            case 3:
                Animals a = data.getAnimals(rowIndex);
                if (a instanceof Mlekopit){
                    ((Mlekopit) a).setSkinn((String)aValue);
                }else {
                    if (a instanceof ParnoKopit){
                        ((ParnoKopit) a).setRoga((String)aValue);
                    }else {
                        if (a instanceof Beards){
                            ((Beards) a).setPerel((String)aValue);
                        }

                    }

                }


        }
    }

    public void delete(int ind)//удаление строк
    {
        this.data.remove(ind);
        fireTableDataChanged();
    }
    public void AnimalAdd(int type,String name, int numbervid, String DopParametr)//добавление животных
    {
        switch (type)
        {
            case 0:data.addAnimals(new Mlekopit(name, numbervid, DopParametr));
            break;
            case 1:data.addAnimals(new ParnoKopit(name, numbervid, DopParametr));
            break;
            case 2:data.addAnimals(new Beards(name, numbervid, DopParametr));
            break;
        }
        fireTableDataChanged();
    }


}
