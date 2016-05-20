import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mkutilek on 5/13/2016.
 */
public class TheList {
    public static ArrayList<Member> theList = new ArrayList<>();

    public TheList(){
        Member mike = new Member("Mike Kutilek", null, null);
        theList.add(mike);
        mike.initializeSubLists();
        //mike.initializeSubConnections();
        //Member chris = new Member("Chris Orban", null, null);
        //Member dom = new Member("Dom Disanti", null, null);
        //Member lear = new Member("Zachary Lear", null, null);
        //Member corey = new Member("Corey Arndt", null, null);
       // Member stitt = new Member("Zack Stitt", null, null);

        //theList.add(chris);
        //theList.add(dom);
        //theList.add(lear);
        //theList.add(corey);
        //theList.add(stitt);
    }

    private static void run(){
        boolean quit = false;
        while (!quit){
            System.out.println("Choose an option (0 to quit): ");
            System.out.println("1. Display List");
            System.out.println("2. Find Someone on the List");
            Scanner scan = new Scanner(System.in);
            while(scan.hasNext()){
                int choice = scan.nextInt();
                scan.nextLine();
                if (choice == 0){
                    quit = true;
                    System.exit(1);
                }
                else if (choice == 1){
                    displayList();
                }
                else if (choice == 2) {
                    System.out.println("Enter name of member: ");
                    String name = scan.nextLine();
                    boolean found = false;
                    Member member = null;
                    for (int i = 0; i < theList.size(); i++) {
                        member = theList.get(i);
                        String memberName = member.getName();
                        if (name.equals(memberName)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("This member was not found on the list.");
                    } else {
                        System.out.println("User information: ");
                        System.out.println("  Name: " + name);
                        System.out.println("  Total # of Relationships: " + member.num_relationships);
                        System.out.println("  Total # of Connections: " + member.num_connections);
                        System.out.println("  Total Relationship Duration: " + Relationship.formatDuration(member.total_duration));
                        System.out.println("  Total Relationship Intensity: " + member.total_intensity);
                        System.out.println("Choose an option (0 to go back): ");
                        System.out.println("1. Display Relationships for " + name);
                        System.out.println("2. Display Connections for " + name);
                        System.out.println("3. Display All Connection paths for " + name);
                        System.out.println("4. Dispaly All Connection paths to a specific member");
                        Scanner scan2 = new Scanner(System.in);
                        while(scan2.hasNext()){
                            int choice2 = scan2.nextInt();
                            scan2.nextLine();
                            if (choice2 == 0) {
                                break;
                            } else if (choice2 == 1) {
                                displayRelationships(member);
                            } else if (choice2 == 2) {
                                displayConnections(member);
                            } else if (choice2 == 3) {
                                displayAllConnectionPaths(member);
                            } else if (choice2 == 4) {
                                System.out.println("Enter the member to connect to: ");
                                String name2 = scan.nextLine();
                                boolean found2 = false;
                                Member m2 = null;
                                for (int i = 0; i < theList.size(); i++) {
                                    m2 = theList.get(i);
                                    String memberName = m2.getName();
                                    if (name2.equals(memberName)) {
                                        found2 = true;
                                        break;
                                    }
                                }
                                if (found2) {
                                    displayConnectionPath(member, m2);
                                }
                                else {
                                    System.out.println("This member was not found on the list.");
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    private static void displayList(){
        for (int i = 0; i < theList.size(); i++){
            Member member = theList.get(i);
            System.out.println(member.getName());
        }
    }
    private static void displayRelationships(Member member){
        ArrayList<Relationship> relationshipList = member.relationshipList;
        for (int i = 0; i < relationshipList.size(); i++){
            Relationship relationship = relationshipList.get(i);
            String name = relationship.m2.getName();
            System.out.println("Name: " + name);
            System.out.println("  Duration: " + relationship.duration + " " + relationship.durStr);
            System.out.println("  Intensity: " + relationship.intensity);
        }
    }
    private static void displayConnections(Member member){
        ArrayList<Connection> connectionList = member.connectionList;
        for (int i = 0; i < connectionList.size(); i++){
            Connection connection = connectionList.get(i);
            String name = "";
            if (connection.m3 != null){
                name = connection.m3.getName();
            }
            else {
                name = connection.m2.getName();
            }
            System.out.println(name);
        }
    }
    private static void displayAllConnectionPaths(Member member){
        ArrayList<Connection> connectionList = member.connectionList;
        for (int i = 0; i < connectionList.size(); i++){
            Connection connection = connectionList.get(i);
            if (connection.m3 != null){
                String name = connection.m2.getName();
                String finalname = connection.m3.getName();
                System.out.println(member.getName() + " -> " + name + " -> " + finalname);
            }
            else {
                String name = connection.m2.getName();
                System.out.println(member.getName() + " -> " + name);
            }
        }
    }
    private static void displayConnectionPath(Member m1, Member m2){
        ArrayList<Connection> connectionList = m1.connectionList;
        boolean found = false;
        Connection connection = null;
        String name = "";
        String finalname = "";
        for (int i = 0; i < connectionList.size(); i++){
            connection = connectionList.get(i);
            if (connection.m3 != null){
                if (m2.getName().equals(connection.m3.getName())){
                    name = connection.m2.getName();
                    finalname = connection.m3.getName();
                    found = true;
                    System.out.println(m1.getName() + " -> " + name + " -> " + finalname);
                }
            }
            else {
                if (m2.equals(connection.m2)){
                    name = connection.m2.getName();
                    found = true;
                    System.out.println(m1.getName() + " -> " + name);
                }
            }
        }


        if (!found){
            System.out.println("We could not find a path between these two members");
        }

    }
    public static void main(String[] args){
        TheList thelist = new TheList();
        thelist.run();
    }
}
