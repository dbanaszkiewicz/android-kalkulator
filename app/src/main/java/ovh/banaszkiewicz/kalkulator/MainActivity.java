package ovh.banaszkiewicz.kalkulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String HISTORY = "ovh.banaszkiewicz.kalkulator.history";
    private static StringBuffer resultString;
    private static boolean lastPressedButtonIsArithmeticSymbol = false;
    private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();
    private static ArrayList<String> history;
    private static boolean isResultInResultTextView = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (history == null) {
            history = new ArrayList<>();
        }

        if (resultString == null) {
            resultString = new StringBuffer(((TextView) findViewById(R.id.resultTextView)).getText());
        } else {
            this.updateResultString();
        }
    }

    public void onClickAddTextToResultTextView(View v) {

        if (v instanceof Button) {
            Button senderButton = (Button) v;

            boolean isSenderArithmeticButton = senderButton.getText().toString().equals("/")
                    || senderButton.getText().toString().equals("*")
                    ||senderButton.getText().toString().equals("-")
                    ||senderButton.getText().toString().equals("+");

            if (isResultInResultTextView && !isSenderArithmeticButton) {
                this.onClickClearResultTextViewButton(v);
            }
            isResultInResultTextView = false;

            if (!(resultString.length() == 0 && isSenderArithmeticButton)) {

                if (isSenderArithmeticButton && lastPressedButtonIsArithmeticSymbol) {
                    resultString.replace(resultString.length() - 1, resultString.length(), senderButton.getText().toString());
                } else {
                    resultString.append(senderButton.getText());
                }

                lastPressedButtonIsArithmeticSymbol = isSenderArithmeticButton;

                this.updateResultString();
            }
        }
    }

    public void onClickClearResultTextViewButton(View v) {
        resultString = new StringBuffer();
        lastPressedButtonIsArithmeticSymbol = false;
        isResultInResultTextView = false;
        this.updateResultString();
    }

    public void onClickApplicationExitButton(View v) {
        this.finishAndRemoveTask();
    }

    public void onClickGetResult(View v) {
        if (!lastPressedButtonIsArithmeticSymbol && resultString.length() > 0) {
            JexlExpression e = jexl.createExpression(resultString.toString());
            if (!resultString.toString().contentEquals(e.evaluate(null).toString())) {
                resultString.append('=');
                resultString.append(e.evaluate(null));

                history.add(0, resultString.toString());

                resultString.replace(0, resultString.length(), e.evaluate(null).toString());
                isResultInResultTextView = true;

                updateResultString();
            }
        }
    }

    public void onClickClearHistory(View v) {
        history.clear();
    }

    public void onClickShowHistory(View v) {
        Intent intent = new Intent(this, HistoryListActivity.class);
        intent.putStringArrayListExtra(HISTORY, history);
        startActivity(intent);
    }

    private void updateResultString() {
        ((TextView)findViewById(R.id.resultTextView)).setText(resultString);
    }
}
