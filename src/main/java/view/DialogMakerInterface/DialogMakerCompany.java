package view.DialogMakerInterface;

import view.DialogImplementation.CaseDialog;
import view.DialogImplementation.CompanyDialog;

public class DialogMakerCompany implements DialogMaker {
    @Override
    public CaseDialog dialogMake() {
        return new CompanyDialog();
    }
}
