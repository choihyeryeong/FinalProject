package kookmin.ac.kr.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
        db.execSQL("CREATE TABLE TODAY_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, memo TEXT, category TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + ".  한 일 : "
                    + cursor.getString(1)
                    + ", 분 류 : "
                    + cursor.getString(2)
                    + "\n";
        }

        return str;
    }

    public double result_eat(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("먹기")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_study(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("공부")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_read(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("독서")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_exercise(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("운동")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_work(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("근로")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_trip(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("여행")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_culture(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("문화")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_hobby(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("취미")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_shopping(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("쇼핑")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

    public double result_etc(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        double all_num = 0;
        double cago_num = 0;
        double result = 0;

        Cursor cursor = db.rawQuery("select * from TODAY_LIST", null);

        while(cursor.moveToNext()) {
            all_num += 1;
            str = cursor.getString(2);
            if(str.equals("기타")){cago_num+=1;}
        }

        result = (cago_num/all_num)*100;
        result = Double.parseDouble(String.format("%.3f", result));

        return result;
    }

}