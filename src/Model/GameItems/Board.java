package Model.GameItems;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class Board {
    public ArrayList<Field> fields = new ArrayList<>(9);

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Field newField = new Field(i, j);
                fields.add(newField);
            }
        }
    }

    public void setFieldStatus(int x, int y, FieldStatus status) throws Exception {
        for (Field field : fields) {
            if (field.getX() == x && field.getY() == y) {
                if (field.getFieldStatus() == FieldStatus.NONE) {
                    field.setState(status);
                    return;
                } else {
                    throw new Exception("Field already used");
                }
            }
        }
        throw new Exception("Field does not exist");
    }

    public FieldStatus getFieldStatus(int x, int y) {
        for (Field field : fields) {
            if (field.getX() == x && field.getY() == y) {
                return field.getFieldStatus();
            }
        }
        return null;
    }
}
