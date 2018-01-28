//Group #8
//Manoj Kumar Rajasekar - 1014265
//Megha Parikh - 1037741


package jdbcdemo;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Megha Parikh
 */
public class EventInformation implements Serializable{
    
    private final Integer x;
    private final Integer y;
    private String from = "CLIENT";
    private String guiId;
//    private final EventType eventType;

    public EventInformation(Integer x, Integer y, String guiId) {
        this.x = x;
        this.y = y;
        this.guiId = guiId;
//        this.eventType = eventType;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
    

//    public EventType getEventType() {
//        return eventType;
//    }
//    
    public static EventInformation getEventRelease(int x, int y, UUID uuId){
        return new EventInformation(x, y, uuId.toString());
    }

    public String getGuiId() {
        return guiId;
    }

    public void setGuiId(String guiId) {
        this.guiId = guiId;
    }

    @Override
    public String toString() {
        return "EventInformation{" + "x=" + x + ", y=" + y + ", from=" + from + ", guiId=" + guiId + '}';
    }
    
    
   
    
}