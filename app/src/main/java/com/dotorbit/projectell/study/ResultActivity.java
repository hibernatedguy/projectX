package com.dotorbit.projectell.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.models.Question;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    public final static String INTENT_QUESTION_LIST = "QUESTION_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get Questions And Question stats
        ArrayList<Question> questionList = (ArrayList<Question>) getIntent().getExtras().get(ResultActivity.INTENT_QUESTION_LIST);
        int [] questionStat = this.getQuestionStats(questionList);

        //Setup view
        ((TextView) findViewById(R.id.txtSkippedCount)).setText(""+questionStat[0]);
        ((TextView) findViewById(R.id.txtCorrectCount)).setText(""+questionStat[1]);
        ((TextView) findViewById(R.id.txtWrongCount)).setText(""+questionStat[2]);

    }

    /**
     * Get Question stats
     * @param questionList Question List
     * @return int array = [missedQuestionCount, CorrectQuestionCount, InCorrectQuestionCount]
     */
    private int[] getQuestionStats(ArrayList<Question> questionList){
        int missCount, correctCount, incorrectCount;
        missCount = incorrectCount = correctCount=0;
        for(Question qus: questionList){
            //Skipped
            if(qus.getSelected_option() == -1){
                missCount+=1;
                continue;
            }
            //Correct
            int i=0;
            for(; i<qus.getAnswer().size();i++){
                if(qus.getAnswer().get(i) == qus.getSelected_option()){
                    correctCount +=1;
                    break;
                }
            }
            //Else
            if(i >=qus.getAnswer().size()){
                incorrectCount +=1;
            }

        }
        int[] stats = {missCount,correctCount,incorrectCount};
        return stats ;
    }

}



