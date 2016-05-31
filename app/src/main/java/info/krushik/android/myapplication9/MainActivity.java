package info.krushik.android.myapplication9;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

//    public static final String EXTRA_PENDING_INTENT = "info.krushik.android.myapplication9.PENDING_INTENT";
//    public static final String EXTRA_RESULT = "info.krushik.android.myapplication9.RESULT";
//
//    public static final int REQUEST_CODE = 1;

    private ProgressDialog mDialog;

    private SaveTask mSaveTask;
    private GetTask mGetTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnClick(View v) {
//        mDialog = new ProgressDialog(this);//подождите
//        mDialog.setMessage("Wait...");//сообщение
//        mDialog.setCancelable(false); //чтоб пользователь не мог проигнорировать и закрыть
//        mDialog.show(); //показать

        switch (v.getId()) {
            case R.id.button:
//                Intent intent = new Intent(this, MyService.class);
//
//                PendingIntent pendingIntent = createPendingResult(REQUEST_CODE, intent, 0);//создание PendingIntent
//                intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);//pendingIntent ложим в intent
//
//                startService(intent);
                MyIntentService.saveStudent(this, new Student("Ivan", "Ivanov", 22));
                break;
            case R.id.button2:
                MyIntentService.getStudent(this, 1);
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                mSaveTask = new SaveTask();
                mSaveTask.execute(
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22));
                break;
            case R.id.button8:
                mGetTask = new GetTask();
                mGetTask.execute(1l);
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
            case R.id.button11:
                break;
            case R.id.button12:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }

        if (mGetTask != null) {
            mGetTask.cancel(true);
        }
    }

    class SaveTask extends AsyncTask<Student, Integer, Integer>{
        private ProgressDialog mDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Saving...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Integer doInBackground(Student... params) {
            Student student = params[0];
//            long id = 0;

            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);
//            id = helper.saveStudent(student);

            for (int i = 0; i< params.length; i++) {
                if (isCancelled()){
                    helper.saveStudent(params[i]);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i + 1, params.length);
                }

            }

//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return params.length;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mDialog.setMessage(String.format("Saved %s students from %s", values[0], values[1]));
        }

        @Override
        protected void onPostExecute(Integer aLong) {
            if (mDialog != null) {
                mDialog.dismiss();
            }
            Toast.makeText(MainActivity.this, String.valueOf(aLong), Toast.LENGTH_SHORT).show();
//            ((Button)findViewById(R.id.button7)).setText(String.valueOf(aLong));
        }



    }

    class GetTask extends AsyncTask<Long, Void, Student>{
        private ProgressDialog mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Saving...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Student doInBackground(Long... params) {
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);

            return helper.getStudent(params[0]);
        }

        @Override
        protected void onPostExecute(Student student) {
            if (mDialog != null) {
                mDialog.dismiss();
            }
            Toast.makeText(MainActivity.this, student.FirstName, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//обработка результата
        if (resultCode == RESULT_OK){
//            if(requestCode == REQUEST_CODE){
//                String result = data.getStringExtra(EXTRA_RESULT);
//                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//            }
            switch (requestCode){
                case MyIntentService.REQUEST_CODE_SAVE_STUDENT:
                    long id = data.getLongExtra(MyIntentService.EXTRA_ID, 0);// возвращается id
                    Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                    break;
                case MyIntentService.REQUEST_CODE_GET_STUDENT:
                    Student student = data.getParcelableExtra(MyIntentService.EXTRA_STUDENT); //возвращается студент
                    Toast.makeText(this, student.FirstName, Toast.LENGTH_SHORT).show();
                    break;
            }

        }
//        if (mDialog != null){
//            mDialog.dismiss();//закрывает mDialog
//        }
    }
}
