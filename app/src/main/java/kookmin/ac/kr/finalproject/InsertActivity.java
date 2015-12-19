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

    Button mBtClose; // 닫기 버튼
    Button mBtAdd; // 저장 버튼
    Button mBtAddList; // 조회 버튼
    TextView mTvAddress; // 현재 위치를 띄워줌
    String selItem; // DB에 저장 될 값(카테고리 값)

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_insert);

        final DBManager dbManager = new DBManager(getApplicationContext(), "List.db", null, 1); // DB를 선언해준다.

        // DB에 저장 될 속성을 입력받는다(한 일)
        final EditText etToday= (EditText) findViewById(R.id.et_today);

        // 뷰를 id를 이용해 변수와 연결해준다.
        mBtAdd = (Button)findViewById(R.id.bt_add);
        mBtAddList = (Button)findViewById(R.id.bt_addlist);
        mBtClose = (Button)findViewById(R.id.bt_close);
        mTvAddress=  (TextView)findViewById(R.id.tv_address);


        Intent address_intent = getIntent(); //전달된 인텐트
        mTvAddress.setText(address_intent.getStringExtra("address")); // 텍스트뷰에 현재 위치를 알려준다.



        final Spinner category = (Spinner) findViewById(R.id.sp_category); // 카테고리를 spinner로 보여준다.
        ArrayAdapter caAdapter = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_dropdown_item);
        caAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(caAdapter);

        // Spinner 동작처리
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selItem = (String) category.getSelectedItem(); // 선택된 값이 저장됨
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
                String memo = etToday.getText().toString(); // 한 일을 입력받아서 memo에 저장
                String category = selItem; // 선택된 분류값을 category에 저장
                if ( memo.length() != 0 ) { // 내용이 입력되면 DB에 저장
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                    dbManager.insert("insert into TODAY_LIST values(null, '" + memo + "', '" + category + "');");
                }
                else { // 아무것도 입력받지 못하면 토스트메세지를 띄워준다.
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요~o>0<o", Toast.LENGTH_LONG).show();
                }
            }
        });

        mBtAddList.setOnClickListener(new View.OnClickListener() { // 조회 버튼 클릭
            @Override
            public void onClick(View v) {
                String str = dbManager.PrintData(); // DB의 데이터들을 str에 저장
                Intent list_intent = new Intent(InsertActivity.this, ListActivity.class); // ListActivity로 이동
                list_intent.putExtra("List", str); // 조회리스트로 str전달
                startActivity(list_intent);
            }
        });

        mBtClose.setOnClickListener(new View.OnClickListener() { // 닫기 버튼 클릭
            @Override
            public void onClick(View v) {
                finish(); // 전 화면인 InsertMapActivity로 이동
            }
        });
    }
}
