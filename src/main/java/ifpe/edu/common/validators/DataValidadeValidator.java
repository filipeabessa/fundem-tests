package ifpe.edu.common.validators;

import java.time.LocalDateTime;

public class DataValidadeValidator{

    public boolean validate(LocalDateTime inputDate) {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Compare the input date with the current date and time
        return inputDate != null && !inputDate.isBefore(currentDateTime);
    }
}
