package com.caren.unobliviate;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CarenService extends IntentService {

    /**
     * A constructor is required, and must call the super <code><a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a></code>
     * constructor with a name for the worker thread.
     */
    public CarenService() {
        super("CarenService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Caren", "service");
    }
}
