package PFE.eSeminaire.mailPattern;

import PFE.eSeminaire.model.Seminar;

import java.io.File;

public class SendNewSeminar {

    private Seminar seminar;
    private File mailList;

    public SendNewSeminar(Seminar seminar, File mailList) {
        this.seminar = seminar;
        this.mailList = mailList;
    }
}
