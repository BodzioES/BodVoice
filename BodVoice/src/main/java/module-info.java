module com.bodex.bodvoice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jjwt.api;


    opens com.bodex.bodvoice to javafx.fxml;
    exports com.bodex.bodvoice;
}