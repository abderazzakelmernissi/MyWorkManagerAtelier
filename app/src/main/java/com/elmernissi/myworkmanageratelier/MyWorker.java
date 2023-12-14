package com.elmernissi.myworkmanageratelier;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        for(int i = 0;i<10000; i++){
            Log.d("mytag","Count "+ i);
        }

        return Result.success();
    }
}
