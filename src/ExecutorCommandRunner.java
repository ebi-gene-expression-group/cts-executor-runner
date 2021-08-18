import java.io.*;

public class ExecutorCommandRunner implements Runnable {

    private String command;

    public ExecutorCommandRunner(String credentialsPath, String clusterName, String historyName,
                                 String workflowPath, String workflowInputsPath){


        StringBuilder builder = new StringBuilder();
        builder.append("run_galaxy_workflow.py ").
                append("-C ").append(credentialsPath + " ").
                append("-G ").append(clusterName + " ").
                append("-H ").append(historyName + " ").
                append("-W ").append(workflowPath + " ").
                append("-i ").append(workflowInputsPath + " ");
        //builder.append(" > ").append(outputPath + "run.log" + " 2>&1");
        command = builder.toString();
        System.out.println("Command: " + command);
    }
    @Override
    public void run() {
        System.out.println("Running Command: " + command);
        try {
            Process commandRun = Runtime.getRuntime().exec(command);
            int exitVal = commandRun.waitFor();
            //System.out.println(exitVal);
            if (exitVal == 0) {
                System.out.println("Success!");
            } else {
                System.out.println("Not So Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String credentialsPath = "/Users/a_solovyev/dev/sandbox/CTS_test/galaxy_credentials.yml";
        String clusterName = "ebi_cluster";
        String historyName = "java_test";
        String workflowPath = "/Users/a_solovyev/dev/sandbox/CTS_test/cts_control_workflow.json";
        String workflowInputPath = "/Users/a_solovyev/dev/sandbox/CTS_test/cts_control_workflow_parameters.yaml";
        //String outputPath = "/Users/a_solovyev/dev/sandbox/CTS_test/";

        ExecutorCommandRunner runner = new ExecutorCommandRunner(credentialsPath, clusterName, historyName,
                                                                 workflowPath, workflowInputPath);
        runner.run();
    }
}
