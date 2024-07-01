import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a single question in the quiz
class Question {
    String question;
    List<String> options;
    int correctAnswer;

    Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

// Class representing a quiz containing multiple questions
class Quiz {
    String title;
    List<Question> questions;

    Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}

// Class managing quizzes and user interactions
class QuizManager {
    List<Quiz> quizzes;
    Scanner scanner;

    QuizManager() {
        quizzes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to create a new quiz
    void createQuiz() {
        System.out.println("Enter quiz title: ");
        String title = scanner.nextLine();
        Quiz quiz = new Quiz(title);
        quizzes.add(quiz);

        while (true) {
            System.out.println("Add a question? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break;
            }

            System.out.println("Enter the question: ");
            String questionText = scanner.nextLine();

            List<String> options = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                System.out.println("Enter option " + i + ": ");
                options.add(scanner.nextLine());
            }

            System.out.println("Enter the number of the correct option: ");
            int correctAnswer = Integer.parseInt(scanner.nextLine());

            Question question = new Question(questionText, options, correctAnswer);
            quiz.addQuestion(question);
        }
    }

    // Method to take a quiz
    void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Select a quiz:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).title);
        }

        int quizChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Quiz selectedQuiz = quizzes.get(quizChoice);

        int score = 0;
        for (Question question : selectedQuiz.questions) {
            System.out.println(question.question);
            for (int i = 0; i < question.options.size(); i++) {
                System.out.println((i + 1) + ". " + question.options.get(i));
            }

            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == question.correctAnswer) {
                score++;
            }
        }

        displayResults(score, selectedQuiz.questions.size());
    }

    // Method to display quiz results
    void displayResults(int score, int total) {
        System.out.println("You scored: " + score + " out of " + total);
        if (score == total) {
            System.out.println("Excellent! Perfect score!");
        } else if (score >= total / 2) {
            System.out.println("Good job! You passed!");
        } else {
            System.out.println("Better luck next time!");
        }
    }

    // Method to display available commands
    void displayCommands() {
        System.out.println("Commands: create, take, exit");
    }

    // Method to start the application
    void start() {
        while (true) {
            displayCommands();
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "create":
                    createQuiz();
                    break;
                case "take":
                    takeQuiz();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
    }
}

// Main class to run the application
public class QuizApp {
    public static void main(String[] args) {
        QuizManager quizManager = new QuizManager();
        quizManager.start();
    }
}
