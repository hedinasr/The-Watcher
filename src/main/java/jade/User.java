package jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.tools.sniffer.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * RMA : Remote Management Agent which handles the GUI interface.
 * AMS : Agent Management Service - the core agent which keeps track of all JADE programs and agents in the system.
 * DF : Directory Facility, a yellow page service, where agents can publish their services.
 */
public class User extends Agent {

    @Override
    protected void setup() {

        addBehaviour(new SimpleBehaviour(this) {

            private boolean finished = true;

            /**
             * This action call periodically 'logwatch' command
             * and send the output to the User.
             */
            @Override
            public void action() {
                // ... this is where the real programming goes !!
                System.out.println("Hello ! My name is " + myAgent.getLocalName());

                // Send message to Smith
                Message message = new Message(new ACLMessage(ACLMessage.INFORM), new AID("peter", AID.ISLOCALNAME));
                message.setLanguage("English");
                //message.setContent(executeCommand("logwatch --level high"));
                message.setContent("Bonjour");
                send(message);
            }

            @Override
            public boolean done() {
                return finished;
            }
        });
    }

    /**
     * Execute command
     * @param command to execute
     * @return output of the command
     */
    private String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Main !!!");
    }
}
