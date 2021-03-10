package com.example.Devita_C0778875;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Second_Activity extends AppCompatActivity {
    private Spinner VoterSpinner;
    ToggleButton ButtonOne;
    Button Vote_Submission;
    EditText Voter_name, Voter_id;
    private ArrayList<CandidateClass> ArrayOf_candidate;
    ArrayList<VoterInformation> ArrayOf_voters;
    private boolean accepted;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        ArrayOf_voters = new ArrayList<VoterInformation>();
        VoterSpinner = findViewById(R.id.spinner);
        ButtonOne = findViewById(R.id.toggleButton);
        Vote_Submission = findViewById(R.id.button);
        Voter_name = findViewById(R.id.editTextTextPersonName);
        Voter_id = findViewById(R.id.editTextTextPersonID);


        Intent intent = getIntent();
        ArrayList<CandidateClass> candidates = (ArrayList<CandidateClass>) intent.getSerializableExtra("candidates");
        ArrayOf_candidate = candidates;
        ArrayAdapter<CandidateClass> adapter = new ArrayAdapter<CandidateClass>(this,
                android.R.layout.simple_spinner_item, ArrayOf_candidate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VoterSpinner.setAdapter(adapter);


        Vote_Submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Voter_name.getText().toString().isEmpty()){
                    Toast.makeText(Second_Activity.this, "Fillup name field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Voter_id.getText().toString().isEmpty()){
                    Toast.makeText(Second_Activity.this, "Fillup id field", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (VoterInformation V : ArrayOf_voters) {
                    if(V.getId() == Integer.parseInt(Voter_id.getText().toString())){
                        Toast.makeText(Second_Activity.this, "Sorry ! Id already present", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!ButtonOne.isChecked()){
                    Toast.makeText(Second_Activity.this, "Accept the terms and condition", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayOf_voters.add(new VoterInformation(Integer.parseInt(Voter_id.getText().toString()), Voter_name.getText().toString()));
                int selectedCandidateIndex = VoterSpinner.getSelectedItemPosition();
                CandidateClass selectedCandidate = ArrayOf_candidate.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(Second_Activity.this, "Thank you for your vote", Toast.LENGTH_SHORT).show();


            }
        });

        ButtonOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    ButtonOne.setTextOn("Reject");

                } else {

                    ButtonOne.setTextOff("Accepted");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Second_Activity.this, MainActivity.class);
        intent.putExtra("candidates", ArrayOf_candidate);
        startActivity(intent);
    }
}
