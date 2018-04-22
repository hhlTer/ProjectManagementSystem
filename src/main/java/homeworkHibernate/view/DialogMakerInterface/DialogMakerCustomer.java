package homeworkHibernate.view.DialogMakerInterface;

import homeworkHibernate.view.DialogImplementation.CaseDialog;
import homeworkHibernate.view.DialogImplementation.CustomerDialog;

public class DialogMakerCustomer implements DialogMaker {
    @Override
    public CaseDialog dialogMake() {
        return new CustomerDialog();
    }
}
