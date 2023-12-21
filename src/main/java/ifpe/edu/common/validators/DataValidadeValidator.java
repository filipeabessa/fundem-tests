package ifpe.edu.common.validators;

import java.time.LocalDateTime;

public class DataValidadeValidator{

    public boolean validate(LocalDateTime inputDate) {

        LocalDateTime currentDateTime = LocalDateTime.now();

        return inputDate != null && !inputDate.isBefore(currentDateTime);
    }
}
