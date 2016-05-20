/**
 * Created by mkutilek on 5/13/2016.
 */
public class Relationship {
    Member m1 = null;
    Member m2 = null;
    double duration = 0.0; //days
    String durStr = "";
    double intensity = 0.0;
    public Relationship(Member m1, Member m2, double duration, String durStr, double intensity){
        this.m1 = m1;
        this.m2 = m2;
        this.duration = duration;
        this.durStr = durStr;
        this.intensity = intensity;
    }
    public static String formatDuration(double duration){
        String formattedDuration = duration + "";
        double new_duration = 0.0;
        double remainder = 0.0;
        if (duration < 1){//weeks or days
            formattedDuration = (duration * 4) + " weeks";
        }
        else if (duration > 12){ //years
            new_duration = duration / 12;

            if (new_duration % 1 != 0){
                remainder = new_duration % 1;
                //if ((remainder * 12) < 1)
                double months = remainder * 12;
                if (months % 1 != 0){
                    double remainder2 = months % 1;
                    double weeks = remainder2 * 4;
                    if (weeks % 1 != 0){
                        double remainder3 = weeks % 1;
                        double days = remainder3 * 7;
                        formattedDuration = (int) new_duration + " years, " + (int) months + " months, " + (int) weeks + " weeks and " + (int) days + " days";
                    }
                    else {
                        formattedDuration = (int) new_duration + " years, " + (int) months + " months and " + weeks + " weeks";
                    }
                }
                else {
                    formattedDuration = (int) new_duration + " years and " + months + " months";
                }
            }
            else {
                formattedDuration = (duration / 12) + " years";
            }

        }
        else {
            formattedDuration += " months";
        }
        return formattedDuration;
    }
}
