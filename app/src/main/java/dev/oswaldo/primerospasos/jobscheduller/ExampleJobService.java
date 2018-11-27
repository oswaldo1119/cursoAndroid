package dev.oswaldo.primerospasos.jobscheduller;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dev.oswaldo.primerospasos.util.Util;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExampleJobService extends JobService {

    private static String TAG = ExampleJobService.class.getSimpleName();

    private boolean jobHasCancel = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Util.showLog(TAG, "Entra a on start job");
        backGroundWork(jobParameters);
        return true;
    }

    private void backGroundWork(JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<15; i++){
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String date = df.format(Calendar.getInstance().getTime());
                    Date currentTime = Calendar.getInstance().getTime();
                    Util.showLog(TAG, "Valor hell yeah "+i+" Date "+date+" currentTime "+currentTime);
                    //oreoNotification("Hora "+date + "numero "+i);
                    if(jobHasCancel){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Util.showLog(TAG, "Job finished");
                jobFinished(jobParameters, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Util.showLog(TAG, "On job cancelled");
        jobHasCancel = true;
        return true;
    }
}
