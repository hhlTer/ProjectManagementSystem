package homeworkHibernate.view.DialogMakerInterface;

import homeworkHibernate.view.DialogImplementation.CaseDialog;
import homeworkHibernate.view.DialogImplementation.ProjectDialog;

public class DialogMakerProjects implements DialogMaker {
    @Override
    public CaseDialog dialogMake() {
        return new ProjectDialog();
    }
}
