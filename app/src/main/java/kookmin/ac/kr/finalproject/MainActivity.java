package kookmin.ac.kr.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button mBtInsert;
    Button mBtList;
    Button mBtStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager dbManager = new DBManager(getApplicationContext(), "List.db", null, 1);

        mBtInsert = (Button) findViewById(R.id.bt_insert);
        mBtList = (Button) findViewById(R.id.bt_list);
        mBtStat = (Button) findViewById(R.id.bt_stat);

        mBtInsert.setOnClickListener(new View.OnClickListener() { // 입력 버튼 클릭
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,InsertMapActivity.class);
                startActivity(intent);
            }
        });

        mBtStat.setOnClickListener(new View.OnClickListener() { // 통계 버튼 클릭
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,CagoStatActivity.class);
                startActivity(intent);
            }
        });

        mBtList.setOnClickListener(new View.OnClickListener() { // 조회 버튼 클릭
            @Override
            public void onClick(View v) {
                String str = dbManager.PrintData();
                // 조회리스트로 str전달
                Intent list_intent = new Intent(MainActivity.this, ListActivity.class);
                list_intent.putExtra("List", str); //인텐트에 데이터 심어 보내기
                startActivity(list_intent);
            }
        });
    }
}
