package info.krushik.android.myapplication9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {//конструктор создания БД
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ("
                + Student.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Student.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                + Student.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                + Student.COLUMN_AGE + " INTEGER NOT NULL);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveStudent(Student student){//сохранение студента
        SQLiteDatabase db = getWritableDatabase();
        long id=0;

        try {
            ContentValues values = new ContentValues();

            values.put(Student.COLUMN_FIRST_NAME, student.FirstName);
            values.put(Student.COLUMN_LAST_NAME, student.LastName);
            values.put(Student.COLUMN_AGE, student.Age);

            id = db.insert(Student.TABLE_NAME, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public Student getStudent(long id){//чтение студента
        SQLiteDatabase db = getWritableDatabase();
        Student student = null;
        Cursor cursor = null;

        try {
            cursor = db.query(Student.TABLE_NAME, null, Student.COLUMN_ID + "=" + id, null, null, null, null);//ищем указанного студента
            if (cursor.moveToFirst()) {//проверяем что что-то нашло
                student = new Student();// заполняем студента

                student.id = cursor.getLong(cursor.getColumnIndex(Student.COLUMN_ID));
                student.FirstName = cursor.getString(cursor.getColumnIndex(Student.COLUMN_FIRST_NAME));
                student.LastName = cursor.getString(cursor.getColumnIndex(Student.COLUMN_LAST_NAME));
                student.Age = cursor.getLong(cursor.getColumnIndex(Student.COLUMN_AGE));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return  student;
    }
}
