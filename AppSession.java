import java.time.Duration;
import java.time.LocalDateTime;

public class AppSession {

    private String appName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public AppSession(String appName, LocalDateTime startTime) {
        this.appName = appName;
        this.startTime = startTime;
    }

    public void end(){
        this.endTime = LocalDateTime.now();
    }

    public long getDuration(){
        if(endTime==null){
            return 0;
        } else {
            return Duration.between(startTime, endTime).toSeconds();  
        }
    }
    public String getAppName() {
        return appName;
    }   

}
