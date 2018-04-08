package view.DialogMakerInterface;

import view.DialogImplementation.CaseDialog;
import view.DialogImplementation.DeveloperDialog;

public class DialogMakerDevelopers implements DialogMaker {
    public CaseDialog dialogMake() {
        return new DeveloperDialog();
    }
}
