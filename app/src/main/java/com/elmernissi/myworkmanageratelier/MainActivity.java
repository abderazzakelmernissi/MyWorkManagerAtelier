package com.elmernissi.myworkmanageratelier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        Constraints constraints = new Constraints
                .Builder()
                .setRequiresCharging(true)
                .build();

        WorkRequest wr = new OneTimeWorkRequest
                .Builder(MyWorker.class)
                .setConstraints(constraints)
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(wr);
            }
        });

        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(wr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo !=null){
                            Toast.makeText(MainActivity.this,
                                            "Status : "+ workInfo.getState().name(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });

    }
}