package homeworkHibernate.view.DialogMakerInterface;

import homeworkHibernate.view.DialogImplementation.CaseDialog;
import homeworkHibernate.view.DialogImplementation.CompanyDialog;

public class DialogMakerCompany implements DialogMaker {
    @Override
    public CaseDialog dialogMake() {
        return new CompanyDialog();
    }
}
