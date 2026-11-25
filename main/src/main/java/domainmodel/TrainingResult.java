package domainmodel;
import domainmodel.Enum.Discipline;
import java.time.LocalDate;

public class TrainingResult {
    private final Discipline discipline;
    private final int time;
    private final LocalDate date;

    public TrainingResult(Discipline discipline, int time, LocalDate date){
        this.discipline = discipline;
        this.time = time;
        this.date = date;
    }

    public Discipline getDiscipline(){
        return discipline;
    }
    public int getTime(){
        return time;
    }
    public LocalDate getDate(){
        return date;
    }
}
