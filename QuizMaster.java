import java.util.*;

// ===============================================
// 🎓 QUIZMASTER – Multiple Choice Quiz Application
// ===============================================
class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    // Constructor
    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = Character.toUpperCase(correctAnswer);
    }

    // Display question and options
    public void displayQuestion() {
        System.out.println("\n" + questionText);
        System.out.println("A) " + options[0]);
        System.out.println("B) " + options[1]);
        System.out.println("C) " + options[2]);
        System.out.println("D) " + options[3]);
    }

    // Check if answer is correct
    public boolean isCorrect(char answer) {
        return Character.toUpperCase(answer) == correctAnswer;
    }
}

// ===============================================
// 💡 QUIZ CLASS – Contains logic and score tracking
// ===============================================
class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        loadQuestions();
    }

    // Load questions into the quiz
    private void loadQuestions() {
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"Paris", "Rome", "Madrid", "Berlin"}, 'A'
        ));
        questions.add(new Question(
            "Which programming language is used for Android development?",
            new String[]{"Python", "Java", "Swift", "C#"}, 'B'
        ));
        questions.add(new Question(
            "Who developed the Java language?",
            new String[]{"Microsoft", "Apple", "Sun Microsystems", "Google"}, 'C'
        ));
        questions.add(new Question(
            "What is 2 + 2?",
            new String[]{"3", "4", "5", "6"}, 'B'
        ));
        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"this", "super", "extends", "implements"}, 'C'
        ));
    }

    // Run the quiz
    public void startQuiz(String playerName) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n===== Welcome to QuizMaster, " + playerName + "! =====");
        System.out.println("Type A, B, C, or D to answer. Type 'Q' to quit anytime.\n");

        int qNo = 1;
        for (Question q : questions) {
            System.out.println("Question " + qNo + ":");
            q.displayQuestion();

            System.out.print("Your answer: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("👋 You chose to quit the quiz early.");
                break;
            }

            if (input.length() != 1 || !"ABCDabcd".contains(input)) {
                System.out.println("⚠️ Invalid choice. Skipping question.");
            } else {
                char ans = input.charAt(0);
                if (q.isCorrect(ans)) {
                    System.out.println("✅ Correct!");
                    score++;
                } else {
                    System.out.println("❌ Incorrect! Correct answer: " + q.correctAnswer);
                }
            }
            qNo++;
        }

        displayResult(playerName);
    }

    // Display result summary
    private void displayResult(String playerName) {
        System.out.println("\n===== QUIZ SUMMARY =====");
        System.out.println("Player: " + playerName);
        System.out.println("Total Questions: " + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Your Score: " + score + " / " + questions.size());

        double percentage = (score * 100.0) / questions.size();
        System.out.printf("Percentage: %.2f%%\n", percentage);

        if (percentage >= 80)
            System.out.println("🏆 Excellent work!");
        else if (percentage >= 50)
            System.out.println("👍 Good job! Keep practicing!");
        else
            System.out.println("📚 Keep learning! You can do better.");
    }
}

// ===============================================
// 🚀 MAIN CLASS – Entry Point
// ===============================================
public class QuizMaster {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== QUIZMASTER =====");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        Quiz quiz = new Quiz();
        quiz.startQuiz(name);
    }
}
