package com.example.photovideohidelock.CV_interfaces;

public interface CV_CalculatorContract {

    public interface Presenter {
        void onCalculateResult();

        void onClearExpression();

        void onExpressionSignChange();

        void onOperatorAdd(String str);
    }

    public interface View {
        void showInvalidExpressionMessage();

        void showResult(String str);

        void updateCurrentExpression(String str);
    }
}
