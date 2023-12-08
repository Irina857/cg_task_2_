module com.example.cg_task_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.cg_task_2 to javafx.fxml;
    exports com.example.cg_task_2;
}