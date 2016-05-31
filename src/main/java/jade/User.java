package jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
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

        // Send message to admin
        //message.setLanguage("English");
        //message.setContent(executeCommand("logwatch --level high"));

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("admin", AID.ISLOCALNAME));
        message.setContent("Hello");
        send(message);
        addBehaviour(new CyclicBehaviour(this) {

            private boolean finished = true;

            /**
             * This action call periodically 'logwatch' command
             * and send the output to the User.
             */
            @Override
            public void action() {
                // ... this is where the real programming goes !!
                System.out.println("Hello ! My name is " + myAgent.getLocalName());
                System.out.println("Waiting for message");
                ACLMessage receive = myAgent.receive();
                if (receive != null) {
                    // process the received message
                    System.out.println("Receive message : " + message.getContent());
                } else {
                    // block the behaviour while receiving message
                    System.out.println("block");
                    block();
                }
            }
        });
    }

    /**
     * Execute command
     *
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
