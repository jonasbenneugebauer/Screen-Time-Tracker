public class AppTracker {

        public static void main(String[] args) throws Exception {
            AppMonitor monitor = new AppMonitor();
            monitor.start();

            System.out.println("App Tracker is running. Switch Apps to see if it works.");

            Thread.sleep(15000); // wait for 15 seconds for testing
    
            monitor.stop();

            for(AppSession session : monitor.getSessions()){
                System.out.println("App: " + session.getAppName() + ", Duration: " + session.getDuration() + " seconds");
            }
               
        }



}
