package com.test.app2.loadmaps.controller;

import com.test.app2.loadmaps.DataSets.UserDataSet;
import com.test.app2.loadmaps.Databases.UserData;
import com.test.app2.loadmaps.Utils.Utils;
import com.test.app2.loadmaps.model.MVCModelImplementor;
import com.test.app2.loadmaps.view.MVCViewLoginImpl;
import com.test.app2.loadmaps.view.MVCViewRegisterImpl;

public class MVCRegisterController {
    MVCModelImplementor mvcModel;
    MVCViewRegisterImpl mvcView;

    public MVCRegisterController(MVCModelImplementor mvcModel, MVCViewRegisterImpl mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

    public void OnRegister(UserDataSet userDataSet) {

        if(userDataSet.userName.length()>0){
            if(userDataSet.mobileNumber.length()>0){
                if(userDataSet.emailId.length()>0){
                    if(Utils.isValidEmail(userDataSet.emailId)){
                        if(userDataSet.password.length()>0){
                            if(userDataSet.conpassword.length()>0){
                                if(userDataSet.password.equals(userDataSet.conpassword)){
                                    mvcView.showprogressbar();

                                    UserData registerData = new UserData();
                                    registerData.setUserName(userDataSet.getUserName());
                                    registerData.setMobileNumber(userDataSet.getMobileNumber());
                                    registerData.setEmailId(userDataSet.getEmailId());
                                    registerData.setPassword(userDataSet.getPassword());
                                    registerData.setCurrentUser(false);
                                    try {
                                        if(mvcModel.UserRegisteration(registerData)){
                                            mvcView.hideprogressbar();
                                            mvcView.navigationToLogin();
                                        } else {
                                            mvcView.hideprogressbar();
                                            mvcView.showError("UserName Already Exists!!");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        mvcView.showError(e.getMessage());
                                    }

                                } else {
                                    mvcView.showError("Password and Confirm Password Must be Same");
                                }
                            } else {
                                mvcView.showError("Please enter Confirm Password");
                            }
                        } else {
                            mvcView.showError("Please enter Password");
                        }
                    } else {
                        mvcView.showError("Please enter valid eMail Id");
                    }
                } else {
                    mvcView.showError("Please enter eMail Id");
                }
            } else {
                mvcView.showError("Please enter MobileNumber");
            }
        } else {
            mvcView.showError("Please enter user name");
        }
    }

}
