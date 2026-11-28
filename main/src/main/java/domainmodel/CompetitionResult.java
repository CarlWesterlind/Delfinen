package domainmodel;
import domainmodel.Enum.Discipline;
import java.time.LocalDate;

public class CompetitionResult {

    private final String meetName;
    private final LocalDate date;
    private final Discipline discipline;
    private final int placement;
    private final int time;



    public CompetitionResult(String meetName, LocalDate date, Discipline discipline, int placement, int time){
        this.meetName = meetName;
        this.date = date;
        this.discipline = discipline;
        this.placement = placement;
        this.time = time;
    }

    public String getMeetName() {
        return meetName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public int getPlacement() {
        return placement;
    }

    public int getTime() {
        return time;
    }
}
