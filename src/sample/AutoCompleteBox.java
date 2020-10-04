package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;

public class AutoCompleteBox implements EventHandler{
    private ComboBox comboBox;
    final private ObservableList data;
    private Integer sid;

    public AutoCompleteBox(final ComboBox comboBox) {
        this.comboBox = comboBox;
        this.data = comboBox.getItems();

        this.doAutoCompleteBox();
    }

    public AutoCompleteBox(final ComboBox comboBox, Integer sid) {
        this.comboBox = comboBox;
        this.data = comboBox.getItems();
        this.sid = sid;

        this.doAutoCompleteBox();
    }

    private void doAutoCompleteBox() {
        this.comboBox.setEditable(true);
        this.comboBox.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                this.comboBox.show();
            }
        });

        this.comboBox.getEditor().setOnMouseClicked(event ->{
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(event.getClickCount() == 2){
                    return;
                }
            }
            this.comboBox.show();
        });

        this.comboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            moveCaret(this.comboBox.getEditor().getText().length());
        });

        this.comboBox.setOnKeyPressed(t -> comboBox.hide());

        this.comboBox.setOnKeyReleased(AutoCompleteBox.this);

        if(this.sid!=null)
            this.comboBox.getSelectionModel().select(this.sid);
    }

    public void handle(Event event) {
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.DOWN
                    || keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.LEFT
                    || keyEvent.getCode() == KeyCode.HOME
                    || keyEvent.getCode() == KeyCode.END || keyEvent.getCode() == KeyCode.TAB
            ) {
                return;
            }

            if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                String str = this.comboBox.getEditor().getText();
                if (str != null && str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                }
                if (str != null) {
                    this.comboBox.getEditor().setText(str);
                    moveCaret(str.length());
                }
                this.comboBox.getSelectionModel().clearSelection();
            }

            if (keyEvent.getCode() == KeyCode.ENTER && comboBox.getSelectionModel().getSelectedIndex() > -1)
                return;

            setItems();
        }
    }

    private void setItems() {
        ObservableList list = FXCollections.observableArrayList();

        for (Object datum : this.data) {
            String s = this.comboBox.getEditor().getText().toLowerCase();
            if (datum.toString().toLowerCase().contains(s.toLowerCase())) {
                list.add(datum.toString());
            }
        }

        if(list.isEmpty()) this.comboBox.hide();

        this.comboBox.setItems(list);
        this.comboBox.show();
    }

    private void moveCaret(int textLength) {
        this.comboBox.getEditor().positionCaret(textLength);
    }
}