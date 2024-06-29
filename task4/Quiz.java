import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
import java.util.Arrays;


 class QuizQuestion {
    String question;
    List<String> options;
    int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

 class QuizTimer {
    private Timer timer;
    private int seconds;
    private Runnable timeUpCallback;

    public QuizTimer(int seconds, Runnable timeUpCallback) {
        this.seconds = seconds;
        this.timeUpCallback = timeUpCallback;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUpCallback.run();
            }
        }, seconds * 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}

 class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private QuizTimer quizTimer;
    private Scanner scanner;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion question = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            startTimer();
            getUserAnswer();
        } else {
            displayResults();
        }
    }

    private void startTimer() {
        quizTimer = new QuizTimer(30, this::timeUp);
        quizTimer.start();
    }

    private void timeUp() {
        System.out.println("Time's up!");
        currentQuestionIndex++;
        displayNextQuestion();
    }

    private void getUserAnswer() {
        System.out.print("Your answer: ");
        try {
            int answerIndex = Integer.parseInt(scanner.nextLine());
            submitAnswer(answerIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            getUserAnswer();
        }
    }

    private void submitAnswer(int answerIndex) {
        quizTimer.stop();
        if (questions.get(currentQuestionIndex).getCorrectAnswerIndex() == answerIndex - 1) {
            score++;
        }
        currentQuestionIndex++;
        displayNextQuestion();
    }

    private void displayResults() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Quiz completed!");
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Correct answer: " + question.getOptions().get(question.getCorrectAnswerIndex()));
        }
        System.out.println();
         System.out.println("---------------------------------------------------------------");
    }

     public static void main(String[] args) {

        List<QuizQuestion> questions = new ArrayList<>();
        System.out.println("---------------------------------------------------------------");
        questions.add(new QuizQuestion("What is the capital of France?", Arrays.asList("Paris", "London", "Berlin", "Rome"), 0));
       
        questions.add(new QuizQuestion("What is 2 + 2 = ?", Arrays.asList("3", "4", "5", "6"), 1));
     

         questions.add(new QuizQuestion("Overridden belongs to which of these ?", Arrays.asList("Polimorphism", "Encapsulation", "Inheritance", "Abstraction"), 0));
       
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}