package kookmin.ac.kr.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertActivity extends Activity {

    Button mBtClose;
    Button mBtAdd;
    Button mBtAddList;
    TextView mTvAddress; // 현재 위치를 띄워줌
    String selItem; // DB에 저장 될 값(카테고리 값)

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_insert);

        final DBManager dbManager = new DBManager(getApplicationContext(), "List.db", null, 1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etToday= (EditText) findViewById(R.id.et_today);

        mBtAdd = (Button)findViewById(R.id.bt_add);
        mBtAddList = (Button)findViewById(R.id.bt_addlist);
        mBtClose = (Button)findViewById(R.id.bt_close);
        mTvAddress=  (TextView)findViewById(R.id.tv_address);


        //SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일  a hh:mm:ss");
        //mTvAddress.setText(sf.format(new Date()));

        Intent address_intent = getIntent(); //전달된 인텐트
        mTvAddress.setText(address_intent.getStringExtra("address")); // 텍스트뷰에 현재 위치를 알려줌



        final Spinner category = (Spinner) findViewById(R.id.sp_category); // 카테고리
        ArrayAdapter caAdapter = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_dropdown_item);
        caAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(caAdapter);

        // Spinner 동작처리
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selItem = (String) category.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });


        mBtAdd.setOnClickListener(new View.OnClickListener() { // 저장 버튼 클릭
            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String memo = etToday.getText().toString();
                String category = selItem;
                if ( memo.length() != 0 ) {
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                    dbManager.insert("insert into TODAY_LIST values(null, '" + memo + "', '" + category + "');");
                }
                else {
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요~o>0<o", Toast.LENGTH_LONG).show();
                }
            }
        });

        mBtAddList.setOnClickListener(new View.OnClickListener() { // 조회 버튼 클릭
            @Override
            public void onClick(View v) {
                String str = dbManager.PrintData();
                // 조회리스트로 str전달
                Intent list_intent = new Intent(InsertActivity.this, ListActivity.class);
                list_intent.putExtra("List", str); //인텐트에 데이터 심어 보내기
                startActivity(list_intent);
            }
        });

        mBtClose.setOnClickListener(new View.OnClickListener() { // 닫기 버튼 클릭
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
