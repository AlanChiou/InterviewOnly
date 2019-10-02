package com.alanchiou.android.apps.chocointerview.http;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import com.alanchiou.android.apps.chocointerview.data.CreatedAtTypeAdapter;
import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.data.Repository;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadJobService extends JobService {

  private static final int JOB_ID = 1234;
  private static final String CONTENT_URL = "http://www.mocky.io/v2/5a97c59c30000047005c1ed2";
  private static final Object REQUEST_TAG = new Object();
  private final ExecutorService executorService = Executors.newCachedThreadPool();
  private RequestQueue requestQueue;

  public static void enqueueJob(Context context) {
    JobInfo.Builder jobInfoBuilder = new Builder(JOB_ID,
        new ComponentName(context, DownloadJobService.class));
    jobInfoBuilder
        .setMinimumLatency(Duration.ZERO.toMillis())
        .setOverrideDeadline(Duration.ZERO.toMillis())
        .build();
    JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
    jobScheduler.cancel(JOB_ID);
    jobScheduler.schedule(jobInfoBuilder.build());
  }

  @Override
  public boolean onStartJob(JobParameters params) {
    requestQueue = Volley.newRequestQueue(getApplicationContext());
    executorService.submit(() -> {
      // Request a string response from the provided URL.
      UTF8StringRequest request = new UTF8StringRequest(Request.Method.GET, CONTENT_URL,
          response -> {
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new CreatedAtTypeAdapter()).create();
            JsonObject data = new Gson().fromJson(response, JsonObject.class);
            Type typeOfT = new TypeToken<ArrayList<Drama>>() {
            }.getType();
            List<Drama> dramas = gson.fromJson(data.getAsJsonArray("data"), typeOfT);
            Repository.getInstance(getApplicationContext()).insertOrUpdateDramas(dramas);
          }, error -> {
      });
      request.setTag(REQUEST_TAG);

      // Add the request to the RequestQueue.
      requestQueue.add(request);
    });

    return true;
  }

  @Override
  public boolean onStopJob(JobParameters params) {
    requestQueue.cancelAll(REQUEST_TAG);
    executorService.shutdownNow();

    return false;
  }
}
