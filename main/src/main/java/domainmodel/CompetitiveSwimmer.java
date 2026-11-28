package domainmodel;

import domainmodel.Enum.Discipline;
import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitiveSwimmer extends Member {
    private ArrayList<Discipline> disciplines;
    private ArrayList<TrainingResult> bestTrainingResults = new ArrayList<>();
    private ArrayList<CompetitionResult> competitionResults = new ArrayList<>();

    public CompetitiveSwimmer(int id, String name, LocalDate birthdate, MemberStatus memberstatus, ArrayList<Discipline> disciplines) {
        super(id, name, birthdate, memberstatus);
        if (disciplines.isEmpty()) {
            throw new IllegalArgumentException("All competitive swimmers must have a discipline");
        }
        this.disciplines = new ArrayList<>(disciplines);

    }

    public TrainingResult getBestTrainingResults(Discipline discipline){
        for(TrainingResult result : bestTrainingResults){
            if(result.getDiscipline() == discipline){
                return result;
            }
        }
        return null;
    }

    public ArrayList<Discipline> getDisciplines() {
        return new ArrayList<>(disciplines);
    }

    public boolean registerTrainingTime(Discipline discipline, int time, LocalDate date) {
        if (!disciplines.contains(discipline)) {
            throw new IllegalArgumentException(getName() + " is not active in " + discipline);

        }

        TrainingResult currentBest = getBestTrainingResults(discipline);
        if(currentBest != null && currentBest.getTime()<= time){
            return false;
        }

        if(currentBest != null) {
            bestTrainingResults.remove(currentBest);
        }

        bestTrainingResults.add(new TrainingResult(discipline,time,date));
        return true;

    }

    public boolean isActiveIn(Discipline discipline){
        return disciplines.contains(discipline);
    }

    public void addCompetitionResult(String meetName, LocalDate date, Discipline discipline, int placement, int time){
        if(!isActiveIn(discipline)){
            throw new IllegalArgumentException(getName() + " is not active in " + discipline);
        }
        if(meetName == null || meetName.isBlank()){
            throw new IllegalArgumentException("The meet must have a name");
        }
        if(placement < 1){
            throw new IllegalArgumentException("Place must be at least 1 or higher");
        }
        if(time<=0){
            throw new IllegalArgumentException("Time must be greater than 0");
        }
        competitionResults.add(new CompetitionResult(meetName, date, discipline, placement, time));
    }

    public ArrayList<CompetitionResult> getCompetitionResults(){
        return new ArrayList<>(competitionResults);
    }

}