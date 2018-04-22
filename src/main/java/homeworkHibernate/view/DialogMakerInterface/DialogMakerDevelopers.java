package homeworkHibernate.view.DialogMakerInterface;

import homeworkHibernate.view.DialogImplementation.CaseDialog;
import homeworkHibernate.view.DialogImplementation.DeveloperDialog;

public class DialogMakerDevelopers implements DialogMaker {
    public CaseDialog dialogMake() {
        return new DeveloperDialog();
    }
}
