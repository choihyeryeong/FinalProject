package kookmin.ac.kr.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ListActivity extends Activity {


    TextView mTvList; // 리스트를 띄워줄 텍스트뷰
    Button mBtEdit; // 수정 버튼
    Button mBtDelete; // 삭제 버튼
    Button mBtGoMain; // 메인으로 버튼
    EditText mEtEdit_in; // 수정할 번호를 입력받는 edit텍스트
    EditText mEtEdit_do; // 수정할 내용을 입력받는 edit텍스트
    EditText mEtDelete_in; // 삭제할 번호를 입력받는 edit텍스트
    String selItem=""; // DB에 저장 될 값(카테고리 값)

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_list);

        final DBManager dbManager = new DBManager(getApplicationContext(), "List.db", null, 1); // DB를 선언해준다.

        mTvList =  (TextView)findViewById(R.id.tv_list);
        mBtEdit =  (Button)findViewById(R.id.bt_edit);
        mBtDelete =  (Button)findViewById(R.id.bt_delete);
        mBtGoMain =  (Button)findViewById(R.id.bt_goMain);
        mEtEdit_in = (EditText)findViewById(R.id.et_edit_index);
        mEtEdit_do = (EditText)findViewById(R.id.et_edit_do);
        mEtDelete_in = (EditText)findViewById(R.id.et_delete_index);

        Intent intent = getIntent(); //전달된 인텐트
        mTvList.setText(intent.getStringExtra("List")); //인텐트 내용물 get
        mTvList.setVerticalScrollBarEnabled(true); // 리스트에 스크롤을 만든다.
        mTvList.setMovementMethod(new ScrollingMovementMethod());

        mEtEdit_do.setVerticalScrollBarEnabled(true); // 수정할 내용을 입력받는 edit텍스트뷰에 스크롤을 만든다.
        mEtEdit_do.setMovementMethod(new ScrollingMovementMethod());


        final Spinner category2 = (Spinner) findViewById(R.id.sp_categoty2); // 카테고리를 spinner로 만들어준다.
        ArrayAdapter caAdapter2 =  ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_dropdown_item);
        caAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category2.setAdapter(caAdapter2);

        // Spinner 동작처리
        category2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selItem = (String) category2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });

        mBtEdit.setOnClickListener(new View.OnClickListener() { // 수정 버튼 클릭
            @Override
            public void onClick(View v) {


                int index;
                String memo = mEtEdit_do.getText().toString();
                String category = selItem;


                if (mEtEdit_in.getText().toString().length() > 0) //index를 입력받으면 int형으로 바꿔줌
                    index = Integer.parseInt(mEtEdit_in.getText().toString());
                else index = -1;


                // update 테이블명 where 조건 set 값;
                if ((index > 0) && (memo.length() > 0) && (category.length() > 0)) {
                    dbManager.update("update TODAY_LIST set memo = '" + memo + "'," + "category = '" + category + "' where _id = " + index + ";");
                    finish();
                } else if ((index > 0) && (memo.length() <= 0) && (category.length() > 0)) //내용만 입력 안하면
                    Toast.makeText(getApplicationContext(), "수정할 내용을 입력해주세요~o>0<o", Toast.LENGTH_LONG).show();
                else if ((index > 0) && (memo.length() > 0) && (category.length() <= 0)) //분류만 입력 안하면
                    Toast.makeText(getApplicationContext(), "수정할 분류를 입력해주세요~o>0<o", Toast.LENGTH_LONG).show();
                else if ((index < 0) && (memo.length() > 0) && (category.length() > 0)) //번호만 입력 안하면
                    Toast.makeText(getApplicationContext(), "수정할 번호를 입력해주세요~o>0<o", Toast.LENGTH_LONG).show();
                else  // 그외
                    Toast.makeText(getApplicationContext(), "수정할 번호/내용/분류를 모두 입력 해주세요~o>0<o", Toast.LENGTH_LONG).show();
            }

        });

        mBtDelete.setOnClickListener(new View.OnClickListener() { // 삭제 버튼 클릭
            @Override
            public void onClick(View v) {

                int index;

                if (mEtDelete_in.getText().toString().length() > 0) //index를 입력받으면 int형으로 바꿔줌
                    index = Integer.parseInt(mEtDelete_in.getText().toString());
                else index = -1;

                // delete from 테이블명 where 조건;
                if (index > 0) {
                    dbManager.delete("delete from TODAY_LIST where _id = " + index + ";");
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "삭제할 알맞은 번호를 입력해주세요~o>0<o", Toast.LENGTH_LONG).show();
                }
            }
        });

        mBtGoMain.setOnClickListener(new View.OnClickListener() { // 메인으로 버튼 클릭
            @Override
            public void onClick(View v) {
                Intent list_intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(list_intent);
            }
        });




    }
}
