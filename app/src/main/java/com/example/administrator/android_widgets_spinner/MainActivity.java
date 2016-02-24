package com.example.administrator.android_widgets_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Spinner.OnItemSelectedListener {
    private static final String[] namesStr = {"张三", "李四", "王五", "赵六"};
    private TextView title;
    private EditText mEditText;
    private Button add_btn;
    private Button delete_btn;
    private Spinner mSpinner;
    private ArrayAdapter<String> adapter;
    private List<String> allNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        Init();
        setListener();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             title.setText(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add_btn:
                addDataToSpinner();
                break;
            case R.id.delete_btn:
                deleteDataFromSpinner();
                break;
        }
    }

    private void deleteDataFromSpinner(){
         if(mSpinner.getSelectedItem()!=null){
             adapter.remove(mSpinner.getSelectedItem().toString());
             mEditText.setText("");
             if(adapter.getCount()==0){
                 showToast("没有可删除的数据",Toast.LENGTH_SHORT);
             }
         }
    }
    //添加数据到Spinner
    private void addDataToSpinner(){
        String input = mEditText.getText().toString();
        for(int i=0;i<adapter.getCount();i++){
            if(input.equals(adapter.getItem(i))){
showToast("已存在",Toast.LENGTH_SHORT);
                return;
            }
        }

        if(!input.equals("")){
            adapter.add(input);
            int position = adapter.getPosition(input);
            mSpinner.setSelection(position);
            mEditText.setText("");

        }
    }

    private void showToast(String showText, int duration) {
        Toast.makeText(this, showText, duration).show();
    }
    /*界面的初始化工作*/
    private void Init() {
        setArrayAdapter();
    }

    //为Spinner添加ArrayAdapter适配器
    private void setArrayAdapter() {
        allNames = new ArrayList<>();
        for (int i = 0; i < namesStr.length; i++) {
            allNames.add(namesStr[i]);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    /*为控件设置事件监听*/
    private void setListener() {
        add_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(this);
    }

    /*实例化布局文件的控件*/
    private void findViewById() {
        title = (TextView) findViewById(R.id.title);
        mEditText = (EditText) findViewById(R.id.mEditText);
        add_btn = (Button) findViewById(R.id.add_btn);
        delete_btn = (Button) findViewById(R.id.delete_btn);
        mSpinner = (Spinner) findViewById(R.id.mSpinner);

    }
}
