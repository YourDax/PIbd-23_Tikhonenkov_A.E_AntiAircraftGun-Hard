module com.example.antiaircraftgun {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.antiaircraftgun to javafx.fxml;
    exports com.example.antiaircraftgun;
}