package insulinpump;

public class InsulinPumpMode {
	
	double insulinCarb;
    double insulinSen;
    String returningName;
    
    public void setInsulinCarb (double newInsulinCarb){
    	insulinCarb = newInsulinCarb;
    }
    
    public double getInsulinCarb(){
        return insulinCarb;
    }
    
    public void setInsulinSen (double newInsulinSen){
    	insulinSen = newInsulinSen;
    }
    public double getInsulinSen(){
        return insulinSen;
    }
    
    public void setreturningName (String newreturningName){
    	returningName = newreturningName;
    }
    public String getreturningName(){
        return returningName;
    }
}


