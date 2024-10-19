package abdulrahmanhamdi.quiz;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button A, B, C, D;
    Button submitBtn;
    int score = 0;
    int total = QuestionAnswer.question.length;
    int current = 0;
    String selectAns = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.question);
        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        submitBtn =findViewById(R.id.submit_btn);


        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalQuestionsTextView.setText("عدد الأسئلة: " + total);
        loadNewQuestion();



    }

    @Override
    public void onClick(View v) {
        A.setBackgroundColor(Color.WHITE);
        B.setBackgroundColor(Color.WHITE);
        C.setBackgroundColor(Color.WHITE);
        D.setBackgroundColor(Color.WHITE);


        Button clickedButton = (Button) v;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectAns.equals(QuestionAnswer.correctAnswers[current])) {
                score++;
                questionTextView.setBackgroundColor(Color.GREEN);
            } else {
                questionTextView.setBackgroundColor(Color.RED);
            }
            current++;
            loadNewQuestion();
        } else {
            selectAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }
    void loadNewQuestion(){
        if (current == total){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[current]);
        A.setText(QuestionAnswer.choices[current][0]);
        B.setText(QuestionAnswer.choices[current][1]);
        C.setText(QuestionAnswer.choices[current][2]);
        D.setText(QuestionAnswer.choices[current][3]);

    }
    void finishQuiz(){
        String passed = "";
        if (score>total*0.60){
            passed = "Passed";
        }else{
            passed = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passed)
                .setMessage("Score is "+score+ " out of "+total)
                .setPositiveButton("Restart",((dialog, which) -> restartQuiz()))
                .setCancelable(false)
                .show();

    }
    void restartQuiz(){
        score = 0;
        current = 0;
        loadNewQuestion();
    }
}