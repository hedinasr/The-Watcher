package jade;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        Properties properties = new ExtendedProperties();
        properties.setProperty(Profile.GUI, "true");
        ProfileImpl profile = new ProfileImpl(properties);
        AgentContainer agentContainer = runtime.createMainContainer(profile);
        try {
            agentContainer.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}
