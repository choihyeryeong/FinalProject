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

        final DBManager dbManager = new DBManager(getApplicationContext(), "List.db", null, 1); // DB를 사용하기위해 선언해준다.

        mBtInsert = (Button) findViewById(R.id.bt_insert); // 버튼뷰를 id를 이용해서 변수와 연결한다.
        mBtList = (Button) findViewById(R.id.bt_list);
        mBtStat = (Button) findViewById(R.id.bt_stat);

        mBtInsert.setOnClickListener(new View.OnClickListener() { // 입력 버튼 클릭
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,InsertMapActivity.class); // intent를 이용해 InsertMapActivity로 이동한다.
                startActivity(intent);
            }
        });

        mBtStat.setOnClickListener(new View.OnClickListener() { // 통계 버튼 클릭
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,CagoStatActivity.class); // intent를 이용해 CagoStatActivity로 이동한다.
                startActivity(intent);
            }
        });

        mBtList.setOnClickListener(new View.OnClickListener() { // 조회 버튼 클릭
            @Override
            public void onClick(View v) {
                String str = dbManager.PrintData(); // 조회리스트로 전달할 str
                Intent list_intent = new Intent(MainActivity.this, ListActivity.class); // intent를 이용해 ListActivity로 이동한다.
                list_intent.putExtra("List", str); //인텐트에 데이터 심어 보내기
                startActivity(list_intent);
            }
        });
    }
}
