import java.util.LinkedList;
import java.util.List;

public class Level {
    public int levelNumber;
    public int rows;
    public List<ParkingSlot> takenSlots;
    public int SPLOTS_PER_ROW = 2;

    public Level(int rows, int levelNumber){
        this.rows = rows;
        this.levelNumber = levelNumber;
        this.takenSlots = new LinkedList<>();
    }

    public ParkingSlot findAvailableSpot(){
        int totalNumberSlots = rows * SPLOTS_PER_ROW;
        if (takenSlots.size() >= totalNumberSlots){
            return null;
        }
        else if (takenSlots.isEmpty()){
            return new ParkingSlot(0, 0, levelNumber);

        }
        else {
            ParkingSlot lastSpaceOccupied = takenSlots.get(takenSlots.size() - 1);
            if (lastSpaceOccupied.col<SPLOTS_PER_ROW>){
                return new ParkingSlot(lastSpaceOccupied.row, lastSpaceOccupied.col + 1, levelNumber );
            }
            else {
                return new ParkingSlot(lastSpaceOccupied.row + 1,0, levelNumber );
            }

        }

        return null;
    }

    public boolean park(Car car){
        ParkingSlot freespace = findAvailableSpot();
        if (freespace == null){
            return false;
        }
        else{
            //if there is a free space, then take the parking spot
            freespace.park(car);
            takenSlots.add(freespace);
            return true;
        }
    }
}
