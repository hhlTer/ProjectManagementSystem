package view.DialogMakerInterface;

import view.DialogImplementation.CaseDialog;
import view.DialogImplementation.ProjectDialog;

public class DialogMakerProjects implements DialogMaker {
    @Override
    public CaseDialog dialogMake() {
        return new ProjectDialog();
    }
}
