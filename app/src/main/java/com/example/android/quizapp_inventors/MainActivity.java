package com.example.android.quizapp_inventors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

// This App is a quiz app based on famous inventors theme.
//Once the user has pressed the score button, a toast message appears with
//a final score out of 14. The solutions will also be displayed next to each question.

public class MainActivity extends AppCompatActivity {
    int checkCount = 0;// initialised variables for each question to keep score.
    int editCount = 0;
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int score4 = 0;
    int score5 = 0;
    boolean checkAns = false;// initialised booleans to check whether the question is answered or not.
    boolean editTextOne = false;
    boolean answerOne = false;
    boolean answerTwo = false;
    boolean answerThree = false;
    boolean answerFour = false;
    boolean answerFive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * The 5 methods below is for the 5 radio groups. All groups are initialised and
     * the checked radio button id is stored as a variable idx. A function(testCorrectSolutionButton)
     * is then called to compare the checked radio button to the actual solution.
     * A true comparison adds two to the scorex variable for each radio group.
     * On clicking the radio button a boolean(answerX) is set to confirm the question has been answered
     *
     * @param view radio button view is parsed
     */
    public void radioButtonClick1(View view) {
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
        int id1 = radioGroup1.getCheckedRadioButtonId();
        int ans1 = R.id.radio_one_flemming;
        score1 = testCorrectSolutionButton(id1, ans1);
        radioGroup1.setEnabled(false);
        answerOne = true;

    }

    public void radioButtonClick2(View view) {

        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radio_group2);
        int id2 = radioGroup2.getCheckedRadioButtonId();
        int ans2 = R.id.radio_two_glue;
        score2 = testCorrectSolutionButton(id2, ans2);
        answerTwo = true;

    }

    public void radioButtonClick3(View view) {

        RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.radio_group3);
        int id3 = radioGroup3.getCheckedRadioButtonId();
        int ans3 = R.id.radio_three_1;
        score3 = testCorrectSolutionButton(id3, ans3);
        answerThree = true;

    }

    public void radioButtonClick4(View view) {

        RadioGroup radioGroup4 = (RadioGroup) findViewById(R.id.radio_group4);
        int id4 = radioGroup4.getCheckedRadioButtonId();
        int ans4 = R.id.radio_four_lincoln;
        score4 = testCorrectSolutionButton(id4, ans4);
        answerFour = true;
    }

    public void radioButtonClick5(View view) {

        RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.radio_group5);
        int id5 = radioGroup5.getCheckedRadioButtonId();
        int ans5 = R.id.radio_five_mundi;
        score5 = testCorrectSolutionButton(id5, ans5);
        answerFive = true;
    }

    /**
     * This method initialises solutions to Solution text boxes,
     * checks the checkbox status for the first question, edit text for the last question
     * and displays the final score with solutions
     *
     * @param view a radiobutton view is parsed
     */
    public void summary(View view) {
        //Solution strings initialised
        String answerNo1 = "Alternating and Direct Current";
        String answerNo2 = "Alexander Flemming";
        String answerNo3 = "Super Glue";
        String answerNo4 = "1 year";
        String answerNo5 = "Abraham Lincoln";
        String answerNo6 = "Salvator Mundi";
        String answerNo7 = "photophone";

        //initialise textviews for solutions
        TextView answerView1 = findViewById(R.id.correct_answer_one);
        TextView answerView2 = findViewById(R.id.correct_answer_two);
        TextView answerView3 = findViewById(R.id.correct_answer_three);
        TextView answerView4 = findViewById(R.id.correct_answer_four);
        TextView answerView5 = findViewById(R.id.correct_answer_five);
        TextView answerView6 = findViewById(R.id.correct_answer_six);
        TextView answerView7 = findViewById(R.id.correct_answer_seven);
        //initialise checkboxes for question 1
        CheckBox checkBoxAc = (CheckBox) findViewById(R.id.check_box_ac);
        CheckBox checkBoxIc = (CheckBox) findViewById(R.id.check_box_ic);
        CheckBox checkBoxDc = (CheckBox) findViewById(R.id.check_box_dc);
        // initilaise states for checkboxes in question one
        boolean state1 = checkBoxAc.isChecked();
        boolean state2 = checkBoxIc.isChecked();
        boolean state3 = checkBoxDc.isChecked();
        //initialises edittext view to obtain a string value for comparison
        //function is called to make a comparison for empty,wrong string and right string
        EditText editText1 = (EditText) findViewById(R.id.edit_text_solution);
        String textAnswer = editText1.getText().toString();
        editTextTest(textAnswer, answerNo7);


        //call the function verify if checkbox answer is correct and increase score
        boolean check = testCorrectSolutionCheckBox(checkAns, state1, state2, state3);

        //Determine if questions have been answered, if not post message
        //to answer all questions or reveal toast message of quiz score and display solutions
        if (answerOne && answerTwo && answerThree && answerFour && check && answerFive && editTextOne) {
            int totalScore = score1 + score2 + score3 + score4 + score5 + checkCount + editCount;

            Toast.makeText(this, "Your score is " + totalScore + "/14, please review answers above", Toast.LENGTH_LONG).show();
            displayAnswer(answerView1, answerNo1);
            displayAnswer(answerView2, answerNo2);
            displayAnswer(answerView3, answerNo3);
            displayAnswer(answerView4, answerNo4);
            displayAnswer(answerView5, answerNo5);
            displayAnswer(answerView6, answerNo6);
            displayAnswer(answerView7, answerNo7);

        } else {
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Verify if question has been answered by passing these variables
     * The checkcount is initialised to zero to prevent user clicking the same
     * checkbox several times and increasing the score.
     *
     * @param checkAns boolean to see if question has been answered
     * @param state1   first checkbox
     * @param state2   second checkbox
     * @param state3   third checkbox
     * @return return a score count for answer number o
     */
    private boolean testCorrectSolutionCheckBox(boolean checkAns, boolean state1, boolean state2, boolean state3) {
        checkCount = 0;
        if (state1 || state2 || state3) {
            checkAns = true;
        }
        if (state1 && state3 && !state2) {
            checkCount = checkCount + 2;
        }
        return checkAns;
    }

    /**
     * Function to test the selected radio button id with the solution
     * increment score value by 2.Again the count is initiliased to zero
     * in case the radio button is clicked several times.
     *
     * @param checkId    ID of the selected radio button
     * @param correctAns the correct solution radio button
     * @return a score value is returned
     */
    private int testCorrectSolutionButton(int checkId, int correctAns) {
        int count = 0;
        if (checkId == correctAns) {
            count = count + 2;
        }
        return count;
    }

    /**
     * Displays the solution by parsing a text view and message
     * Also changes color of text boxes
     * @param view    textview is parsed
     * @param message solution message is parsed
     */
    private void displayAnswer(TextView view, String message) {

        view.setBackgroundColor(Color.rgb(154, 240, 182));
        view.setTextSize(15);
        view.setText(message);
    }

    /**
     * A function to compare the edittext string. Note if more edittexts are used
     * an integer can be parsed to track the score of individual edittexts
     * @param textAnswer given answer from user
     * @param answerNo7 solution answer
     */

    private void editTextTest(String textAnswer, String answerNo7) {
            editCount=0;
        if (textAnswer.equals("")) {
            editTextOne = false;
        } else if (textAnswer.equals(answerNo7)) {
            editCount = 2;
            editTextOne = true;
        } else {
            editTextOne = true;
        }
    }
}