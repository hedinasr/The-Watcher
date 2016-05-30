package jade;


import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

public class Container {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        // false = is not main
        ProfileImpl profile = new ProfileImpl(false);
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);

    }
}
