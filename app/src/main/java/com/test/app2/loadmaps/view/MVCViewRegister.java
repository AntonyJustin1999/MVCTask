package com.test.app2.loadmaps.view;

public interface MVCViewRegister extends MVCView {
    void navigationToLogin();
    void showprogressbar();
    void hideprogressbar();
    void showError(String ErrorMessage);
}

