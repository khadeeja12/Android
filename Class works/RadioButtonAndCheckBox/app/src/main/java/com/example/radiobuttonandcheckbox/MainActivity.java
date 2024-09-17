package com.example.radiobuttonandcheckbox;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxReading, checkBoxSinging, checkBoxPlaying;
    private TextView textViewResult;
    private Button buttonShowSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        radioGroupGender = findViewById(R.id.gender);
        checkBoxSinging = findViewById(R.id.checkBox);
        checkBoxReading = findViewById(R.id.checkBox2);
        checkBoxPlaying = findViewById(R.id.checkBox3);
        buttonShowSelection = findViewById(R.id.button);
        textViewResult = findViewById(R.id.textView4);

        // Set OnClickListener for the button
        buttonShowSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelection();
            }
        });
    }
    private void showSelection() {
        // Get selected gender from RadioGroup
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton == null ? "Not selected" : selectedGenderButton.getText().toString();

        // Get selected hobbies from CheckBoxes
        StringBuilder hobbies = new StringBuilder();
        if (checkBoxSinging.isChecked()) {
            hobbies.append("Singing ");
        }
        if (checkBoxReading.isChecked()) {
            hobbies.append("Reading ");
        }
        if (checkBoxPlaying.isChecked()) {
            hobbies.append("Playing ");
        }
        if (hobbies.length() == 0) {
            hobbies.append("No hobbies selected");
        }

        // Display the result
        String result = "Gender: " + gender + "\nHobbies: " + hobbies.toString();
        textViewResult.setText(result);
    }
}