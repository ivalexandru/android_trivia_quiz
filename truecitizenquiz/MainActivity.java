package com.bawp.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private TextView questionTextView;
    private Button nextButton;

    private int currentQuestionIndex = 0 ;

    private Question[] questionBank = new Question[] {

            new Question(R.string.question_amendments, false),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_religion, true),


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.answer_text_view);
nextButton = findViewById(R.id.next_button);

//varianta pt onClick in care nu mai pui in xml chestii:
        //this = clasa MainActivity
        //dar tre sa implementam ceva pt a functiona astfel
        //tu tastezi doar asta:
//        falseButton.setOnClickListener(this);
//        apoi click in stanga la tips si "make MainActivity implement ..OnClickListener"

        //deci, INTREAGA CLASA VA ASCULTA PT ONCLICK
        //IAR LA CLICK metoda onClick va fi overridden
        //si se va executa codul tau de il pui mai jos
falseButton.setOnClickListener(this);
trueButton.setOnClickListener(this);
nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
break;

//go to next question
            case R.id.next_button:
                // 1%8 = 1, pt ca 8 intra de zero ori in 1, asa ca de la zero, pt a ajunge la 1, ne trebuie 1
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
                break;

            default:
                Toast.makeText(MainActivity.this, "nu mai e nimic", Toast.LENGTH_LONG).show();
        }
    }

    private void updateQuestion(){
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }

    // isAnswerTrue e definit de mine in clasa Question, returneaza fieldul boolean din array
    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId;

        if (userChooseCorrect == answerIsTrue){
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(
                MainActivity.this,
                toastMessageId,
                Toast.LENGTH_SHORT
        ).show();
    }
}
