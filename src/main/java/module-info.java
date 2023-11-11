module ru.dubna.simplejavagui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens ru.dubna.simplejavagui.controllers to javafx.fxml;
    opens ru.dubna.simplejavagui.entities to javafx.base;
    opens ru.dubna.simplejavagui.utils to javafx.base;

    exports ru.dubna.simplejavagui;
}