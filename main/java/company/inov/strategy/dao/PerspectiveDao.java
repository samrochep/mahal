package company.inov.strategy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import company.inov.strategy.model.Goal;
import company.inov.strategy.model.Objective;
import company.inov.strategy.model.Perspective;

@Repository
public class PerspectiveDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Perspective fetchPerspectiveData(String perspectiveType) {
		System.out.println("before query");
		String query = "select persp.perspective_id,persp.perspective_name, persp.perspective_progress,"
				+ "goal_inst.goal_id,goal_inst.goal_name, goal_inst.goal_progress,"
				+ "obj.objective_id,obj.objective_name,obj.objective_measure,"
				+ "obj.objective_initiative, obj.objective_progress " + "from perspective persp "
				+ "left join goal goal_inst on persp.perspective_id=goal_inst.perspective_id "
				+ "left join Objective obj on goal_inst.goal_id=obj.goal_id " + "where persp.perspective_name=?";
		System.out.println("after query --> " + query);
		//jdbcTemplate.query(sql, rowMapper)
		return jdbcTemplate.query(query, new Object[] { perspectiveType }, new ResultSetExtractor<Perspective>() {

			@Override
			public Perspective extractData(ResultSet rs) throws SQLException, DataAccessException {
				// Map <Integer,List<Integer>> goalToObjectiveMap=new HashMap();

				Map<Integer, Goal> goalMap = new HashMap();
				Map<Integer, List<Objective>> objectiveMap = new HashMap();

				System.out.println("after query exce");

				Perspective perspective = new Perspective();
				boolean isPerspectiveBasicDataSet = false;
				while (rs.next()) {
                    
					//Set perspective obj
					if (!isPerspectiveBasicDataSet) {
						perspective.setId(Long.parseLong(rs.getString("perspective_id")));
						perspective.setName(rs.getString("perspective_name"));
						perspective.setProgress(Integer.parseInt(rs.getString("perspective_progress")));
						isPerspectiveBasicDataSet = true;
					}
                    
					//set goal and objective obj
					if (rs.getString("goal_id") != null) {

						int key = Integer.parseInt(rs.getString("goal_id"));
						if (objectiveMap.containsKey(key)) {
							System.out.println("contains --> " + key);
							Objective objective = prepareObjectiveObject(rs);
							objectiveMap.get(key).add(objective);

						} else {

							Goal goal = prepareGoalObject(rs);
							goalMap.put(key, goal);
							Objective objective = prepareObjectiveObject(rs);
							List<Objective> objectiveList = new ArrayList();
							objectiveList.add(objective);
							objectiveMap.put(key, objectiveList);

						}

					}

				}
                
				//set objectived to goal and goals to perspective
				List<Goal> goalList = new ArrayList();
				goalMap.forEach((key, goalObj) -> {

					goalObj.setObjectives(objectiveMap.get(key));

					goalList.add(goalObj);

				});
				perspective.setGoals(goalList);
				System.out.println(" after if else " + perspective.getName());
				return perspective;
			}

		});

	}

	private Goal prepareGoalObject(ResultSet rs) {

		Goal goal = new Goal();

		try {
			goal.setId(Long.parseLong(rs.getString("goal_id")));
			goal.setName(rs.getString("goal_name"));
			goal.setProgress(Integer.parseInt(rs.getString("goal_progress")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return goal;

	}

	private Objective prepareObjectiveObject(ResultSet rs) {

		Objective objective = new Objective();

		try {
			objective.setName(rs.getString("objective_name"));
			objective.setProgress(Integer.parseInt(rs.getString("objective_progress")));
			objective.setMeasure(rs.getString("objective_measure"));
			objective.setInitiative(rs.getString("objective_measure"));
			objective.setId(Long.parseLong(rs.getString("objective_id")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objective;

	}

}
