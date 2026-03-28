import java.util.ArrayList;

public class AppMonitor {

    private String currentApp;
    private AppSession currentSession;
    private ArrayList<AppSession> sessions = new ArrayList<>();
    private boolean isRunning = false;
    private Thread monitorThread;

    public void start(){
      isRunning = true;
        monitorThread = new Thread(() -> {
            while(isRunning){
                try {
                String activeApp = getActiveApp();
                if(!activeApp.equals(currentApp)){
                    if(currentSession != null){
                        currentSession.end();
                        sessions.add(currentSession);
                    }
                    currentApp = activeApp;
                    currentSession = new AppSession(currentApp, java.time.LocalDateTime.now());
                }
               Thread.sleep(2000); // check every 2 seconds
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    public void stop(){
        isRunning = false;
    }

    public ArrayList<AppSession> getSessions(){
        return sessions;
    }

    private String getActiveApp(){
        try{
            ProcessBuilder pb = new ProcessBuilder("osascript", "-e", "tell application \"System Events\" to get name of first application process whose frontmost is true");
            Process process = pb.start();
            return new String(process.getInputStream().readAllBytes()).trim();
        } catch (Exception e){
            return "Unknown";
        }
    }



}
