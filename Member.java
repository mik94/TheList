import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mkutilek on 5/13/2016.
 */
public class Member {
    String name = "";
    int num_relationships = 0;
    int num_connections = 0;
    int total_duration = 0;
    double total_intensity = 0.0;
    ArrayList<Relationship> relationshipList = new ArrayList<Relationship>();
    ArrayList<Connection> connectionList = new ArrayList<Connection>();
    public Member(String name, ArrayList<Relationship> relationshipList, ArrayList<Connection> connectionList){
        this.name = name;
        if (name == "Mike Kutilek" || name == "Chris Orban" || name == "Dom Disanti" || name == "Zachary Lear" || name == "Corey Arndt" || name == "Zack Stitt"){
            //DOLKAS
        }
        else {

        }
    }

    public String getName(){
        return this.name;
    }

    public void initializeSubLists(){
        String name = this.getName();
        String[] nameArr = name.split("\\s+");
        String fname = nameArr[0];
        String filename = fname + ".txt";
        String line = null;
        try{
            FileReader fileReader = new FileReader("src/"+filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Member newmem;
            while((line = bufferedReader.readLine()) != null){
                if (line.length() == line.trim().length()){ //relationship
                    String[] lineArr = line.split("\\s+");
                    double duration = Double.parseDouble(lineArr[2]);
                    String durStr = lineArr[3];
                    double intensity = Double.parseDouble(lineArr[4]);
                    newmem = new Member(lineArr[0] + " " + lineArr[1], null, null);
                    Relationship r = new Relationship(this, newmem, duration, durStr, intensity);
                    Relationship r2 = new Relationship(newmem, this, 0,"", 0);
                    this.addRelationship(r);
                    newmem.addRelationship(r2);
                    Connection c = new Connection(this, newmem);
                    this.addConnection(c);
                    Connection c2 = new Connection(newmem, this);
                    newmem.addConnection(c2);
                }
                else {//connection
                    int index = relationshipList.size() - 1;
                    Relationship r = relationshipList.get(index);
                    String finalname = line.trim();
                    Member finalmem = null;
                    Connection c = null;
                    Connection c2 = null;
                    if (onList(finalname)){
                        System.out.println("Found");
                        finalmem = findOnList(finalname);
                        c = new Connection(this, r.m2, finalmem);
                        c2 = new Connection(finalmem, r.m2, this);
                    }
                    else {
                        finalmem = new Member(finalname, null, null);
                        c = new Connection(this, r.m2, finalmem);
                        c2 = new Connection(finalmem, r.m2, this);
                    }
                    this.addConnection(c);
                    finalmem.addConnection(c2);
                }

            }
            bufferedReader.close();
            fileReader.close();
        }
        catch(FileNotFoundException f){
            f.printStackTrace();
            System.exit(0);
        }
        catch(IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }
    public void initializeSubConnections(){
        String name = this.getName();
        String[] nameArr = name.split("\\s+");
        String fname = nameArr[0];
        String filename = fname + ".txt";
        String line = null;
        String line2 = null;
        try{
            FileReader fileReader = new FileReader("src/"+filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Member newmem;
            while((line = bufferedReader.readLine()) != null){
                if (line.length() == line.trim().length()){ //relationship
                    newmem = new Member(line, null, null);
                    Relationship r = new Relationship(this, newmem, 0,"", 0);
                    Relationship r2 = new Relationship(newmem, this, 0,"", 0);
                    this.addRelationship(r);
                    newmem.addRelationship(r2);
                    Connection c = new Connection(this, newmem);
                    this.addConnection(c);
                }
                else {//connection
                    int index = relationshipList.size() - 1;
                    Relationship r = relationshipList.get(index);
                    Member finalmem = new Member(line.trim(), null, null);
                    Connection c = null;
                    Connection c2 = null;
                    if (onList(line.trim())){
                        System.out.println("Found");
                        finalmem = finalmem.findOnList("");
                        c = new Connection(this, r.m2, finalmem);
                        c2 = new Connection(finalmem, r.m2, this);
                    }
                    else {
                        c = new Connection(this, r.m2, finalmem);
                        c2 = new Connection(finalmem, r.m2, this);
                    }

                    this.addConnection(c);
                    finalmem.addConnection(c2);
                }

            }
            bufferedReader.close();
            fileReader.close();
        }
        catch(FileNotFoundException f){
            f.printStackTrace();
            System.exit(0);
        }
        catch(IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }
    public void addRelationship(Relationship r){
        num_relationships++;
        double new_duration = r.duration;
        if (r.durStr.equals("years") || r.durStr.equals("year")){
            new_duration = r.duration * 12;
        }
        else if (r.durStr.equals("months") || r.durStr.equals("month")){
            new_duration = r.duration;
        }
        else if (r.durStr.equals("weeks") || r.durStr.equals("week")){
            new_duration = r.duration / 4;
        }
        else if (r.durStr.equals("days") || r.durStr.equals("day")){
            new_duration = r.duration / 30;
        }
        total_duration += new_duration;
        total_intensity += r.intensity;
        relationshipList.add(r);
    }
    public void addConnection(Connection c){
        num_connections++;
        connectionList.add(c);
        if (c.m3 != null){
            if (!onList(c.m3.getName()))
                TheList.theList.add(c.m3);
        }
        else {
            if (!onList(c.m2.getName()))
                TheList.theList.add(c.m2);
        }
    }
    public boolean onList(String name){
        for (int i = 0; i < TheList.theList.size(); i++){
            Member m = TheList.theList.get(i);
            if (name.equals(m.getName())){
                return true;
            }
        }
        return false;
    }
    public Member findOnList(String name){
        for (int i = 0; i < TheList.theList.size(); i++){
            Member m = TheList.theList.get(i);
            if (this.getName().equals(name)){
                return m;
            }
        }
        return null;
    }
}
