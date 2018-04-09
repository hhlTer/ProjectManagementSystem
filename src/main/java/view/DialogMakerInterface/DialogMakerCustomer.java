package view.DialogMakerInterface;

import view.DialogImplementation.CaseDialog;
import view.DialogImplementation.CustomerDialog;

public class DialogMakerCustomer implements DialogMaker {
    @Override
    public CaseDialog dialogMake() {
        return new CustomerDialog();
    }
}
