package thuchanh1_1;


public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY, null, null);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, Environment.LOCATION_A);

		env.step(3);
	}
}
