package com.test.app2.loadmaps.view;

public interface MVCViewLogin extends MVCView {
    void navigationToRegister();
    void navigationToHome();
    void showprogressbar();
    void hideprogressbar();
    void showError(String ErrorMessage);
}

