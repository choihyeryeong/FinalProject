package kookmin.ac.kr.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class CagoStatActivity extends Activity {

    TextView mEtResult;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        final DBManager dbManager = new DBManager(getApplicationContext(), "List.db", null, 1);

        mEtResult = (TextView)findViewById(R.id.tv_result);
        mEtResult.setText(
                "☞ 공부: "+dbManager.result_study()+"%\n\n"+
                        "☞ 독서: "+dbManager.result_read()+"%\n\n"+
                        "☞ 운동: "+dbManager.result_exercise()+"%\n\n"+
                        "☞ 먹기: "+dbManager.result_eat()+"%\n\n"+
                        "☞ 근로: "+dbManager.result_work()+"%\n\n"+
                        "☞ 여행: "+dbManager.result_trip()+"%\n\n"+
                        "☞ 문화: "+dbManager.result_culture()+"%\n\n"+
                        "☞ 취미: "+dbManager.result_hobby()+"%\n\n"+
                        "☞ 쇼핑: "+dbManager.result_shopping()+"%\n\n"+
                        "☞ 기타: "+dbManager.result_etc()+"%\n\n"
        );


    }
}
