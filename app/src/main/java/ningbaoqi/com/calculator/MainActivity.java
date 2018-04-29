package ningbaoqi.com.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button number_0;
    private Button number_1;
    private Button number_2;
    private Button number_3;
    private Button number_4;
    private Button number_5;
    private Button number_6;
    private Button number_7;
    private Button number_8;
    private Button number_9;
    private Button point;
    private Button plus;
    private Button sub;
    private Button mul;
    private Button div;
    private Button equal;
    private Button clear;
    private Button delete;
    private TextView tv_result;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView();
        this.setClickListener();
    }

    private void setClickListener() {
        this.number_0.setOnClickListener(this);
        this.number_1.setOnClickListener(this);
        this.number_2.setOnClickListener(this);
        this.number_3.setOnClickListener(this);
        this.number_4.setOnClickListener(this);
        this.number_5.setOnClickListener(this);
        this.number_6.setOnClickListener(this);
        this.number_7.setOnClickListener(this);
        this.number_8.setOnClickListener(this);
        this.number_9.setOnClickListener(this);
        this.point.setOnClickListener(this);
        this.plus.setOnClickListener(this);
        this.sub.setOnClickListener(this);
        this.mul.setOnClickListener(this);
        this.div.setOnClickListener(this);
        this.equal.setOnClickListener(this);
        this.clear.setOnClickListener(this);
        this.delete.setOnClickListener(this);
        this.tv_result.setOnClickListener(this);
    }

    private void initView() {
        this.setContentView(R.layout.activity_main);
        this.number_0 = (Button) findViewById(R.id.id_0);
        this.number_1 = (Button) findViewById(R.id.id_1);
        this.number_2 = (Button) findViewById(R.id.id_2);
        this.number_3 = (Button) findViewById(R.id.id_3);
        this.number_4 = (Button) findViewById(R.id.id_4);
        this.number_5 = (Button) findViewById(R.id.id_5);
        this.number_6 = (Button) findViewById(R.id.id_6);
        this.number_7 = (Button) findViewById(R.id.id_7);
        this.number_8 = (Button) findViewById(R.id.id_8);
        this.number_9 = (Button) findViewById(R.id.id_9);
        this.point = (Button) findViewById(R.id.point);
        this.plus = (Button) findViewById(R.id.plus);
        this.sub = (Button) findViewById(R.id.id_sub);
        this.mul = (Button) findViewById(R.id.multiply);
        this.div = (Button) findViewById(R.id.divide);
        this.equal = (Button) findViewById(R.id.equal);
        this.clear = (Button) findViewById(R.id.clear);
        this.delete = (Button) findViewById(R.id.delete);
        this.tv_result = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        String result = tv_result.getText().toString();
        switch (v.getId()) {
            case R.id.id_0:
            case R.id.id_1:
            case R.id.id_2:
            case R.id.id_3:
            case R.id.id_4:
            case R.id.id_5:
            case R.id.id_6:
            case R.id.id_7:
            case R.id.id_8:
            case R.id.id_9:
                if (flag) {
                    result = ((Button) v).getText().toString();
                    flag = false;
                    break;
                }
                result += ((Button) v).getText().toString();
                break;
            case R.id.delete:
                if (!TextUtils.isEmpty(result) && !result.substring(result.length() - 1, result.length()).equals(" ")) {
                    result = result.substring(0, result.length() - 1);
                } else if (!TextUtils.isEmpty(result) && result.substring(result.length() - 1, result.length()).equals(" ")) {
                    result = result.substring(0, result.length() - 3);
                }
                break;
            case R.id.clear:
                result = "";
                break;
            case R.id.point:
                if (!TextUtils.isEmpty(result) && !result.substring(result.length() - 1, result.length()).equals(" ")) {
                    if ((result.contains(".") && !result.contains(" ")) || (result.lastIndexOf(".") > result.lastIndexOf(" "))) {
                        break;
                    }
                    result += ((Button) v).getText().toString();
                    break;
                }
            case R.id.divide:
            case R.id.multiply:
            case R.id.plus:
            case R.id.id_sub:
                if (!TextUtils.isEmpty(result) && !result.substring(result.length() - 1, result.length()).equals(".") && !result.substring(result.length() - 1, result.length()).equals(" ") && !result.contains(" ")) {
                    result += " " + ((Button) v).getText().toString() + " ";
                    if (flag) {
                        flag = false;
                    }
                }
                break;
            case R.id.equal:
                if (!result.contains(" ") || (result.lastIndexOf(" ") == result.length() - 1)) {
                    return;
                }
                int lastEmptyIndex = result.lastIndexOf(" ");
                String str1 = result.substring(0, lastEmptyIndex - 2);
                String str2 = result.substring(lastEmptyIndex + 1);
                double number1 = Double.parseDouble(str1);
                double number2 = Double.parseDouble(str2);
                if (result.contains("÷") && number2 == 0) {
                    return;
                }
                if (result.contains("+")) {
                    result = String.valueOf(number1 + number2);
                }
                if (result.contains("-")) {
                    result = String.valueOf(number1 - number2);
                }
                if (result.contains("×")) {
                    result = String.valueOf(number1 * number2);
                }
                if (result.contains("÷")) {
                    result = String.valueOf(number1 / number2);
                }
                flag = true;
                break;
        }
        if (TextUtils.isEmpty(result)) {
            result = "";
        }
        tv_result.setText(result);
    }
}
