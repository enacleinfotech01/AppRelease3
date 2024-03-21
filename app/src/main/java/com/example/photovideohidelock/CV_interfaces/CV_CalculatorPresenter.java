package com.example.photovideohidelock.CV_interfaces;

import android.util.Log;
import com.example.photovideohidelock.CV_activities.CV_CalculatorActivity;
import com.example.photovideohidelock.CV_interfaces.CV_CalculatorContract;
import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CV_CalculatorPresenter implements CV_CalculatorContract.Presenter {
    public static final String INFINITY = "Infinity";
    public static final String PERCENTAGE = "%";
    public static final String SCIENTIFIC_NOTATION_CHAR = "E";
    private static final String STRING_COMMA = ".";
    private boolean isNumberPositive = true;
    private String mCurrentStringExpression;
    private List<Character> validOperators = Arrays.asList(new Character[]{'+', '-', '/', '*', '^', 8730});
    private final CV_CalculatorContract.View view;

    public CV_CalculatorPresenter(CV_CalculatorContract.View view2) {
        this.view = view2;
        this.mCurrentStringExpression = "";
    }

    public void onExpressionSignChange() {
        String str;
        if (this.isNumberPositive) {
            str = "-" + this.mCurrentStringExpression;
        } else {
            String str2 = this.mCurrentStringExpression;
            str = str2.substring(1, str2.length());
        }
        this.mCurrentStringExpression = str;
        this.isNumberPositive = true ^ this.isNumberPositive;
        this.view.updateCurrentExpression(str);
    }

    public void onOperatorAdd(String str) {
        if (!this.mCurrentStringExpression.isEmpty() || !str.equals(STRING_COMMA)) {
            boolean z = true;
            boolean z2 = false;
            if ((isValueAnOperator(str) || str.equals(PERCENTAGE)) && this.mCurrentStringExpression.length() > 0) {
                String str2 = this.mCurrentStringExpression;
                if (isValueAnOperator(String.valueOf(str2.charAt(str2.length() - 1)))) {
                    clearLastCharOfExpression();
                }
            } else if (str.equals(STRING_COMMA)) {
                boolean z3 = false;
                for (char c : this.mCurrentStringExpression.toCharArray()) {
                    if (c == STRING_COMMA.toCharArray()[0]) {
                        z3 = true;
                    }
                    if (this.validOperators.contains(Character.valueOf(c))) {
                        z3 = false;
                    }
                }
                String str3 = this.mCurrentStringExpression;
                if (!this.validOperators.contains(Character.valueOf(str3.charAt(str3.length() - 1)))) {
                    z = z3;
                }
                z2 = z;
            }
            if (!z2) {
                String str4 = this.mCurrentStringExpression + str;
                this.mCurrentStringExpression = str4;
                this.view.updateCurrentExpression(str4);
                return;
            }
            return;
        }
        this.view.showInvalidExpressionMessage();
    }

    public void onClearExpression() {
        this.mCurrentStringExpression = "";
        this.view.updateCurrentExpression("");
        this.view.showResult("");
    }

    public void onCalculateResult() {
        String str;
        if (this.mCurrentStringExpression.isEmpty() || this.mCurrentStringExpression.contains(INFINITY)) {
            this.view.showInvalidExpressionMessage();
            return;
        }
        clearLastValueIfItIsAnOperator();
        this.mCurrentStringExpression = this.mCurrentStringExpression.replaceAll(PERCENTAGE, "/100");
        Expression expression = new Expression(this.mCurrentStringExpression);
        if (CV_CalculatorActivity.cv_mSqroot) {
            String str2 = this.mCurrentStringExpression;
            String substring = str2.substring(0, str2.indexOf("√"));
            String str3 = this.mCurrentStringExpression;
            str = String.valueOf(Double.parseDouble(substring) * Math.sqrt(Double.parseDouble(str3.substring(str3.indexOf("√") + 1))));
            Log.e("AAA", "onCalculateResult: ");
            CV_CalculatorActivity.cv_mSqroot = false;
        } else {
            double doubleValue = expression.eval().doubleValue();
            if (!isValueInteger(doubleValue) || isScientificNotation(Double.toString(doubleValue))) {
                str = Double.toString(doubleValue);
            } else {
                str = String.valueOf((int) Math.round(doubleValue));
            }
        }
        this.view.showResult(str);
        this.mCurrentStringExpression = str;
    }

    private boolean isValueAnOperator(String str) {
        return this.validOperators.contains(Character.valueOf(str.toCharArray()[0]));
    }

    private void clearLastValueIfItIsAnOperator() {
        if (isValueAnOperator(String.valueOf(getLastCharOfExpression()))) {
            clearLastCharOfExpression();
            this.view.updateCurrentExpression(this.mCurrentStringExpression);
        }
    }

    private void clearLastCharOfExpression() {
        String str = this.mCurrentStringExpression;
        this.mCurrentStringExpression = str.substring(0, str.length() - 1);
    }

    private char getLastCharOfExpression() {
        String str = this.mCurrentStringExpression;
        return str.charAt(str.length() - 1);
    }

    private boolean isValueInteger(double d) {
        double round = (double) ((int) Math.round(d));
        Double.isNaN(round);
        return d % round == 0.0d;
    }

    private boolean isScientificNotation(String str) {
        try {
            new BigDecimal(str);
            return str.toUpperCase().contains("E");
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
