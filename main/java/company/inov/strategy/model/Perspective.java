package company.inov.strategy.model;

import java.util.List;



public class Perspective {
   
   private Long id;
   private String name;
   private int progress;
   private List<Goal> goals;
   
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getProgress() {
	return progress;
}
public void setProgress(int progress) {
	this.progress = progress;
}
public List<Goal> getGoals() {
	return goals;
}
public void setGoals(List<Goal> goals) {
	this.goals = goals;
}

   
}
