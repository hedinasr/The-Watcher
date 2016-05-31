package container;


import agent.User;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Container {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        // false = is not main
        ProfileImpl profile = new ProfileImpl(false);
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        try {
            AgentController agentController = agentContainer.createNewAgent("user", User.class.getName(), new Object[]{});
            agentController.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
