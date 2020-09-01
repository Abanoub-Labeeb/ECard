package org.ecards.entities.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardExpirationValidator implements ConstraintValidator<CardExpiration, String> {

	@Override
    public void initialize(CardExpiration annotation) {
        //Not much to do - this is invoked before "isValid", and can be used to pass annotation parameters
    }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		String validMonths[] = {"01", "02", "03", "04" , "05", "06", "07", "08", "09", "10", "11", "12"};
        
		String expiryDate = value;
		String Month = expiryDate.substring(0, 2);
	    String Year  = expiryDate.substring(3, 5);
	    
	    if(!Arrays.asList(validMonths).contains(Month) || !(Integer.parseInt(Year) > 19) || !(Year.length() == 2))
        {
          isValid = false;
        }
	    
		return isValid;
	}
}
