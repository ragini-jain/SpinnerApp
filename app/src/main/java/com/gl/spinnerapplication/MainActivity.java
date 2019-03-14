package com.gl.spinnerapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText mNameLabel;
    private EditText mQualificationLabel;
    private Button mSaveButton;
    private Button mClearButton;
    private Spinner mAgeSpinner;
    private TextView mSaveDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemOnAgeSpinner();
        initViews();
        registerListener();
    }

    /**
     * Added age in Spinner
     */
    private void addItemOnAgeSpinner() {
        mAgeSpinner=findViewById(R.id.spinner1);
        List<String> list = Arrays.asList("23","24","25","26","27","28");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAgeSpinner.setAdapter(dataAdapter);
    }

    /**
     * All Event Listener
     */
    private void registerListener() {
        mSaveButton.setOnClickListener(this);
        mClearButton.setOnClickListener(this);
    }

    /**
     * Init View
     */
    private void initViews() {
        mNameLabel =findViewById(R.id.nameEditView);
        mQualificationLabel =findViewById(R.id.qualificationEditText);
        mSaveButton=findViewById(R.id.saveButton);
        mClearButton=findViewById(R.id.clearButton);
        mAgeSpinner=findViewById(R.id.spinner1);
        mSaveDetails  = findViewById(R.id.saveTextView);
    }

    @Override
    public void onClick(View v) {
        if(v == mSaveButton){
            saveUserDetails();
        }else {
         clearUserDetails();
        }
    }

    /**
     *
     * @param name
     * @param qualification
     * @param age
     * @return
     */
    private boolean validateView(String name, String qualification, String age){
        boolean status = true;
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(MainActivity.this,
                    "Please Enter Name", Toast.LENGTH_LONG).show();
            status =  false;
        }
        if(TextUtils.isEmpty(qualification)){
            Toast.makeText(MainActivity.this,
                    "Please Enter Qualification", Toast.LENGTH_LONG).show();
            status =  false;
        }
        if (TextUtils.isEmpty(age)){
            Toast.makeText(MainActivity.this,
                    "Please Select Age", Toast.LENGTH_LONG).show();
            status =  false;
        }
       return status;
    }

    private void saveUserDetails(){
       String nameInput = mNameLabel.getText().toString();
       String qualificationInput = mQualificationLabel.getText().toString();
       String age = mAgeSpinner.getSelectedItem().toString();
       if(validateView(nameInput,qualificationInput,age)){
           mSaveDetails.setText("Name :"+nameInput +" Qualification :"+qualificationInput+" Age :"+age);
       }

    }

    private void clearUserDetails(){
        mNameLabel.getText().clear();
        mQualificationLabel.getText().clear();
        mAgeSpinner.setSelected(false);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
