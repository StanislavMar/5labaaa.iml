package com.company.vew;

import com.company.Main;
import com.company.MyTableModel;
import com.company.data.JobFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    private JTable jTable;
    private MyTableModel myTableModel;
    private JScrollPane jScrollPane;
    private TableRowSorter sorter;
    private JButton jButtonDel, jButtonAdd,jButtonPrin,jButtonOtch;
    private JComboBox jComboBox;
    private JTextField jTextFieldName, jTextFieldNumber,jTextFieldCharakt, jTextFieldSearch;
    private JLabel jLabelSearch,jLabel1,jLabelADD2, jLabel3;
    private JPanel jPanel,jPanelSearch; //объявление переменных

    public MainWindow()
    {
    super("Зоопарк");
    myTableModel = new MyTableModel(new JobFile());
    jTable = new JTable();
    jTable.setModel(myTableModel);//таблица


    jScrollPane = new JScrollPane(jTable);//наш скролл


    jButtonDel = new JButton("Удаление животного");
    jButtonAdd = new JButton("Добавление животного");//Кнопки на главной

    jComboBox = new JComboBox(new String[]{
            "Млекопитающие",
            "Парнокопытные",
            "Птицы"//Выплывающий список на добавлении
    });
    jLabelADD2 = new JLabel("Название животного");//лейбл на добавлении
        jTextFieldSearch = new JTextField("",15);//field на поиске
        jLabelSearch = new JLabel("Поиск:");//лейб на поиске
        sorter = new TableRowSorter(jTable.getModel());//класс для сортировки(поиск)
        jTable.setRowSorter(sorter);
        jPanelSearch = new JPanel(new FlowLayout());//Панель на поиске
        jPanelSearch.add(jLabelSearch);//лейбл на поиске
        jPanelSearch.add(jTextFieldSearch);//field на поиске
        jButtonOtch = new JButton("Отчистить");
        jPanelSearch.add(jButtonOtch);//кнопка отчистки


    jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {//слушатель для того, что поиск сделать динамическим
        @Override
        public void insertUpdate(DocumentEvent e) {
            try {

                String text = jTextFieldSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            catch (java.util.regex.PatternSyntaxException ex)
            {

            }
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {

                String text = jTextFieldSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            catch (java.util.regex.PatternSyntaxException ex)
            {

            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    });
    jButtonOtch.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            jTextFieldSearch.setText("");
        }
    });

    jButtonDel.addActionListener(new ActionListener() {//слушатель для кнопки удаления
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int modelRow = jTable.convertRowIndexToModel(jTable.getSelectedRow());
                myTableModel.delete(modelRow);

            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                JDialog jDErrorDel = new JDialog(MainWindow.this,"Выделите строку для удаления", true);
                jDErrorDel.setLocationRelativeTo(null);
                jDErrorDel.setSize(250,0);
                jDErrorDel.setResizable(false);
                jDErrorDel.setVisible(true);
            }
        }
    });
    jButtonAdd.addActionListener(new ActionListener() {//слушатель на кнопку добавления
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicAdd();
        }
    });


    jPanel = new JPanel(new FlowLayout());//панель для кнопок на главной
    jPanel.add(jButtonAdd);
    jPanel.add(jButtonDel);


    this.add(jPanel, BorderLayout.SOUTH);
    this.add(jPanelSearch,BorderLayout.NORTH);
    this.add (jScrollPane);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setMinimumSize(new Dimension(500,300));
    this.setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
    public void LogicAdd(){
        jButtonPrin = new JButton("Принять");//кнопка принять

        jTextFieldName = new JTextField("",15);//field на окне добавления "название животного"
        jTextFieldNumber = new JTextField("",15);//field "кол-во видов"
        jTextFieldNumber.addKeyListener(new KeyAdapter() {//слушатель на нажатие с клавы
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        jTextFieldCharakt = new JTextField("",15);//field "характеристика"
        jLabel1 = new JLabel("Название животного");
        jLabelADD2 = new JLabel("Кол-во видов");
        jLabel3 = new JLabel("Характеристика(необяз.)");



        jButtonPrin.addActionListener(new ActionListener() {//слушатель на кнопку принять
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myTableModel.AnimalAdd(jComboBox.getSelectedIndex(), jTextFieldName.getText(), Integer.parseInt(jTextFieldNumber.getText()),jTextFieldCharakt.getText());
                //добавление в таблицу нового животного
                }
                catch (NumberFormatException ex)
                {
                    JDialog jDialogErrorAdd = new JDialog(MainWindow.this,"Заполните поля", true);
                    jDialogErrorAdd.setLocationRelativeTo(null);
                    jDialogErrorAdd.setSize(250,0);
                    jDialogErrorAdd.setResizable(false);
                    jDialogErrorAdd.setVisible(true);
                }
            }
        });

        JPanel grid = new JPanel(new GridLayout(8,1,0,0));
        grid.add(jComboBox);
        grid.add(jLabel1);
        grid.add(jTextFieldName);
        grid.add(jLabelADD2);
        grid.add(jTextFieldNumber);
        grid.add(jLabel3);
        grid.add(jTextFieldCharakt);
        grid.add(jButtonPrin);
        jPanel = new JPanel(new FlowLayout());
        jPanel.add(grid);
        Container container = getContentPane();
        container.add(jPanel);
        //панель для нашего окна добавления


        JDialog jDialogADD = new JDialog(MainWindow.this,"Окно добавления", true);
        jDialogADD.add(jPanel);
        jDialogADD.setSize(200,260);
        jDialogADD.setLocationRelativeTo(null);
        jDialogADD.setMinimumSize(new Dimension(200,260));
        jDialogADD.setVisible(true);
        //Окно добавления



    }

}
