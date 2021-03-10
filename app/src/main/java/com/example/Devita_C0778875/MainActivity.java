package com.example.Devita_C0778875;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CandidateClass> candidateArray;
    private TextView candidate1, candidate2, candidate3;
    private Button vote_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidate1 = findViewById(R.id.txtViewCandidate1);
        candidate2 = findViewById(R.id.txtViewCandidate2);
        candidate3 = findViewById(R.id.txtViewCandidate3);

        vote_button = findViewById(R.id.voting_button);

        candidateArray = new ArrayList<CandidateClass>();
        Intent intent = getIntent();

        ArrayList<CandidateClass> candidates = (ArrayList<CandidateClass>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateArray.add(new CandidateClass(1,"TMC",300));
            candidateArray.add(new CandidateClass(2,"BSP",234));
            candidateArray.add(new CandidateClass(3,"RTC",654));
        }
        else{
            candidateArray = candidates;
        }

        candidate1.setText(candidateArray.get(0).getName()+" : " + candidateArray.get(0).getVotes());
        candidate2.setText(candidateArray.get(1).getName()+" : " + candidateArray.get(1).getVotes());
        candidate3.setText(candidateArray.get(2).getName()+" : " + candidateArray.get(2).getVotes());

        vote_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.putExtra("candidates", candidateArray);
                startActivity(intent);
            }
        });


    }
}