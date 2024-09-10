package backend.academy.hangman.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGame {
    private static JFrame frame;
    private static JTextArea gameArea;
    private static JTextField inputField;
    private static JLabel attemptsLabel;
    private static String word = "Саид петух"; // Загаданное слово
    private static String guessedLetters = ""; // Угаданные буквы
    private static int attemptsLeft = 5; // Количество попыток

    // Обновляем экран
    public static void displayGame() {
        gameArea.setText(""); // Очищаем область текста
        gameArea.append("=== Игра Виселица ===\n");
        gameArea.append("Попыток осталось: " + attemptsLeft + "\n");
        gameArea.append("Загаданное слово: ");

        // Показываем текущее состояние угаданного слова
        for (char c : word.toCharArray()) {
            if (guessedLetters.indexOf(c) >= 0) {
                gameArea.append(c + " ");
            } else {
                gameArea.append("_ ");
            }
        }
        gameArea.append("\n");
    }

    public static void main(String[] args) {
        // Создаем главное окно
        frame = new JFrame("Игра Виселица");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Создаем текстовую область для отображения состояния игры
        gameArea = new JTextArea();
        gameArea.setEditable(false);
        gameArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(gameArea);

        // Поле для ввода букв
        inputField = new JTextField(1);
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 16));

        // Лейбл для отображения попыток
        attemptsLabel = new JLabel("Попыток осталось: " + attemptsLeft);
        attemptsLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));

        // Панель для размещения элементов
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);
        panel.add(attemptsLabel, BorderLayout.NORTH);

        // Добавляем панель в окно
        frame.add(panel);
        frame.setVisible(true);

        // Обрабатываем ввод букв
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = inputField.getText().toLowerCase();
                inputField.setText(""); // Очищаем поле ввода

                if (guess.length() != 1) {
                    gameArea.append("Введите только одну букву!\n");
                    return;
                }

                char guessedChar = guess.charAt(0);
                if (word.indexOf(guessedChar) >= 0) {
                    guessedLetters += guessedChar; // Добавляем угаданную букву
                } else {
                    attemptsLeft--; // Уменьшаем количество попыток
                }

                // Проверяем, угадано ли всё слово
                boolean allGuessed = true;
                for (char c : word.toCharArray()) {
                    if (guessedLetters.indexOf(c) < 0) {
                        allGuessed = false;
                        break;
                    }
                }

                // Обновляем экран
                displayGame();

                // Проверка на выигрыш или проигрыш
                if (allGuessed) {
                    gameArea.append("Поздравляем, вы угадали слово!\n");
                    inputField.setEditable(false); // Блокируем дальнейший ввод
                } else if (attemptsLeft == 0) {
                    gameArea.append("Вы проиграли! Загаданное слово было: " + word + "\n");
                    inputField.setEditable(false); // Блокируем дальнейший ввод
                }

                attemptsLabel.setText("Попыток осталось: " + attemptsLeft);
            }
        });

        // Отображаем начальное состояние игры
        displayGame();
    }
}
