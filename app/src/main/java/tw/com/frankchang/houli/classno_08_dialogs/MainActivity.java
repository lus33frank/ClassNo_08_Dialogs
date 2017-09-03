package tw.com.frankchang.houli.classno_08_dialogs;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvShow;
    EditText etAcc, etPW;

    int single_Checked, single_Checked_temp;
    boolean[] multiple_Checked;
    ArrayList<Integer> multiple_ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewer();
        multiple_ArrayList = new ArrayList<>();
    }

    private void findviewer() {
        tvShow = (TextView) findViewById(R.id.textView);
    }

    public void onAlertDialog(View v){
        //一般常用警示訊息（MessageBox）
        AlertDialog alert_dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)      //Icon圖示
                .setTitle(R.string.Alert_Title)     //標題
                .setMessage(R.string.Alert_Message) //訊息內容
                .setPositiveButton(R.string.Alert_Positive, Alert_OnClick) //正向位置按鈕
                .setNeutralButton(R.string.Alert_Neutral, Alert_OnClick)   //中性位置按鈕
                .setNegativeButton(R.string.Alert_Negative, Alert_OnClick) //負向位置按鈕
                .create();
        alert_dialog.show();
    }

    DialogInterface.OnClickListener Alert_OnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String str_Message = getResources().getString(R.string.Alert_OnClick_Message);

            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //正向位置按鈕
                    str_Message += getResources().getString(R.string.Alert_Positive);
                    break;

                case DialogInterface.BUTTON_NEUTRAL:
                    //中性位置按鈕
                    str_Message += getResources().getString(R.string.Alert_Neutral);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //負向位置按鈕
                    str_Message += getResources().getString(R.string.Alert_Negative);
                    break;
            }
            Toast.makeText(MainActivity.this, str_Message, Toast.LENGTH_SHORT).show();
        }
    };


    public void onDialogWithList(View v){
        //列表型（List）
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)      //Icon圖示
                .setTitle(R.string.WithList_Title)  //標題
                .setItems(R.array.WithList_Color, WithList_OnClick) //選單列表
                .show();

    }

    DialogInterface.OnClickListener WithList_OnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String str_Message = getResources().getString(R.string.WithList_OnClick_Message);

            switch (which){
                case 0:
                    str_Message += getResources().getString(R.string.WithList_Red);
                    break;

                case 1:
                    str_Message += getResources().getString(R.string.WithList_Green);
                    break;

                case 2:
                    str_Message += getResources().getString(R.string.WithList_Blue);
                    break;
            }
            Toast.makeText(MainActivity.this, str_Message, Toast.LENGTH_SHORT).show();
        }
    };


    public void onPersistent_Single(View v){
        //列表單選型（radioButton）
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)      //Icon圖示
                .setTitle(R.string.Single_Title)    //標題
                //選單選項（選單列表陣列, 預設值, 監聽器）
                .setSingleChoiceItems(R.array.Weeks, single_Checked, Single_OnClick)
                .setPositiveButton(R.string.btn_OK, Single_OnClick)      //正向位置按鈕
                .setNegativeButton(R.string.btn_CANCEL, Single_OnClick)  //負向位置按鈕
                .show();
    }

    DialogInterface.OnClickListener Single_OnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String str_Message;

            //按下選單項目
            if (which != DialogInterface.BUTTON_POSITIVE && which != DialogInterface.BUTTON_NEGATIVE){
                single_Checked_temp = which;
            }

            //按下OK
            if (which == DialogInterface.BUTTON_POSITIVE){
                str_Message = getResources().getString(R.string.Select_OnClick_Message);
                single_Checked = single_Checked_temp;

                switch (single_Checked){
                    case 0:
                        str_Message += getResources().getString(R.string.Sunday);
                        break;

                    case 1:
                        str_Message += getResources().getString(R.string.Monday);
                        break;

                    case 2:
                        str_Message += getResources().getString(R.string.Tuesday);
                        break;

                    case 3:
                        str_Message += getResources().getString(R.string.Wednesday);
                        break;

                    case 4:
                        str_Message += getResources().getString(R.string.Thursday);
                        break;

                    case 5:
                        str_Message += getResources().getString(R.string.Friday);
                        break;

                    case 6:
                        str_Message += getResources().getString(R.string.Saturday);
                        break;
                }
                Toast.makeText(MainActivity.this, str_Message, Toast.LENGTH_SHORT).show();
            }
        }
    };


    public void onPersistent_Multiple(View v){
        //列表單選型（checkbox）
        String[] weeks = getResources().getStringArray(R.array.Weeks);
        multiple_Checked = new boolean[weeks.length];
        for (int row = 0; row < multiple_ArrayList.size(); row++) {
            multiple_Checked[multiple_ArrayList.get(row)] = true;
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.Multiple_Title)    //標題
                //選單選項（選單列表陣列,預設值（陣列）,監聽器）
                .setMultiChoiceItems(R.array.Weeks, multiple_Checked, Multiple_ChoiceClick)
                .setPositiveButton(R.string.btn_OK, Multiple_OnClick)      //正向位置按鈕
                .setNegativeButton(R.string.btn_CANCEL, Multiple_OnClick)  //負向位置按鈕
                .show();
    }

    DialogInterface.OnMultiChoiceClickListener Multiple_ChoiceClick = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            //勾選項目時的監聽器

            //利用 ArrayList 記錄勾選項目
            if (isChecked){
                //將勾選的項目的 index 加到 ArrayList 中儲存為"值"
                multiple_ArrayList.add(which);
            }
            else{
                //將取消勾選的項目的 index 到 ArrayList 中比對「所儲存的"值"」後移除
                multiple_ArrayList.remove(Integer.valueOf(which));
            }
        }
    };

    DialogInterface.OnClickListener Multiple_OnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //按下正向、負向、中性鍵時的監聽器

        }
    };


    public void onCustomDialog(View v){
        //自訂畫面
        LayoutInflater LInflater = getLayoutInflater();
        View CustomDialog = LInflater.inflate(R.layout.activity_customdialog, null);

        etAcc = (EditText) CustomDialog.findViewById(R.id.editText);
        etPW = (EditText) CustomDialog.findViewById(R.id.editText2);

        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)      //Icon圖示
                .setTitle(R.string.Custom_Title)     //標題
                .setView(CustomDialog)
                .setPositiveButton(R.string.btn_OK, Custom_OnClick) //正向位置按鈕
                .setNegativeButton(R.string.btn_CANCEL, Custom_OnClick) //負向位置按鈕
                .show();
    }

    DialogInterface.OnClickListener Custom_OnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE){
                tvShow.setText(etAcc.getText().toString() + " - " + etPW.getText().toString());
                Toast.makeText(MainActivity.this, getResources().getString(R.string.btn_OK), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, getResources().getString(R.string.btn_CANCEL), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
