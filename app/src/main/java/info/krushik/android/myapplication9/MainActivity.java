package info.krushik.android.myapplication9;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    public static final String EXTRA_PENDING_INTENT = "info.krushik.android.myapplication9.PENDING_INTENT";
//    public static final String EXTRA_RESULT = "info.krushik.android.myapplication9.RESULT";
//
//    public static final int REQUEST_CODE = 1;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnClick(View v) {
        mDialog = new ProgressDialog(this);//подождите
        mDialog.setMessage("Wait...");//сообщение
        mDialog.setCancelable(false); //чтоб пользователь не мог проигнорировать и закрыть
        mDialog.show(); //показать

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
        if (mDialog != null){
            mDialog.dismiss();//закрывает mDialog
        }
    }
}
