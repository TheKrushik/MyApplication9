package info.krushik.android.myapplication9;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.TimeUtils;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        try {
//            TimeUnit.SECONDS.sleep(5);//засыпает текущий поток на 5сек.
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.EXTRA_PENDING_INTENT);//получаем обратно pendingIntent, вынимаем его из intent
//
//        Intent resultIntent = new Intent();//делаем новый intent
//        resultIntent.putExtra(MainActivity.EXTRA_RESULT, "Result data");//в который ложим ответ
//
//        try {//обязательно в try-catch, потому что на мометнт ответа Activity может уже не быть
//            pendingIntent.send(this, Activity.RESULT_OK, resultIntent);//(контекст, резалт код, интент)
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
